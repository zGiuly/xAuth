package it.zgiulydev.zauth.commands;

import it.zgiulydev.zauth.sql.Database;
import it.zgiulydev.zauth.zAuth;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public abstract class Command implements CommandExecutor {
    private final String commandName;
    private final SenderType senderType;
    private zAuth instance;
    private Database database;

    public Command(String commandName, SenderType senderType) {
        this.commandName = commandName;
        this.senderType = senderType;
        this.instance = zAuth.getInstance();
        this.database = instance.getDb();
    }

    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        switch (senderType) {
            case PLAYER:
                if(commandSender instanceof Player) {
                    execute(commandSender, s, strings);
                }
                break;
            case CONSOLE:
                if(commandSender instanceof ConsoleCommandSender) {
                    execute(commandSender, s, strings);
                }
                break;
        }
        return true;
    }

    public zAuth getInstance() {
        return instance;
    }

    public Database getDatabase() {
        return database;
    }

    public String getCommandName() {
        return commandName;
    }

    abstract void execute(CommandSender sender, String s, String[] strings);
}
