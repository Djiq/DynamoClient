package group.societyproject.dynamoclient.command.inbuilt_commands;

import group.societyproject.dynamoclient.command.Command;
import group.societyproject.dynamoclient.command.CommandHandler;
import group.societyproject.dynamoclient.util.Helpers;

public class CDynamo extends Command {
    public CDynamo(CommandHandler handler){
        super(handler);
    }

    @Override
    public String getCallname() {
        return callname;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public String callname = "dynamo";

    public String description = "Prints DYNAMO to screen";

    @Override
    public void digestCommand(String message){
        Helpers.sendRawMessage("O-------------------------O");
        Helpers.sendRawMessage("|  ##--#-#-###--#--#-#--#-  |");
        Helpers.sendRawMessage("|  #-#-#-#-#-#-#-#-###-#-#  |");
        Helpers.sendRawMessage("|  #-#--#--#-#-###-###-#-#  |");
        Helpers.sendRawMessage("|  #-#--#--#-#-#-#-#-#-#-#  |");
        Helpers.sendRawMessage("|  ##---#--#-#-#-#-#-#--#-  |");
        Helpers.sendRawMessage("O-------------------------O");
    }
}

