package it.zgiulydev.zauth.events;

import it.zgiulydev.zauth.utils.Messages;
import it.zgiulydev.zauth.utils.TaskUtils;
import it.zgiulydev.zauth.zAuth;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;

public class JoinEvent implements Listener {
    private Map<String, BukkitTask> tasks;

    public JoinEvent() {
        tasks = new HashMap<>();
    }

    @EventHandler
    public void join(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if(zAuth.getInstance().getLogged().contains(player.getName())) {
            TaskUtils.restartAutoLogout(player.getName(), 12000);
            return;
        }
        BukkitTask task = Bukkit.getScheduler().runTaskTimerAsynchronously(zAuth.getInstance(), () -> {
            if(zAuth.getInstance().getLogged().contains(player.getName())) {
                tasks.get(player.getName()).cancel();
            }
            player.sendMessage("Required login!");
        }, 0, 40);
        tasks.put(player.getName(), task);
    }
}
