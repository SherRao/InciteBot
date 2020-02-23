package tk.sherrao.discord.incitebot;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import tk.sherrao.discord.incitebot.chat.ChatCommandManager;

public class GlobalEventManager extends ListenerAdapter {

    protected InciteBot bot;
    protected ChatCommandManager chatManager;
    protected MessageRegulator messageRegulator;
    
    protected Role newRole;
    protected String prefix;
    protected String cmdChannel, testChannel, fpChannel, gfChannel; 
    
    public GlobalEventManager( InciteBot bot ) {
        this.bot = bot;
        this.chatManager = bot.getChatManager();
        this.messageRegulator = bot.getChatRegulator();
        
        this.newRole = bot.getAPI().getRoleById( "211770188868091905" );
        this.prefix = BotWrapper.CHAT_PREFIX;
        this.cmdChannel = BotWrapper.COMMANDS_CHANNEL_ID;
        this.testChannel = BotWrapper.TEST_CHANNEL_ID;
        this.fpChannel = BotWrapper.FOOD_PORN_CHANNEL_ID;
        this.gfChannel = BotWrapper.GROUP_FINDER_CHANNEL_ID;
        
    }
    
    @Override
    public void onGuildMessageReceived( GuildMessageReceivedEvent event ) {
        Message message = event.getMessage();
        TextChannel channel = event.getChannel();
        Member author = event.getMember();
        String raw = message.getContentRaw();
        if( event.getAuthor().isBot() )
            return;
            
        else if( raw.startsWith( prefix ) && ( channel.getId().equals( cmdChannel ) || channel.getId().equals( testChannel ) ) )
            chatManager.onCommandExecuted( author, channel, raw );
            
        else if( channel.getId().equals( fpChannel ) )
            messageRegulator.onFoodPornMessage( author, message );
            
        else return;
        
    }
    
    @Override
    public void onGuildMemberJoin( GuildMemberJoinEvent event ) {
        Member member = event.getMember();
        member.getGuild().getController().addSingleRoleToMember( member, newRole );
        
    }
    
    public InciteBot getBot() {
        return bot;
        
    }

}