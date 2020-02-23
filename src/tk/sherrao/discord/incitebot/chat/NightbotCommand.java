package tk.sherrao.discord.incitebot.chat;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;

public abstract class NightbotCommand extends ChatCommand {

    public NightbotCommand( ChatCommandManager manager, String command, String... aliases ) {
        super( manager, command, aliases );
        
    }
    
    @Override
    public void onExecute( Member author, TextChannel channel, String alias, String[] args ) {
        channel.sendMessage( author.getAsMention() + ", That command is a Nightbot command and is currently unavailable!" ).complete();
        
    }

}
