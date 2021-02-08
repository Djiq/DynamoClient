package group.societyproject.dynamoclient.command.inbuilt_commands.module_commands;

import group.societyproject.dynamoclient.command.Command;
import group.societyproject.dynamoclient.command.CommandHandler;
import group.societyproject.dynamoclient.command.Module;
import group.societyproject.dynamoclient.util.Helpers;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class CSprint extends Command {

    private ModuleSprint module;

    @Override
    public String getCallname() {
        return callname;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public String callname = "sprint";

    public String description = "toggles sprint, type just '=sprint' to toggle it, '=sprint true' to force it on, and 'sprint false' to force it off";

    @Override
    public void digestCommand(String message){
        if(module == null){
            module = new ModuleSprint();
        }

        if(message.equals("")){
            Helpers.sendLocalMessage("Toggled auto-sprint");
            module.setState(!module.isState());
            return;
        }

        String word = Helpers.emergeWord(message,false);

        if(word.equals("true")){
            Helpers.sendLocalMessage("Auto-sprint toggled on");
            module.setState(true);
            return;
        }

        if(word.equals("false")){
            Helpers.sendLocalMessage("Auto-sprint toggled off");
            module.setState(false);
            return;
        }

    }

    public class ModuleSprint extends Module {

        @SubscribeEvent
        public void onPlayerUpdate(TickEvent.PlayerTickEvent event){
            try{
                if(!event.player.collidedHorizontally){
                    event.player.setSprinting(true);
                } else {
                    event.player.setSprinting(false);
                }
            } catch (Exception ignored) {
            }
        }

    }
}