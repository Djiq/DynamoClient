package group.societyproject.dynamoclient.command;

import group.societyproject.dynamoclient.command.inbuilt_commands.CBuildCommandList;
import group.societyproject.dynamoclient.command.inbuilt_commands.CDynamo;
import group.societyproject.dynamoclient.command.inbuilt_commands.CHelp;
import group.societyproject.dynamoclient.command.inbuilt_commands.CListCommands;
import group.societyproject.dynamoclient.command.inbuilt_commands.module_commands.CAutoCrystal;
import group.societyproject.dynamoclient.command.inbuilt_commands.module_commands.CBind;
import group.societyproject.dynamoclient.command.inbuilt_commands.module_commands.CSprint;
import group.societyproject.dynamoclient.events.EventKillModules;
import group.societyproject.dynamoclient.util.Helpers;
import group.societyproject.dynamoclient.util.Reference;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

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
            Reference.mc.ingameGUI.getChatGUI().addToSentMessages(local_message);
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
        commandList.add(new CBind());
    }

    public void KillModules(){
        MinecraftForge.EVENT_BUS.post(new EventKillModules());
    }

}
