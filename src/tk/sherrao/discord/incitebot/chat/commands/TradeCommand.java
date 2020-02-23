package tk.sherrao.discord.incitebot.chat.commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.TextChannel;
import tk.sherrao.discord.incitebot.chat.ChatCommand;
import tk.sherrao.discord.incitebot.chat.ChatCommandManager;

public class TradeCommand extends ChatCommand {

    private MessageEmbed message;
    
    public TradeCommand( ChatCommandManager manager ) {
        super( manager, "trade" );
        
        this.message = new EmbedBuilder()
                .setAuthor( "Incite's Steam Trade URL", null, manager.getBot().getIconURL() )
                .setColor( manager.getBot().getColour() )
                .setTitle( "steamcommunity.com/tradeoffer/new/?partner=27084025&token=RORbnZgt", "https://steamcommunity.com/tradeoffer/new/?partner=27084025&token=RORbnZgt" )
                //.setFooter( "InciteBot | Developed by SherRao", null )
                .build();
        
    }

    @Override
    public void onExecute( Member author, TextChannel channel, String alias, String[] args ) {
        channel.sendMessage( message ).complete();
        
    }

    
}
