package group.societyproject.dynamoclient.command.inbuilt_commands;

import group.societyproject.dynamoclient.command.Command;
import group.societyproject.dynamoclient.command.CommandHandler;
import group.societyproject.dynamoclient.util.Helpers;
import net.minecraft.client.Minecraft;
import net.minecraft.launchwrapper.LogWrapper;
import net.minecraft.util.text.TextComponentString;
import scala.collection.parallel.ParIterableLike;

public class CHelp extends Command {


    @Override
    public String getCallname() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Shows you the description of the command";
    }

    public void digestCommand(String message){
        if(message.equals("")){
            Helpers.sendLocalMessage( "Type =lsc to list all commands, =help <command> to get it's description.");
            return;
        }
        String word =  Helpers.emergeWord(message,false);
        System.out.println("help  :" + word + "|");
        for(Command com : CommandHandler.getCommandHandler().getCommandList() ){
            if(com.getCallname().equals(word)){
               Helpers.sendLocalMessage( com.getCallname() + " : " + com.getDescription());
               return;
            }
        }

    }

}
