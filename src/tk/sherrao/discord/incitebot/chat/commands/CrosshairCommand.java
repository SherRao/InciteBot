package tk.sherrao.discord.incitebot.chat.commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.TextChannel;
import tk.sherrao.discord.incitebot.chat.ChatCommand;
import tk.sherrao.discord.incitebot.chat.ChatCommandManager;

public class CrosshairCommand extends ChatCommand {

    private MessageEmbed message;
    
    public CrosshairCommand( ChatCommandManager manager ) {
        super( manager, "crosshair", "ch", "xhair", "xh" );

        this.message = new EmbedBuilder()
                .setAuthor( "Incite's CS:GO Crosshair", null, manager.getBot().getIconURL() )
                .setColor( manager.getBot().getColour() )
                .setTitle( "pastebin.com/gEsL2TaZ", "https://pastebin.com/gEsL2TaZ" )
                //.setFooter( "InciteBot | Developed by SherRao", null )
                .build();
        
    }

    @Override
    public void onExecute( Member author, TextChannel channel, String alias, String[] args ) {
        channel.sendMessage( message ).complete();
        
    }

}
