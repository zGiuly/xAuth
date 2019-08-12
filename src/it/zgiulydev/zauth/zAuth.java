package it.zgiulydev.zauth;

import it.zgiulydev.zauth.commands.CommandManager;
import it.zgiulydev.zauth.commands.LoginCommand;
import it.zgiulydev.zauth.commands.RegisterCommand;
import it.zgiulydev.zauth.sql.Database;
import it.zgiulydev.zauth.tasks.TaskManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class zAuth extends JavaPlugin {
    private static zAuth instance;
    private Database database;
    private Set<String> logged;
    private TaskManager taskManager;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        database = new Database(new File(getDataFolder(), "sql.db"));
        database.connect();
        logged = new HashSet<>();
        taskManager = new TaskManager(this);
        new CommandManager(this, new LoginCommand(), new RegisterCommand()).registerCommands();
    }

    public Set<String> getLogged() {
        return logged;
    }

    public static zAuth getInstance() {
        return instance;
    }

    public Database getDb() {
        return database;
    }

    public TaskManager getTaskManager() {
        return taskManager;
    }
}
