package tk.sherrao.discord.incitebot.chat.commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.TextChannel;
import tk.sherrao.discord.incitebot.chat.ChatCommand;
import tk.sherrao.discord.incitebot.chat.ChatCommandManager;

public class FACEITCommand extends ChatCommand {

    private MessageEmbed message;
    
    public FACEITCommand( ChatCommandManager manager ) {
        super( manager, "faceit" );

        this.message = new EmbedBuilder()
                .setAuthor( "Incite's FACEIT Career", null, manager.getBot().getIconURL() )
                .setColor( manager.getBot().getColour() )
                .setTitle( "Incite is currently Silver LUL faceit.com/en/players/InciteTV", "https://www.faceit.com/en/players/InciteTV" )
                //.setFooter( "InciteBot | Developed by SherRao", null )
                .build();
        
    }

    @Override
    public void onExecute( Member author, TextChannel channel, String alias, String[] args ) {
        channel.sendMessage( message ).complete();
        
    }

}
