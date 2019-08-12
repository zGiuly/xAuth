package it.zgiulydev.zauth.tasks;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class TaskManager {
    private final Map<String, BukkitRunnable> tasks;
    private final JavaPlugin plugin;

    public TaskManager(JavaPlugin plugin) {
        this.tasks = new HashMap<>();
        this.plugin = plugin;
    }

    public void addTaskToUser(String user, BukkitRunnable task, int delay) {
        try {
            if (Bukkit.getPlayerExact(user).isOnline()) {
                if(tasks.containsKey(user)) return;
                task.runTaskTimer(plugin, 0, delay);
                tasks.put(user, task);
            }
        }catch (NullPointerException ignored){}
    }

    public void removeTaskFromUser(String user) {
        try {
            if (!tasks.containsKey(user)) return;
            tasks.get(user).cancel();
            tasks.remove(user);
        } catch (NullPointerException ignored) {}
    }

    public Map<String, BukkitRunnable> getTasks() {
        return tasks;
    }
}
