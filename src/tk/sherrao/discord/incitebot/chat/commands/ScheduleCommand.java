package tk.sherrao.discord.incitebot.chat.commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.TextChannel;
import tk.sherrao.discord.incitebot.chat.ChatCommand;
import tk.sherrao.discord.incitebot.chat.ChatCommandManager;

public class ScheduleCommand extends ChatCommand {

    private MessageEmbed message;
    
    public ScheduleCommand( ChatCommandManager manager ) {
        super( manager, "schedule" );
        
        this.message = new EmbedBuilder()
                .setAuthor( "Incite's Stream Schedule", null, manager.getBot().getIconURL() )
                .setColor( manager.getBot().getColour() )
                .setTitle( "Incite streams Saturday-Monday from 13:00 CET." )
                //.setFooter( "InciteBot | Developed by SherRao", null )
                .build();
        
    }

    @Override
    public void onExecute( Member author, TextChannel channel, String alias, String[] args ) {
        channel.sendMessage( message ).complete();
        
    }

    
}
