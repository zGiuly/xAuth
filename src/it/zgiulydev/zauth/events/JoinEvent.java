package it.zgiulydev.zauth.events;

import it.zgiulydev.zauth.zAuth;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {
    @EventHandler
    public void join(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if(zAuth.getInstance().getLogged().contains(player.getName()))
    }
}
