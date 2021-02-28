package group.societyproject.dynamoclient.command.inbuilt_commands;

import group.societyproject.dynamoclient.command.Command;
import group.societyproject.dynamoclient.command.CommandHandler;
import group.societyproject.dynamoclient.util.Helpers;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;

public class CDynamo extends Command {

    @Override
    public String getCallname() {
        return "dynamo";
    }

    @Override
    public String getDescription() {
        return "Prints DYNAMO to screen";
    }

    @Override
    public ArrayList<String> getExtensiveDescription() {
        return null;
    }



    public void digestCommand(String message){
        Helpers.sendRawMessage( TextFormatting.BLUE +"O------------------------------O");
        Helpers.sendRawMessage( TextFormatting.BLUE +"|  ##---#-#--###---#---#-#---#-  |");
        Helpers.sendRawMessage( TextFormatting.BLUE +"|  #-#--#-#--#-#--#-#--###--#-#  |");
        Helpers.sendRawMessage( TextFormatting.BLUE +"|  #-#---#---#-#--###--###--#-#  |");
        Helpers.sendRawMessage( TextFormatting.BLUE +"|  #-#---#---#-#--#-#--#-#--#-#  |");
        Helpers.sendRawMessage( TextFormatting.BLUE +"|  ##----#---#-#--#-#--#-#---#-  |");
        Helpers.sendRawMessage( TextFormatting.BLUE +"O------------------------------O");
    }
}

