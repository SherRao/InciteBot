package tk.sherrao.discord.incitebot.chat.commands;

import tk.sherrao.discord.incitebot.chat.ChatCommandManager;
import tk.sherrao.discord.incitebot.chat.NightbotCommand;

public class SubscribersCommand extends NightbotCommand {

    public SubscribersCommand( ChatCommandManager manager ) {
        super( manager, "subscribers", "subs", "sub", "subscribe", "subscriber" );
        
    }
    
}