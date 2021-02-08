package group.societyproject.dynamoclient.command;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableMap;
import group.societyproject.dynamoclient.command.inbuilt_commands.CBuildCommandList;
import group.societyproject.dynamoclient.command.inbuilt_commands.CDynamo;
import group.societyproject.dynamoclient.command.inbuilt_commands.CHelp;
import group.societyproject.dynamoclient.command.inbuilt_commands.CListCommands;
import group.societyproject.dynamoclient.command.inbuilt_commands.module_commands.CAutoCrystal;
import group.societyproject.dynamoclient.command.inbuilt_commands.module_commands.CSprint;
import group.societyproject.dynamoclient.events.EventKillModules;
import group.societyproject.dynamoclient.util.Helpers;
import group.societyproject.dynamoclient.util.Reference;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.xeustechnologies.jcl.JarClassLoader;
import org.xeustechnologies.jcl.JclObjectFactory;

import java.util.*;

public class CommandHandler {
    private List<Command> commandList;

    private static CommandHandler instance;

    public CommandHandler(){
        super();
        BuildCommandList();
        instance = this;

    }

    public static CommandHandler getCommandHandler() {
        return instance;
    }

    public List<Command> getCommandList() {
        return commandList;
    }

    @SubscribeEvent
    public void ClientChatMessage(ClientChatEvent event){
        String local_message = event.getOriginalMessage();
        if(local_message.startsWith(Reference.commandPrefix)){
            event.setCanceled(true);
            ParseMessage(local_message);
            return;
        }
    }

    public void ParseMessage(String message){
        message = message.replaceFirst(Reference.commandPrefix ,"" );

        if(message.length() < 1){
            return ;
        }

        String word =  Helpers.emergeWord(message,false);

        for(Command com : commandList ){

            if(com.getCallname().equals(word)){

                com.digestCommand(Helpers.emergeWord(message,true));
            }
        }
    }

    public void BuildCommandList(){
        commandList = new LinkedList<Command>();
        commandList.add(new CBuildCommandList());
        commandList.add(new CHelp());
        commandList.add(new CDynamo());
        commandList.add(new CListCommands());
        commandList.add(new CAutoCrystal());
        commandList.add(new CSprint());
    }

    public void KillModules(){
        MinecraftForge.EVENT_BUS.post(new EventKillModules());
    }

}
