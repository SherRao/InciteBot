package tk.sherrao.discord.incitebot;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.security.auth.login.LoginException;

import me.philippheuer.twitch4j.events.Event;
import me.philippheuer.twitch4j.events.IListener;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Game.GameType;
import tk.sherrao.discord.incitebot.chat.ChatCommandManager;
import tk.sherrao.discord.incitebot.console.ConsoleManager;
import tk.sherrao.logging.LogLevel;
import tk.sherrao.logging.Logger;

public class InciteBot {

    protected JDA api;

    protected Logger log;
    protected Color colour;
    protected String iconURL;

    protected ChatCommandManager chatManager;
    protected ConsoleManager consoleManager;
    protected MessageRegulator chatRegulator;
    protected GlobalEventManager eventManager;
    protected TwitchUpdater twitchUpdater;
    protected ViewerGameManager viewergames;
    
    protected Runnable consoleThread;
    protected Runnable regulatorThread;
    protected Runnable twitchThread;
    protected ScheduledExecutorService threadExecutor;
    
    protected File workingDirectory;
    
    public InciteBot() 
            throws LoginException, InterruptedException, UnsupportedEncodingException {
        
        this.api = new JDABuilder( AccountType.BOT )
                .setAudioEnabled( false )
                .setToken( BotWrapper.DISCORD_TOKEN )
                .setGame( Game.of( GameType.DEFAULT, "Starting..." ) )
                .setStatus( OnlineStatus.DO_NOT_DISTURB )
                .buildBlocking();

        this.log = new Logger( "InciteBot", LogLevel.TRACE );
        this.colour = new Color( 53, 128, 219 );
        this.iconURL = "https://cdn.discordapp.com/avatars/206411635752042496/2d8d1ca369a21e32231a88fc0b8dfc8a.png";
        
        this.threadExecutor = Executors.newScheduledThreadPool(3);
        this.workingDirectory = new File( URLDecoder.decode( InciteBot.class.getProtectionDomain().getCodeSource().getLocation().getPath(), "UTF-8" ) ).getParentFile();
        
        this.chatManager = new ChatCommandManager( this );
        this.consoleManager = new ConsoleManager( this );
        this.chatRegulator = new MessageRegulator( this );
        this.eventManager = new GlobalEventManager( this );
        this.twitchUpdater = new TwitchUpdater( this );
        this.viewergames = new ViewerGameManager( this );
        
        this.consoleThread = consoleManager;
        this.regulatorThread = chatRegulator;
        this.twitchThread = twitchUpdater;

    }
    
    public PrintStream disableJDAOut() {
        PrintStream out = System.out;
        PrintStream dummy = new PrintStream( new OutputStream() {

            @Override
            public void write( int out ) throws IOException {} 
            
        } );
        
        System.setOut( dummy );
        System.setErr( dummy );
        return out;
        
    }
    
    public void load() {
        api.getPresence().setGame( Game.of( GameType.DEFAULT, "Loading..." ) );
        try {
            chatManager.loadCommands();
            consoleManager.loadCommands();
     
        } catch ( InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e ) { e.printStackTrace(); }
        
        api.addEventListener( eventManager );
        api.getPresence().setGame( twitchUpdater.waiting );
        threadExecutor.schedule( consoleThread, 5, TimeUnit.SECONDS );
        threadExecutor.scheduleWithFixedDelay( twitchThread, 5, 60, TimeUnit.SECONDS );
        /*threadExecutor.scheduleWithFixedDelay( regulatorThread, 5, 60, TimeUnit.SECONDS );*/

    }
    
    public void reload1() {
        System.out.println( "Reloading...." );
        threadExecutor.shutdown();
        threadExecutor = null;
        threadExecutor = Executors.newScheduledThreadPool(5);
        threadExecutor.schedule( consoleThread, 5, TimeUnit.SECONDS );
        threadExecutor.scheduleWithFixedDelay( regulatorThread, 5, 60, TimeUnit.SECONDS );
        threadExecutor.scheduleWithFixedDelay( twitchThread, 5, 60, TimeUnit.SECONDS );
                
        twitchUpdater.twitch.disconnect();
        twitchUpdater = null;
        twitchUpdater = new TwitchUpdater( this );
        twitchUpdater.twitch.connect();
        twitchUpdater.twitch.getDispatcher().registerListener( new IListener<Event>() {

            @Override
            public void handle( Event event ) {
                
            } } );

        chatManager.getCommands().clear();
        consoleManager.getCommands().clear();
        try {
            chatManager.loadCommands();
            consoleManager.loadCommands();

        } catch ( InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e ) { e.printStackTrace(); }
        
        System.out.println( "Reloaded..." );
        
    }
    
    public void shutdown() {
        System.out.println( "Shutting down..." );
        threadExecutor.shutdownNow();
        twitchUpdater.twitch.disconnect();
        api.shutdown();
        System.out.println( "Done!" );
        
    }
    
    public JDA getAPI() {
        return api;
        
    }
    
    public Color getColour() {
        return colour;
        
    }
    
    public String getIconURL() {
        return iconURL;
        
    }
    
    public ChatCommandManager getChatManager() {
        return chatManager;
    
    }

    public ConsoleManager getConsoleManager() {
        return consoleManager;
    
    }

    public MessageRegulator getChatRegulator() {
        return chatRegulator;
    
    }

    public GlobalEventManager getEventManager() {
        return eventManager;
    
    }
    
    public ViewerGameManager getViewerGamesManager() {
        return viewergames;
        
    }
    
    public File getWorkingDirectory() {
        return workingDirectory;
        
    }
    
}