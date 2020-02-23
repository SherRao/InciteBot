package tk.sherrao.discord.incitebot.chat.commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.TextChannel;
import tk.sherrao.discord.incitebot.chat.ChatCommand;
import tk.sherrao.discord.incitebot.chat.ChatCommandManager;

public class GiveawayCommand extends ChatCommand {

    protected MessageEmbed message;
    
    public GiveawayCommand( ChatCommandManager manager ) {
        super( manager, "giveaway", "knife" );
        
        this.message = new EmbedBuilder()
                .setAuthor( "Incite's CS:GO Knife Giveaway", null, manager.getBot().getIconURL() )
                .setColor( manager.getBot().getColour() )
                .setTitle( "Incite is doing another knife giveaway at 3000 followers. Here's the link to the entry: gleam.io/pwj8k/csgo-knife-giveaway", "https://www.gleam.io/pwj8k/csgo-knife-giveaway" )
                //.setFooter( "InciteBot | Developed by SherRao", null )
                .build();
        
    }
    
    @Override
    public void onExecute( Member author, TextChannel channel, String alias, String[] args ) {
        channel.sendMessage( message ).complete();
        
    }

}