package it.zgiulydev.zauth.commands;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.Optional;

public class CommandManager {
    private final Command[] commands;
    private final JavaPlugin plugin;

    public CommandManager(JavaPlugin plugin, Command... commands) {
        this.commands = commands;
        this.plugin = plugin;
    }

    public void registerCommands() {
        Arrays.stream(commands).forEach((command -> Optional.ofNullable(plugin.getCommand(command.getCommandName())).orElseThrow(() -> new NullPointerException(String.format("Command %s not found!", command.getCommandName()))).setExecutor(command)));
    }
}
