package it.zgiulydev.zauth.utils;

import it.zgiulydev.zauth.tasks.AutoLogout;
import it.zgiulydev.zauth.zAuth;
import org.bukkit.Bukkit;

public class TaskUtils {
    public static void stopTask(String user) {
        zAuth.getInstance().getTaskManager().removeTaskFromUser(user);
    }

    public static void startAutoLogout(String user, int delay) {
        zAuth.getInstance().getTaskManager().addTaskToUser(user, new AutoLogout(Bukkit.getPlayerExact(user)), delay);
    }

    public static void restartAutoLogout(String user, int delay) {
        stopTask(user);
        startAutoLogout(user, delay);
    }
}
