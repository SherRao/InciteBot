package tk.sherrao.discord.incitebot.chat.commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.TextChannel;
import tk.sherrao.discord.incitebot.chat.ChatCommand;
import tk.sherrao.discord.incitebot.chat.ChatCommandManager;

public class RulesCommand extends ChatCommand {

    private MessageEmbed message;
    
    public RulesCommand( ChatCommandManager manager ) {
        super( manager, "rules" );
        
        this.message = new EmbedBuilder()
                .setAuthor( "Incite's Rules", null, manager.getBot().getIconURL() )
                .setColor( manager.getBot().getColour() )
                .setTitle( "You can read the chat rules here: pastebin.com/zSiKuLvs", "https://pastebin.com/zSiKuLvs" )
                //.setFooter( "InciteBot | Developed by SherRao", null )
                .build();
        
    }

    @Override
    public void onExecute( Member author, TextChannel channel, String alias, String[] args ) {
        channel.sendMessage( message ).complete();
        
    }

    
}
