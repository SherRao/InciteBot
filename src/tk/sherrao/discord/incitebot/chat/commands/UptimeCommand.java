package tk.sherrao.discord.incitebot.chat.commands;

import tk.sherrao.discord.incitebot.chat.ChatCommandManager;
import tk.sherrao.discord.incitebot.chat.InactiveCommand;

public class UptimeCommand extends InactiveCommand {

    public UptimeCommand( ChatCommandManager manager ) {
        super( manager, "uptime" );
        
    }
    
}