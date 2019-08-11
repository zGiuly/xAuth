package it.zgiulydev.zauth.commands;

import it.zgiulydev.zauth.sql.UserManager;
import it.zgiulydev.zauth.utils.Messages;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RegisterCommand extends Command {
    public RegisterCommand() {
        super("/register", SenderType.PLAYER);
    }

    @Override
    void execute(CommandSender sender, String s, String[] strings) {
        Player player = (Player)sender;
        if(strings.length != 0) {
            String password = strings[1];
            if(new UserManager(player.getName(), password).registerUser()) {
                player.sendMessage(Messages.REGISTERED.toString());
            }else {
                player.sendMessage(Messages.USER_EXISTS.toString());
            }
        } else {
            player.sendMessage(Messages.USER_EXISTS.toString());
        }
    }
}
