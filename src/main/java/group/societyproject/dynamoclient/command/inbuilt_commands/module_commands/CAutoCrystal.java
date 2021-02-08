package group.societyproject.dynamoclient.command.inbuilt_commands.module_commands;

import group.societyproject.dynamoclient.command.Command;
import group.societyproject.dynamoclient.command.Module;
import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import scala.collection.parallel.ParIterableLike;

import java.util.Comparator;

import static group.societyproject.dynamoclient.util.Helpers.LookAt;

public class CAutoCrystal extends Command {

    public AutoCrystal module;

    @Override
    public String getCallname() {
        return "CrystalAura";
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

        module.toggleState();

    }

    private class AutoCrystal extends Module{

        public int range = 4;

        private long lastAttack = -1;

        @SubscribeEvent
        public void onTick(TickEvent.ClientTickEvent event){
            if(event.phase != TickEvent.Phase.START){
                return;
            }

            EntityEnderCrystal some_crystal = Minecraft.getMinecraft().world.loadedEntityList.stream().filter(entity -> entity instanceof EntityEnderCrystal)
                    .map(entity -> (EntityEnderCrystal) entity).min(Comparator.comparing(c -> Minecraft.getMinecraft().player.getDistance(c))).orElse(null);

            EntityPlayer player = Minecraft.getMinecraft().player;

            if(some_crystal != null && player.getDistance(some_crystal) < range && ((System.nanoTime() / 1000000) - lastAttack ) >= 250 ){
                //double[] pitchnyaw = LookAt(some_crystal.posX,some_crystal.posY - 2.0d ,some_crystal.posZ);
                //player.rotationYaw = (float) pitchnyaw[0];
                //player.rotationPitch = (float) pitchnyaw[1];


                Minecraft.getMinecraft().playerController.attackEntity(player,some_crystal);
                player.swingArm(EnumHand.MAIN_HAND);
                lastAttack = System.nanoTime() / 1000000;
            }
        }



    }
}
