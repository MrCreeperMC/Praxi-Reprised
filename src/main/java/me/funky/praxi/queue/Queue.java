package me.funky.praxi.queue;

import lombok.Getter;
import me.funky.praxi.Locale;
import me.funky.praxi.Praxi;
import me.funky.praxi.kit.Kit;
import me.funky.praxi.profile.Profile;
import me.funky.praxi.profile.ProfileState;
import me.funky.praxi.profile.hotbar.Hotbar;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Queue {


    @Getter
    private final UUID uuid = UUID.randomUUID();
    @Getter
    private final Kit kit;
    @Getter
    private final boolean ranked;

    public Queue(Kit kit, boolean ranked) {
        this.kit = kit;
        this.ranked = ranked;

        Praxi.getInstance().getCache().getQueues().add(this);
    }

    public static Queue getByUuid(UUID uuid) {
        for (Queue queue : Praxi.getInstance().getCache().getQueues()) {
            if (queue.getUuid().equals(uuid)) {
                return queue;
            }
        }

        return null;
    }

    public String getQueueName() {
        return (ranked ? "Ranked" : "Unranked") + " " + kit.getName();
    }

    public void addPlayer(Player player, int elo) {
        QueueProfile queueProfile = new QueueProfile(this, player.getUniqueId());
        queueProfile.setElo(elo);

        Profile profile = Profile.getByUuid(player.getUniqueId());
        profile.setQueueProfile(queueProfile);
        profile.setState(ProfileState.QUEUEING);

        Praxi.getInstance().getCache().getPlayers().add(queueProfile);

        Hotbar.giveHotbarItems(player);

        if (ranked) {
            player.sendMessage(Locale.QUEUE_JOIN_RANKED.format(kit.getName(), elo));
        } else {
            player.sendMessage(Locale.QUEUE_JOIN_UNRANKED.format(kit.getName()));
        }
    }

    public void removePlayer(QueueProfile queueProfile) {
        Praxi.getInstance().getCache().getPlayers().remove(queueProfile);

        Profile profile = Profile.getByUuid(queueProfile.getPlayerUuid());
        profile.setQueueProfile(null);
        profile.setState(ProfileState.LOBBY);

        Player player = Bukkit.getPlayer(queueProfile.getPlayerUuid());

        if (player != null) {
            Hotbar.giveHotbarItems(player);

            if (ranked) {
                player.sendMessage(Locale.QUEUE_LEAVE_RANKED.format(kit.getName()));
            } else {
                player.sendMessage(Locale.QUEUE_LEAVE_UNRANKED.format(kit.getName()));
            }
        }

    }

}
