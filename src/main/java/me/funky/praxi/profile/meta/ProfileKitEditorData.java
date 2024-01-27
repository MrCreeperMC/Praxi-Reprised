package me.funky.praxi.profile.meta;

import lombok.Getter;
import lombok.Setter;
import me.funky.praxi.kit.Kit;
import me.funky.praxi.kit.KitLoadout;

public class ProfileKitEditorData {

    @Getter
    @Setter
    private boolean active;
    @Setter
    private boolean rename;
    @Getter
    @Setter
    private Kit selectedKit;
    @Getter
    @Setter
    private KitLoadout selectedKitLoadout;

    public boolean isRenaming() {
        return this.active && this.rename && this.selectedKit != null;
    }

}
