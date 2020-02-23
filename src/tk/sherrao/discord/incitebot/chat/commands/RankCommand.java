package tk.sherrao.discord.incitebot.chat.commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.TextChannel;
import tk.sherrao.discord.incitebot.chat.ChatCommand;
import tk.sherrao.discord.incitebot.chat.ChatCommandManager;

public class RankCommand extends ChatCommand {

    protected MessageEmbed message;
    
    public RankCommand( ChatCommandManager manager ) {
        super( manager, "group" );
        
        this.message = new EmbedBuilder()
                .setAuthor( "Incite's Rank", null, manager.getBot().getIconURL() )
                .setColor( manager.getBot().getColour() )
                .setTitle( "Incite is currently Global Elite (or so he thinks)" )
                //.setFooter( "InciteBot | Developed by SherRao", null )
                .build();
        
    }
    
    @Override
    public void onExecute( Member author, TextChannel channel, String alias, String[] args ) {
        channel.sendMessage( message ).complete();
        
    }

}