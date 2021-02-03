package group.societyproject.dynamoclient.command.inbuilt_commands;

import group.societyproject.dynamoclient.command.Command;
import group.societyproject.dynamoclient.command.CommandHandler;
import group.societyproject.dynamoclient.util.Helpers;

public class CListCommands extends Command {
    public CListCommands(CommandHandler handler){
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

    public String callname = "lsc";

    public String description = "lists all registered commands, if something doesn't show after installing it, try build-cmd to rebuild all commands.";

    @Override
    public void digestCommand(String message){
        int i = 0;
        for(Command com : parentHandler.getCommandList() ){
            i++;
            Helpers.sendLocalMessage("[ " + i + " ] " + com.getCallname());
        }
    }
}

