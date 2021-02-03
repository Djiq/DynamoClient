package group.societyproject.dynamoclient.command;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;

public class Command {

    public Command(CommandHandler handler){
        super();
        parentHandler = handler;
    }
    protected CommandHandler parentHandler;

    public String getCallname() {
        return "";
    }

    public String getDescription() {
        return "";
    }


    public void digestCommand(String message){
        System.out.println("parent");
    }

}
