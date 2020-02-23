package tk.sherrao.discord.incitebot.chat.commands;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;
import tk.sherrao.discord.incitebot.chat.ChatCommand;
import tk.sherrao.discord.incitebot.chat.ChatCommandManager;

public class QuoteCommand extends ChatCommand {

    private File file;
    private BufferedReader input;
    private BufferedWriter output;
    
    private Random random;
    private List<String> quotes;
    
    public QuoteCommand( ChatCommandManager manager ) {
        super( manager, "quotes", "quote", "saying" );
        
        try {
            this.file = new File( manager.getBot().getWorkingDirectory(), "quotes.txt" );
            if( !file.exists() ) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                output = new BufferedWriter( new FileWriter( file ) );
                output.write( "I like holes" );
                output.close();
                
            }
            
            this.input = Files.newBufferedReader( Paths.get( file.getPath() ) );
            this.random = new Random();
            this.quotes = input.lines().collect( Collectors.toList() );
            
        } catch ( IOException e ) { e.printStackTrace(); }
    
    }

    @Override
    public void onExecute( Member author, TextChannel channel, String alias, String[] args ) {
        channel.sendMessage( author.getAsMention() + ", A wise Swede once said _'" + getQuote() + "'_" ).complete();
        
    }
    
    private String getQuote() {
        return quotes.get( random.nextInt( quotes.size() ) );
        
    }
    
    
}
