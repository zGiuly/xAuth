package it.zgiulydev.zauth.commands;

import it.zgiulydev.zauth.sql.UserManager;
import it.zgiulydev.zauth.tasks.AutoLogout;
import it.zgiulydev.zauth.utils.Messages;
import it.zgiulydev.zauth.utils.TaskUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LoginCommand extends Command {
    public LoginCommand() {
        super("/login", SenderType.PLAYER);
    }

    @Override
    void execute(CommandSender sender, String s, String[] strings) {
        Player player = (Player)sender;
        if(strings.length != 0) {
            String password = strings[1];
            if(new UserManager(player.getName(), password).login()) {
                player.sendMessage(Messages.LOGGED.toString());
                TaskUtils.startAutoLogout(player.getName(), 12000);
            }else {
                player.sendMessage(Messages.PASSWORD_NOT_CORRECT.toString());
            }
        } else {
            player.sendMessage(Messages.PASSWORD_NOT_CORRECT.toString());
        }
    }
}
