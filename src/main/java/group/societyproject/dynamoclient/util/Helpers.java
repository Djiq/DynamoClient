package group.societyproject.dynamoclient.util;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.launchwrapper.LogWrapper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

import java.util.List;

public class Helpers {

    public static String emergeWord(String message, boolean rest){
        int place = message.indexOf(' ');
        String retunrmsg;

        if(place > 0){
            if(rest){
                retunrmsg =  message.substring(place+1);
            } else {

                retunrmsg = message.substring(0,place);
            }
            System.out.println(retunrmsg);
            return retunrmsg;
        } else {
            if(rest){
                return "";
            } else {
                return message;
            }
        }


    }

    public static void sendRawMessage(String message){
        if (Minecraft.getMinecraft().player != null) {
            Minecraft.getMinecraft().player.sendMessage(new TextComponentString(message));
        } else {
            LogWrapper.info(message);
        }
    }

    public static void sendLocalMessage(String message){
        sendRawMessage(getDynamoPrefix() + message);
    }

    public static String getDynamoPrefix() {
        String message = TextFormatting.BLUE +  "[ " + Minecraft.getMinecraft().player.getName() + "@DYNAMO ] ";
        return message;
    }

    public static List getTargetList(){
        EntityPlayer player = Minecraft.getMinecraft().player;
        List<EntityPlayer> player_list =  Minecraft.getMinecraft().world.playerEntities;
        return player_list;
    }

    public static double[] LookAt(double xcord, double ycord, double zcord){
        EntityPlayer player = Minecraft.getMinecraft().player;
        double[] vector = {player.posX - xcord,player.posY - ycord,player.posZ - zcord};

        double len = Math.sqrt(vector[0]*vector[0] + vector[1]*vector[1] + vector[2]*vector[2] );

        for(int i = 0 ; i < 3; i ++){
            vector[i] /= len;
        }

        double pitch = Math.asin(vector[1]) * 180.0d / Math.PI;
        double yaw = Math.atan2(vector[2], vector[0]) * 180.0d / Math.PI + 90f;

        return new double[]{yaw,pitch};
    }

}
