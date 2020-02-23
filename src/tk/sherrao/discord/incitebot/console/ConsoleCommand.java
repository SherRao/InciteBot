package tk.sherrao.discord.incitebot.console;

import java.util.Arrays;
import java.util.List;

public abstract class ConsoleCommand {

    protected final ConsoleManager manager;
    private final String command;
    private final List<String> alises;
    
    public ConsoleCommand( ConsoleManager manager, String command, String... aliases ) {
        this.manager = manager;
        this.command = command;
        this.alises = Arrays.asList( aliases );
        
    }
    
    public abstract void onExecute( String alias, String[] args);
    
    public String getCommandAsString() {
        return command;
        
    }
    
    public List<String> getAliases() {
        return alises;
        
    }
    
    public boolean isAlias( String alias ) {
        return alises.contains( alias );
        
    }
    
}