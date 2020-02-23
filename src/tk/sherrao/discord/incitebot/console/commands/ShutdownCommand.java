package tk.sherrao.discord.incitebot.console.commands;

import tk.sherrao.discord.incitebot.console.ConsoleCommand;
import tk.sherrao.discord.incitebot.console.ConsoleManager;

public class ShutdownCommand extends ConsoleCommand {

    public ShutdownCommand( ConsoleManager manager ) {
        super( manager, "shutdown", "stop", "quit", "exit" );
        
    }

    @Override
    public void onExecute( String alias, String[] args ) {
        System.exit( 0 );
        
    }

}
