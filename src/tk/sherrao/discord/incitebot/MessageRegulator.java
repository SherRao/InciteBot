package tk.sherrao.discord.incitebot;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;

public class MessageRegulator implements Runnable {

    protected InciteBot bot;
    protected TextChannel gfChannel;
    protected String pinnedMessage;
    
    public MessageRegulator( InciteBot bot ) {
        this.bot = bot;
        this.gfChannel = bot.getAPI().getTextChannelById( BotWrapper.GROUP_FINDER_CHANNEL_ID );
  
        this.pinnedMessage = "```" 
                + "Messages are automatically removed after 60 minutes. ONLY use this channel to search/create groups. Game discussions and or general discussions belong in #general_chat."
                + "\n\nBe sure to tag @CSGO or @Fortnite in your message depending on the group you're looking for. If you don't have any of these roles, then ask one of the mods to give it to you. When you have either of these roles, you'll get a ping whenever someone is looking to create a group."
                + "```";
        
    }

    public void onFoodPornMessage( Member author, Message message ) {
        if( message.getAttachments().isEmpty() && !isLink( message.getContentRaw() ) ) 
            message.delete().complete();
        
        else return;
        
    } 
    
    private boolean isLink( String link ) {
        try {
            ImageIO.read( new URL( link ) );
            return true;

        } catch ( IOException e ) {
            for( String str : link.split( " " ) ) {
                try {
                    ImageIO.read( new URL( str ) ); 
                    return true;
                        
                } catch( IOException e1 ) {
                    if( link.contains( "prntscr.com" ) ) 
                        return true;
                        
                    else if( link.equals( "http://" ) || link.equals( "https://" ) ) 
                        return false;
                            
                    else return false;
                        
                }
            }
        }
            
        return false;
        
    }
    
    @Override
    public void run() {
        List<Message> messages = gfChannel.getIterableHistory().complete();
        if( messages.isEmpty() ) 
            gfChannel.sendMessage( pinnedMessage ).complete();
            
        else {
            for( Message message : messages ) {
                if( !message.isPinned() && message.getCreationTime().toInstant().toEpochMilli() <= System.currentTimeMillis() - 1000 * 60 * 60 )
                    message.delete().complete();
            
                else continue;
         
            }
        }
    }
    
    public InciteBot getBot() {
        return bot;
        
    }
    
}