package group.societyproject.dynamoclient.command.inbuilt_commands.module_commands;

import group.societyproject.dynamoclient.command.Command;
import group.societyproject.dynamoclient.command.Module;
import group.societyproject.dynamoclient.util.Helpers;
import group.societyproject.dynamoclient.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.ArrayList;
import java.util.Comparator;

public class CAutoCrystal extends Command {

    public CrystalAura module;

    @Override
    public String getCallname() {
        return "crystal-aura";
    }

    @Override
    public String getDescription() {
        return "Automatically places and destroys crystals";
    }

    @Override
    public ArrayList<String> getExtensiveDescription() {
        return null;
    }

    @Override
    public void digestCommand(String message) {
        if(module == null){
            module = new CrystalAura();
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

        String word = Helpers.emergeWord(message,false);

        if(word.equals("range")){
            String number_string = Helpers.emergeWord(message,true).replaceAll("[^0-9]","");
            System.out.println("1|" + number_string );
            if(number_string.equals("")){
                return;
            }
            System.out.println("2|" + number_string);
            module.range = Integer.parseInt(number_string);
            Helpers.sendLocalMessage("Crystal aura range is now [" + number_string + "]");
        }


    }

    private class CrystalAura extends Module{

        public int range = 4;


        @SubscribeEvent
        public void onTick(TickEvent.ClientTickEvent event){
            if(event.phase != TickEvent.Phase.START){
                return;
            }

            EntityEnderCrystal some_crystal = Reference.mc.world.loadedEntityList.stream().filter(entity -> entity instanceof EntityEnderCrystal)
                    .map(entity -> (EntityEnderCrystal) entity).min(Comparator.comparing(c -> Reference.mc.player.getDistance(c))).orElse(null);

            EntityPlayer player = Reference.mc.player;

            if(some_crystal != null && player.getDistance(some_crystal) < range){
                //double[] pitchnyaw = LookAt(some_crystal.posX,some_crystal.posY - 2.0d ,some_crystal.posZ);
                //player.rotationYaw = (float) pitchnyaw[0];
                //player.rotationPitch = (float) pitchnyaw[1];


                Reference.mc.playerController.attackEntity(player,some_crystal);
                player.swingArm(EnumHand.MAIN_HAND);
            }
        }



    }
}
