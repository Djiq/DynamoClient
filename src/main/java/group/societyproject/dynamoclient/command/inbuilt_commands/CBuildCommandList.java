package group.societyproject.dynamoclient.command.inbuilt_commands;

import group.societyproject.dynamoclient.command.Command;
import group.societyproject.dynamoclient.command.CommandHandler;
import group.societyproject.dynamoclient.util.Helpers;

public class CBuildCommandList extends Command {

    public CBuildCommandList(CommandHandler handler){
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

    public String callname = "build-cmd";

    public String description = "rebuilds the command list, forcing an update of available commands";

    @Override
    public void digestCommand(String message){
        parentHandler.BuildCommandList();
        Helpers.sendLocalMessage("Rebuilding Commands");
    }
}
