package tk.sherrao.discord.incitebot;

import java.io.UnsupportedEncodingException;

import javax.security.auth.login.LoginException;

public class BotWrapper {

    public static String DISCORD_ID = null;
    public static String DISCORD_TOKEN = null;
    
    public static String TWITCH_ID = null;
    public static String TWITCH_SECRET = null;
    public static String TWITCH_OAUTH = null;
    
    public static String CHAT_PREFIX = "!";
    public static String CONSOLE_PREFIX = "/";
    
    public static String GROUP_FINDER_CHANNEL_ID = null;
    public static String FOOD_PORN_CHANNEL_ID = null;
    public static String COMMANDS_CHANNEL_ID = null;
    public static String ANNOUNCEMENT_CHANNEL_ID = null;;
    public static String TEST_CHANNEL_ID = null;
    public static String VIEWER_GAMES_CHANNEL_ID = null;
    public static String MOD_CHANNEL_ID = null;
    
    public static String INCITE_USER_ID = null;
    public static String SHER_USER_ID = null;
    
    public static String ONLINE_ROLE_ID = null;
    
    public static void main( String... vargs ) {
        try {
            final InciteBot bot = new InciteBot();
            bot.load();
            
            Runtime.getRuntime().addShutdownHook( new Thread( () ->  { 
                bot.shutdown();
                
            } ) );
            
        } catch ( LoginException | InterruptedException | UnsupportedEncodingException e ) {
            e.printStackTrace();
        
        }
    }
    
}