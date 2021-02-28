package group.societyproject.dynamoclient.command.inbuilt_commands.module_commands;

import group.societyproject.dynamoclient.command.Command;
import group.societyproject.dynamoclient.command.Module;
import group.societyproject.dynamoclient.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
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
            Minecraft localMc = Reference.mc;

        }

        public float calcDamage(Entity some_entity, BlockPos some_position){
            double distance = some_entity.getDistance(some_position.getX(),some_position.getY() + 2,some_position.getZ());
            float dExplSize = 12.0f;




            return 0.0f;
        }


        //Damage calculation directly ripped from mc source code, with some of the confusing d* variables renames to more appropriate names.

        /*
        public void someStuff(){
            for (int k2 = 0; k2 < list.size(); ++k2)
            {
                Entity entity = list.get(k2);

                if (!entity.isImmuneToExplosions())
                {
                    double distance = entity.getDistance(this.x, this.y, this.z) / (double)f3;

                    if (distance <= 1.0D)
                    {
                        double distx = entity.posX - this.x;
                        double disty = entity.posY + (double)entity.getEyeHeight() - this.y;
                        double distz = entity.posZ - this.z;
                        double d13 = (double) MathHelper.sqrt(d5 * d5 + d7 * d7 + d9 * d9);

                        if (d13 != 0.0D)
                        {
                            //Normalize the vectors
                            distx = distx / d13;
                            disty = disty / d13;
                            distz = distz / d13;
                            double d14 = (double)this.world.getBlockDensity(vec3d, entity.getEntityBoundingBox());
                            double d10 = (1.0D - distance) * d14;
                            entity.attackEntityFrom(DamageSource.causeExplosionDamage(this), (float)((int)((d10 * d10 + d10) / 2.0D * 7.0D * (double)f3 + 1.0D)));
                            double d11 = d10;

                            if (entity instanceof EntityLivingBase)
                            {
                                d11 = EnchantmentProtection.getBlastDamageReduction((EntityLivingBase)entity, d10);
                            }

                            entity.motionX += distx * d11;
                            entity.motionY += disty * d11;
                            entity.motionZ += distz * d11;

                            if (entity instanceof EntityPlayer)
                            {
                                EntityPlayer entityplayer = (EntityPlayer)entity;

                                if (!entityplayer.isSpectator() && (!entityplayer.isCreative() || !entityplayer.capabilities.isFlying))
                                {
                                    this.playerKnockbackMap.put(entityplayer, new Vec3d(d5 * d10, d7 * d10, d9 * d10));
                                }
                            }
                        }
                    }
                }
            }
        } */


    }

}
