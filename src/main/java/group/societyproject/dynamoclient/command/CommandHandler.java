package group.societyproject.dynamoclient.command;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableMap;
import group.societyproject.dynamoclient.command.inbuilt_commands.CBuildCommandList;
import group.societyproject.dynamoclient.command.inbuilt_commands.CDynamo;
import group.societyproject.dynamoclient.command.inbuilt_commands.CHelp;
import group.societyproject.dynamoclient.command.inbuilt_commands.CListCommands;
import group.societyproject.dynamoclient.util.Helpers;
import group.societyproject.dynamoclient.util.Reference;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.xeustechnologies.jcl.JarClassLoader;
import org.xeustechnologies.jcl.JclObjectFactory;

import java.util.*;

public class CommandHandler {

    public List<Command> getCommandList() {
        return commandList;
    }

    private List<Command> commandList;

    public CommandHandler(){
        super();
        BuildCommandList();
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
        initInbuiltCommands();
        //TODO implement dynamic class loading
        //How this is gonna work
        //First we have to read a manifest file that details the name of the command class to load and the module id
        //We then load that class using JCL
        //We create the object and load it into array
        //Hopefully this is gonna work
        JarClassLoader jcl = new JarClassLoader();
        jcl.add(String.valueOf(Reference.modFolder.resolve("commands")));
        Map<String,Class> jclmap = jcl.getLoadedClasses();
        Set jclset = jclmap.entrySet();
        Iterator itr = jclset.iterator();
        while(itr.hasNext()){
            Map.Entry entry = (Map.Entry)itr.next();
            Object some_class = entry.getValue();
            if(some_class.getClass().isAssignableFrom(Command.class)){
                Command newcommand = (Command)some_class;

            }
        }
    }

    public void initInbuiltCommands(){
        commandList = new LinkedList<Command>();
        commandList.add(new CBuildCommandList(this));
        commandList.add(new CHelp(this));
        commandList.add(new CDynamo(this));
        commandList.add(new CListCommands(this));
    }
}
