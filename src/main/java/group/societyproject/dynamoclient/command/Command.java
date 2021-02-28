package group.societyproject.dynamoclient.command;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;

import java.util.ArrayList;

abstract public class Command {

    abstract public String getCallname();

    abstract public String getDescription() ;

    abstract public ArrayList<String> getExtensiveDescription();

    abstract public void digestCommand(String message);

}
