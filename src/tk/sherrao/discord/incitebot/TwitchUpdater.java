package tk.sherrao.discord.incitebot;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.TimeZone;

import me.philippheuer.twitch4j.TwitchClient;
import me.philippheuer.twitch4j.TwitchClientBuilder;
import me.philippheuer.twitch4j.endpoints.ChannelEndpoint;
import me.philippheuer.twitch4j.endpoints.StreamEndpoint;
import me.philippheuer.twitch4j.model.Channel;
import me.philippheuer.twitch4j.model.Stream;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Game.GameType;
import net.dv8tion.jda.core.entities.TextChannel;

public class TwitchUpdater implements Runnable {

    protected InciteBot bot;
    protected TwitchClient twitch;
    
    protected ChannelEndpoint channels;
    protected StreamEndpoint streams;
    protected Channel channel;
    
    protected Game streaming, waiting;
    protected TextChannel announcementChannel;
    protected boolean liveOnBot;
    
    
    private long iteration;
    
    public TwitchUpdater( InciteBot bot ) {
        this.bot = bot;
        this.twitch = TwitchClientBuilder.init()
                .withClientId( BotWrapper.TWITCH_ID )
                .withClientSecret( BotWrapper.TWITCH_SECRET )
                .withCredential( BotWrapper.TWITCH_OAUTH )
                .connect();
        
        this.streaming = Game.of( GameType.STREAMING, "InciteTV live on Twitch!!", "https://www.twitch.tv/incitetv" );
        this.waiting = Game.of( GameType.DEFAULT, "dOnT fOrGeT tO bUiLd A cAsTlE oR sOmEtHiNg!!!", "https://www.twitch.tv/incitetv" );
        this.announcementChannel = bot.getAPI().getTextChannelById( BotWrapper.MOD_CHANNEL_ID );
        this.liveOnBot = false;

    }   
    
    @Override
    public void run() {
        iteration++;
        channels = twitch.getChannelEndpoint( "InciteTV" );
        streams = twitch.getStreamEndpoint();
        channel = channels.getChannel();
        System.out.println( LocalDateTime.now().toString() + "   Twitch Updater Iteration: " + iteration );
        
        if( streams.isLive( channel ) && !liveOnBot ) {
            Stream stream = twitch.getStreamEndpoint().getByChannel( channel ).get();
            Calendar normal = Calendar.getInstance();
            normal.set( Calendar.YEAR, Calendar.MONTH, Calendar.DATE, 12, 0, 0 );
            normal.setTimeZone( TimeZone.getTimeZone( "UTC" ) );
            
            Instant regular = normal.toInstant();
            Instant started = Instant.ofEpochMilli( regular.toEpochMilli() + (System.currentTimeMillis() - regular.toEpochMilli()) );
            Duration delay = Duration.ofMillis( started.toEpochMilli() - regular.toEpochMilli() );
            
            announcementChannel.sendMessage( new MessageBuilder()
                    .setContent( "**everyone InciteTV is now live on Twitch! Come along for the ride! http://twitch.tv/InciteTV**"  )
                    .setEmbed( new EmbedBuilder()
                            .setThumbnail( bot.getIconURL() )
                            .setColor( bot.getColour() )
                            .addField( "Game", stream.getGame(), true )
                            .addField( "Viewers", String.valueOf( stream.getViewers() ), true )
                            .addField( "Stream Late By", delay.toMinutes() + " Minutes", true )
                            .setImage( stream.getPreview().getLarge() )
                            .build() ).build() ).complete();
            
            bot.getAPI().getPresence().setGame( streaming );
            liveOnBot = true;
            
        } else if( !streams.isLive( channel ) && liveOnBot ) {
            bot.getAPI().getPresence().setGame( waiting );
            liveOnBot = false;
            
        } else return; 
        
    }
    
}
