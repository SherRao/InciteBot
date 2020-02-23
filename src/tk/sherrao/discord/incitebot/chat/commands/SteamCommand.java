package tk.sherrao.discord.incitebot.chat.commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.TextChannel;
import tk.sherrao.discord.incitebot.chat.ChatCommand;
import tk.sherrao.discord.incitebot.chat.ChatCommandManager;

public class SteamCommand extends ChatCommand {

    private MessageEmbed message;
    
    public SteamCommand( ChatCommandManager manager ) {
        super( manager, "steam", "account" );
        
        this.message = new EmbedBuilder()
                .setAuthor( "Incite's Steam Account", null, manager.getBot().getIconURL() )
                .setColor( manager.getBot().getColour() )
                .setTitle( "steamcommunity.com/id/InciteTV", "https://steamcommunity.com/id/InciteTV" )
                //.setFooter( "InciteBot | Developed by SherRao", null )
                .build();
        
    }

    @Override
    public void onExecute( Member author, TextChannel channel, String alias, String[] args ) {
        channel.sendMessage( message ).complete();
        
    }

    
}
