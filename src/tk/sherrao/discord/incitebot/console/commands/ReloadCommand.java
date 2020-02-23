package tk.sherrao.discord.incitebot.console.commands;

import tk.sherrao.discord.incitebot.console.ConsoleCommand;
import tk.sherrao.discord.incitebot.console.ConsoleManager;

public class ReloadCommand extends ConsoleCommand {

    public ReloadCommand( ConsoleManager manager ) {
        super( manager, "reload", "load" );
        
    }

    @Override
    public void onExecute( String alias, String[] args ) {
        System.exit( 0 );
        
    }

}
