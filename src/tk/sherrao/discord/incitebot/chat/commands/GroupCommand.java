package tk.sherrao.discord.incitebot.chat.commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.TextChannel;
import tk.sherrao.discord.incitebot.chat.ChatCommand;
import tk.sherrao.discord.incitebot.chat.ChatCommandManager;

public class GroupCommand extends ChatCommand {

    private MessageEmbed message;
    
    public GroupCommand( ChatCommandManager manager ) {
        super( manager, "group" );
        
        this.message = new EmbedBuilder()
                .setAuthor( "Incite's Steam Group", null, manager.getBot().getIconURL() )
                .setColor( manager.getBot().getColour() )
                .setTitle( "oin our group! You'll be notified when Incite goes live steamcommunity.com/groups/InciteTV", "http://steamcommunity.com/groups/InciteTV" )
                //.setFooter( "InciteBot | Developed by SherRao", null )
                .build();
        
    }

    @Override
    public void onExecute( Member author, TextChannel channel, String alias, String[] args ) {
        channel.sendMessage( message ).complete();
        
    }
    
}