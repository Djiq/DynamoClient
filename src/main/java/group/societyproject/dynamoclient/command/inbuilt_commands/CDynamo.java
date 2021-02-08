package group.societyproject.dynamoclient.command.inbuilt_commands;

import group.societyproject.dynamoclient.command.Command;
import group.societyproject.dynamoclient.command.CommandHandler;
import group.societyproject.dynamoclient.util.Helpers;

public class CDynamo extends Command {

    @Override
    public String getCallname() {
        return "dynamo";
    }

    @Override
    public String getDescription() {
        return "Prints DYNAMO to screen";
    }

    public void digestCommand(String message){
        Helpers.sendRawMessage("O------------------------------O");
        Helpers.sendRawMessage("|  ##---#-#--###---#---#-#---#-  |");
        Helpers.sendRawMessage("|  #-#--#-#--#-#--#-#--###--#-#  |");
        Helpers.sendRawMessage("|  #-#---#---#-#--###--###--#-#  |");
        Helpers.sendRawMessage("|  #-#---#---#-#--#-#--#-#--#-#  |");
        Helpers.sendRawMessage("|  ##----#---#-#--#-#--#-#---#-  |");
        Helpers.sendRawMessage("O------------------------------O");
    }
}

