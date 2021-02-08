package group.societyproject.dynamoclient.command.inbuilt_commands.module_commands;

import group.societyproject.dynamoclient.command.Command;
import group.societyproject.dynamoclient.command.Module;
import group.societyproject.dynamoclient.util.Helpers;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.Comparator;

public class CAutoCrystal extends Command {

    public AutoCrystal module;

    @Override
    public String getCallname() {
        return "crystal-aura";
    }

    @Override
    public String getDescription() {
        return "Automatically places and destroys crystals";
    }

    @Override
    public void digestCommand(String message) {
        if(module == null){
            module = new AutoCrystal();
        }

        if(message.equals("")){

            module.toggleState();
            if(module.isState()){
                Helpers.sendLocalMessage("Crystal aura was toggled it is now [on] ");
            } else {
                Helpers.sendLocalMessage("Crystal aura was toggled it is now [off] ");
            }
        }

        if(message.equals("on")){
            module.setState(true);
            Helpers.sendLocalMessage("Crystal aura was turned [on]");
        }

        if(message.equals("off")){
            module.setState(false);
            Helpers.sendLocalMessage("Crystal aura was turned [off]");
        }

        if(message.equals("range")){
            String number_string = Helpers.emergeWord(message,false);
            module.range = Integer.parseInt(number_string);
            Helpers.sendLocalMessage("Crystal aura range is now [" + String.valueOf(module.range) + "]");
        }


    }

    private class AutoCrystal extends Module{

        public int range = 4;


        @SubscribeEvent
        public void onTick(TickEvent.ClientTickEvent event){
            if(event.phase != TickEvent.Phase.START){
                return;
            }

            EntityEnderCrystal some_crystal = Minecraft.getMinecraft().world.loadedEntityList.stream().filter(entity -> entity instanceof EntityEnderCrystal)
                    .map(entity -> (EntityEnderCrystal) entity).min(Comparator.comparing(c -> Minecraft.getMinecraft().player.getDistance(c))).orElse(null);

            EntityPlayer player = Minecraft.getMinecraft().player;

            if(some_crystal != null && player.getDistance(some_crystal) < range){
                //double[] pitchnyaw = LookAt(some_crystal.posX,some_crystal.posY - 2.0d ,some_crystal.posZ);
                //player.rotationYaw = (float) pitchnyaw[0];
                //player.rotationPitch = (float) pitchnyaw[1];


                Minecraft.getMinecraft().playerController.attackEntity(player,some_crystal);
                player.swingArm(EnumHand.MAIN_HAND);
            }
        }



    }
}
