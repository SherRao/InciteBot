package tk.sherrao.discord.incitebot.chat.commands;

import java.awt.Color;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.TextChannel;
import tk.sherrao.discord.incitebot.BotWrapper;
import tk.sherrao.discord.incitebot.ViewerGameManager;
import tk.sherrao.discord.incitebot.chat.ChatCommand;
import tk.sherrao.discord.incitebot.chat.ChatCommandManager;

public class ViewerGamesCommand extends ChatCommand {

    protected ViewerGameManager games;
    protected TextChannel announcements;
    protected MessageEmbed startedMsg, endedMsg;
    
    public ViewerGamesCommand( ChatCommandManager manager ) {
        super( manager, "viewergame", "viewergames", "vg", "games" );
        
        this.games = manager.getBot().getViewerGamesManager();
        this.announcements = manager.getBot().getAPI().getTextChannelById( BotWrapper.TEST_CHANNEL_ID );
        this.startedMsg = new EmbedBuilder()
                .setAuthor( "Viewer Games Have Started! :D", null, manager.getBot().getIconURL() )
                .setColor( Color.GREEN )
                .setTitle( "Viewer games have started!\nUse _'" + BotWrapper.CHAT_PREFIX + "join'_ to join the viewer games queue!" )
                //.setFooter( "InciteBot | Developed by SherRao", null )
                .build();
        
        this.endedMsg = new EmbedBuilder()
                .setAuthor( "Viewer Games Have Ended! :(", null, manager.getBot().getIconURL() )
                .setColor( Color.GREEN )
                .setTitle( "Viewer games have ended!\nThanks to everyone who participated and/or watched!" )
                //.setFooter( "InciteBot | Developed by SherRao", null )
                .build();
        
    }

    @Override
    public void onExecute( Member author, TextChannel channel, String alias, String[] args ) {
        if( author.getUser().getId().equals( BotWrapper.SHER_USER_ID ) && channel.getId().equals( BotWrapper.MOD_CHANNEL_ID ) ) {
            if( args.length > 0 ) {
                if( args[0].equalsIgnoreCase( "start" ) ) {
                    if( games.start() ) {
                        announcements.sendMessage( startedMsg ).complete();
                        
                    } else channel.sendMessage( author.getAsMention() + ", the viewer games have already been started!" ).complete();
                            
                } else if( args[0].equalsIgnoreCase( "end" ) ) {
                    if( games.end() ) {
                        announcements.sendMessage( endedMsg ).complete();
                        
                    } else channel.sendMessage( author.getAsMention() + ", the viewer games have already been ended!" ).complete();
                
                } else if( args[0].equalsIgnoreCase( "update" ) ) {
                    if( args.length > 1 ) {
                        games.update( Integer.parseInt( args[1] ) );
                        
                    } else channel.sendMessage( author.getAsMention() + ", you have to specify how many users you want to update!" ).complete();
                    
                    
                } else channel.sendMessage( author.getAsMention() + ", either use '" + BotWrapper.CHAT_PREFIX + alias + " start' or '" + BotWrapper.CHAT_PREFIX + alias + " end' as an argument!" ).complete();
                 
            } else channel.sendMessage( author.getAsMention() + ", either use '" + BotWrapper.CHAT_PREFIX + alias + " start' or '" + BotWrapper.CHAT_PREFIX + alias + " end' as an argument!" ).complete();
            
        } else return;
        
    }
    

}
