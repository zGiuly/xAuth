package it.zgiulydev.zauth.tasks;

import it.zgiulydev.zauth.zAuth;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class AutoLogout extends BukkitRunnable {
    private final Player player;

    public AutoLogout(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        try {
            if (!player.isOnline()) {
                if(zAuth.getInstance().getLogged().contains(player.getName())) {
                    zAuth.getInstance().getLogged().remove(player.getName());
                }
            }
        }catch (NullPointerException ignored){}
    }
}
