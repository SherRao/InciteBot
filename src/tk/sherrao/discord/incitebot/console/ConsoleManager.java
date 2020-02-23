package tk.sherrao.discord.incitebot.console;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import org.reflections.Reflections;

import tk.sherrao.discord.incitebot.InciteBot;

public class ConsoleManager implements Runnable {

    private InciteBot bot;
    private Map<String, ConsoleCommand> commands;
    private Scanner console;
    private Reflections commandLoader;

    public ConsoleManager( InciteBot bot ) {
        this.bot = bot;
        this.commands = new HashMap<>();
        this.console = new Scanner( System.in );
        this.commandLoader = new Reflections( "tk.sherrao.discord.incitebot.console.commands" );

    }
    
    public void loadCommands() 
            throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        for( Iterator<Class<? extends ConsoleCommand>> it = commandLoader.getSubTypesOf( ConsoleCommand.class ).iterator(); it.hasNext(); ) {
            Class<? extends ConsoleCommand> next = it.next();
            ConsoleCommand command = next.getDeclaredConstructor( ConsoleManager.class ).newInstance( this );
            commands.put( command.getCommandAsString(), command );
            //log
            
        }
    }
    
    @Override
    public void run() {
        while( true ) {
            String[] splitCommand = console.nextLine().split( " " );
            for( Entry<String, ConsoleCommand> cmd : commands.entrySet() ) {
                if( splitCommand[0].equalsIgnoreCase( cmd.getKey() ) )
                    cmd.getValue().onExecute( splitCommand[0], Arrays.copyOfRange( splitCommand, 1, splitCommand.length ) );
                    
                else {
                    for( String alias : cmd.getValue().getAliases() )
                        if( splitCommand[0].equalsIgnoreCase( alias ) ) 
                            cmd.getValue().onExecute( splitCommand[0], Arrays.copyOfRange( splitCommand, 1, splitCommand.length ) );
                            
                        else continue;
                    
                }
            }
        }
    }
    
    public Map<String, ConsoleCommand> getCommands() {
        return commands;
        
    }
    
    public InciteBot getBot() {
        return bot;
        
    }
    
}