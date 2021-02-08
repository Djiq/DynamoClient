package group.societyproject.dynamoclient.command.inbuilt_commands;

import group.societyproject.dynamoclient.command.Command;
import group.societyproject.dynamoclient.command.CommandHandler;
import group.societyproject.dynamoclient.util.Helpers;

public class CBuildCommandList extends Command {


    @Override
    public String getCallname() {
        return "build-cmd";
    }

    @Override
    public String getDescription() {
        return "rebuilds the command list, forcing an update of available commands";
    }


    @Override
    public void digestCommand(String message){
        CommandHandler.getCommandHandler().KillModules();
        CommandHandler.getCommandHandler().BuildCommandList();
        Helpers.sendLocalMessage("Rebuilding Commands");
    }
}
