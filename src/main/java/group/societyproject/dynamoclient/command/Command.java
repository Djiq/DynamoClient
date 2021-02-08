package group.societyproject.dynamoclient.command;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;

abstract public class Command {

    abstract public String getCallname();

    abstract public String getDescription() ;

    abstract public void digestCommand(String message);

}
