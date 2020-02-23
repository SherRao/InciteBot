package tk.sherrao.discord.incitebot.chat.commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.TextChannel;
import tk.sherrao.discord.incitebot.chat.ChatCommand;
import tk.sherrao.discord.incitebot.chat.ChatCommandManager;

public class ResolutionCommand extends ChatCommand {

    private MessageEmbed message;
    
    public ResolutionCommand( ChatCommandManager manager ) {
        super( manager, "resolution", "res" );
        
        this.message = new EmbedBuilder()
                .setAuthor( "Incite's Ingame Resolution", null, manager.getBot().getIconURL() )
                .setColor( manager.getBot().getColour() )
                .setTitle( "Incite current plays @ 1920 x 1080 with a 16:9 aspect ratio" )
                //.setFooter( "InciteBot | Developed by SherRao", null )
                .build();
        
    }

    @Override
    public void onExecute( Member author, TextChannel channel, String alias, String[] args ) {
        channel.sendMessage( message ).complete();
        
    }

    
}
