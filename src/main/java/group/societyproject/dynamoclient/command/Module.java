package group.societyproject.dynamoclient.command;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;


//Modular nameless method holder, implement inside of [Command] for proper use
public class Module {

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
        if(state){
            onEnabled();
        } else {
            onDisable();
        }
    }

    ///wether it is on or off
    protected boolean state = false;

    protected void onEnabled() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    protected void onDisable() {
        MinecraftForge.EVENT_BUS.unregister(this);
    }


}
