package tk.sherrao.discord.incitebot.chat;

import java.util.Arrays;
import java.util.List;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;

public abstract class ChatCommand {

    protected final ChatCommandManager manager;
    private final String command;
    private final List<String> alises;
    
    public ChatCommand( ChatCommandManager manager, String command, String... aliases ) {
        this.manager = manager;
        this.command = command;
        this.alises = Arrays.asList( aliases );
        
    }
    
    public abstract void onExecute( Member author, TextChannel channel, String alias, String[] args);
    
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