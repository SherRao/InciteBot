package tk.sherrao.discord.incitebot.chat;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;

public abstract class InactiveCommand extends ChatCommand {

    public InactiveCommand( ChatCommandManager manager, String command, String... aliases ) {
        super( manager, command, aliases );
        
    }
    
    @Override
    public void onExecute( Member author, TextChannel channel, String alias, String[] args ) {
        channel.sendMessage( author.getAsMention() + ", That command is currently unavailable or a Work In Progress command!" ).complete();
        
    }

}
