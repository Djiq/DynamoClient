package group.societyproject.dynamoclient.command.inbuilt_commands.module_commands;

import group.societyproject.dynamoclient.command.Command;
import group.societyproject.dynamoclient.command.Module;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;


import java.util.ArrayList;

public class CAutoCrystalPlace extends Command {
    @Override
    public String getCallname() {
        return "crystal-auto-place";
    }

    @Override
    public String getDescription() {
        return "automatically places crystals to damage your opponent.";
    }

    @Override
    public ArrayList<String> getExtensiveDescription() {
        return null;
    }

    @Override
    public void digestCommand(String message) {

    }


    class AutoCrystal extends Module{

        @SubscribeEvent
        public void onTick(TickEvent.ClientTickEvent event){
            Minecraft localMc = Minecraft.getMinecraft();

        }
    }

}
