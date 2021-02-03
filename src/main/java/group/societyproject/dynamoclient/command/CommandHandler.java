package group.societyproject.dynamoclient.command;

import group.societyproject.dynamoclient.command.inbuilt_commands.CBuildCommandList;
import group.societyproject.dynamoclient.command.inbuilt_commands.CDynamo;
import group.societyproject.dynamoclient.command.inbuilt_commands.CHelp;
import group.societyproject.dynamoclient.command.inbuilt_commands.CListCommands;
import group.societyproject.dynamoclient.util.Helpers;
import group.societyproject.dynamoclient.util.Reference;
import net.minecraftforge.client.event.ClientChatEvent;
import org.xeustechnologies.jcl.JarClassLoader;

import java.util.LinkedList;
import java.util.List;

public class CommandHandler {

    public List<Command> getCommandList() {
        return commandList;
    }

    private List<Command> commandList;

    public CommandHandler(){
        super();
        BuildCommandList();
    }

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
        initInbuiltCommands();
        //TODO implement dynamic class loading
        JarClassLoader jcl = new JarClassLoader();
        jcl.add(String.valueOf(Reference.modFolder.resolve("commands"));


    }

    public void initInbuiltCommands(){
        commandList = new LinkedList<Command>();
        commandList.add(new CBuildCommandList(this));
        commandList.add(new CHelp(this));
        commandList.add(new CDynamo(this));
        commandList.add(new CListCommands(this));
    }
}
