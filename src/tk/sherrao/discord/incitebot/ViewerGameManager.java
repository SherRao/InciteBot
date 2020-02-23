package tk.sherrao.discord.incitebot;

import java.util.ArrayList;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.TextChannel;
import tk.sherrao.utils.strings.StringMultiJoiner;

public class ViewerGameManager {
    
    private InciteBot bot;
    private TextChannel viewergamesChannel;
    private Role onlineRole;
    
    private Message update;
    private ArrayList<Member> queue, lastPlayed;
    private boolean started;
    
    public ViewerGameManager( InciteBot bot ) {
        this.bot = bot;
        this.viewergamesChannel = this.bot.getAPI().getTextChannelById( BotWrapper.TEST_CHANNEL_ID );
        this.onlineRole = this.bot.getAPI().getRoleById( BotWrapper.ONLINE_ROLE_ID );
        
        this.queue = new ArrayList<>();
        
    }
    
    public void add( Member member ) {
        if( started ) {
            queue.add( member );
            updateMessage();
            
        } else return;
        
    }
    
    public void update( int amount ) {
        if( started ) {
            lastPlayed.clear();
            for( int i = 0; i < amount; i++ ) {
                Member m = queue.get(0);
                queue.remove( m );
                lastPlayed.add( m );
                
            }
            
            updateMessage();
            
        } else return;
        
    }
    
    public void updateMessage() {
        StringMultiJoiner currentQueue = new StringMultiJoiner( "\n" ).setEmptyValue( "Empty!" );
        for( int i = 1; i <= queue.size(); i++ ) 
            currentQueue.add( "  " + i + "> " + queue.get(i) );
        
        StringMultiJoiner lastQueue = new StringMultiJoiner( "\n" ).setEmptyValue( "Empty!" );
        for( int i = 1; i <= lastPlayed.size(); i++ )
            lastQueue.add( "  " + i + "> " + lastPlayed.get(i) );
        
        update.editMessage(  new EmbedBuilder()  
                .setAuthor( "Viewer Games Queue", currentQueue.toString(), bot.getIconURL() )
                .setColor( bot.getColour() )
                .addField( "Current Player Queue:", currentQueue.toString(), false )
                .addField( "Last Played With: ", lastQueue.toString(), false )
                //.setFooter( "InciteBot | Developed by SherRao", null )
                .build() ).complete();
        
    }

    public boolean start() {
        if( !started ) {
            viewergamesChannel.getManager().setName( "✅-viewergames" ).complete();
            viewergamesChannel.putPermissionOverride( onlineRole )
                .setAllow( Permission.MESSAGE_READ, Permission.MESSAGE_WRITE, Permission.VIEW_CHANNEL )
                .complete();
            
            update = viewergamesChannel.sendMessage( new EmbedBuilder()  
                    .setAuthor( "Viewer Games Queue", null, bot.getIconURL() )
                    .setColor( bot.getColour() )
                    .addField( "Current Player Queue", "Empty", false )
                    .addField( "Last Played With: ", "Empty", false )
                    //.setFooter( "InciteBot | Developed by SherRao", null )
                    .build() ).complete();
            
            update.pin().complete();
            started = true;
            return started;
            
        } else return false;
            
    }
    
    public boolean end() {
        if( started ) {
            viewergamesChannel.getManager().setName( "⛔-viewergames" ).complete();
            viewergamesChannel.putPermissionOverride( onlineRole )
                .setDeny( Permission.MESSAGE_READ, Permission.MESSAGE_WRITE, Permission.VIEW_CHANNEL )
                .complete();
            queue.clear();
            update.delete().complete();
            
            started = false;
            return true;
            
        } else return false;
        
    }
    
    public boolean started() {
        return started;
        
    }
    
    public boolean isInQueue( Member member ) {
        return queue.contains( member );
        
    }

}