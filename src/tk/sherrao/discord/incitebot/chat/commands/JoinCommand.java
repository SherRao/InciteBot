package tk.sherrao.discord.incitebot.chat.commands;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;
import tk.sherrao.discord.incitebot.BotWrapper;
import tk.sherrao.discord.incitebot.ViewerGameManager;
import tk.sherrao.discord.incitebot.chat.ChatCommand;
import tk.sherrao.discord.incitebot.chat.ChatCommandManager;

public class JoinCommand extends ChatCommand {
    
    private ViewerGameManager viewergames;
    
    public JoinCommand( ChatCommandManager manager ) {
        super( manager, "join" );
        
        this.viewergames = manager.getBot().getViewerGamesManager();
        
    }

    @Override
    public void onExecute( Member author, TextChannel channel, String alias, String[] args ) {
        if( channel.getId().equals( BotWrapper.TEST_CHANNEL_ID ) ) {
            if( !viewergames.isInQueue( author ) ) {
                viewergames.add( author );
                
            } else return;
            
        } else return;
        
    }
    
}