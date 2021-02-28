package group.societyproject.dynamoclient.command.inbuilt_commands;

import group.societyproject.dynamoclient.command.Command;
import group.societyproject.dynamoclient.command.CommandHandler;
import group.societyproject.dynamoclient.util.Helpers;

import java.util.ArrayList;

public class CListCommands extends Command {

    @Override
    public String getCallname() {
        return "lsc";
    }

    @Override
    public String getDescription() {
        return "lists all registered commands, if something doesn't show after installing it, try build-cmd to rebuild all commands.";
    }

    @Override
    public ArrayList<String> getExtensiveDescription() {
        return null;
    }

    @Override
    public void digestCommand(String message){
        int i = 0;
        for(Command com : CommandHandler.getCommandHandler().getCommandList() ){
            i++;
            Helpers.sendLocalMessage("[ " + i + " ] " + com.getCallname());
        }
    }
}

