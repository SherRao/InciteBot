package tk.sherrao.discord.incitebot.chat;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.reflections.Reflections;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;
import tk.sherrao.discord.incitebot.BotWrapper;
import tk.sherrao.discord.incitebot.InciteBot;

public class ChatCommandManager  {
    
    private InciteBot bot;
    private Map<String, ChatCommand> commands;
    private Reflections commandLoader;
    
    public ChatCommandManager( InciteBot bot ) {
        this.bot = bot;
        this.commands = new HashMap<>();
        this.commandLoader = new Reflections( "tk.sherrao.discord.incitebot.chat.commands" );
        
    }
    
    public void loadCommands() 
            throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        for( Iterator<Class<? extends ChatCommand>> it = commandLoader.getSubTypesOf( ChatCommand.class ).iterator(); it.hasNext(); ) {
            Class<? extends ChatCommand> next = it.next();
            if( next.getSimpleName().equals( "InactiveCommand" ) || next.getSimpleName().equals( "NightbotCommand" ) )
                continue;
            
            ChatCommand command = next.getDeclaredConstructor( ChatCommandManager.class ).newInstance( this );
            commands.put( command.getCommandAsString(), command );
            //log
            
        }
    }
    
    public void onCommandExecuted( Member executor, TextChannel channel, String command ) {
        String[] splitCommand = command.replaceFirst( BotWrapper.CHAT_PREFIX, "" ).split( " " );
        for( Entry<String, ChatCommand> cmd : commands.entrySet() ) {
            if( splitCommand[0].equalsIgnoreCase( cmd.getKey() ) )
                cmd.getValue().onExecute( executor, channel, splitCommand[0], Arrays.copyOfRange( splitCommand, 1, splitCommand.length ) );
                
            else {
                for( String alias : cmd.getValue().getAliases() )
                    if( splitCommand[0].equalsIgnoreCase( alias ) ) 
                        cmd.getValue().onExecute( executor, channel, splitCommand[0], Arrays.copyOfRange( splitCommand, 1, splitCommand.length ) );
                        
                    else continue;
                
            }
        }
    }

    public Map<String, ChatCommand> getCommands() {
        return commands;
        
    }
    
    public InciteBot getBot() {
        return bot;
        
    }
    
}