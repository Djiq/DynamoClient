package group.societyproject.dynamoclient.util;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.launchwrapper.LogWrapper;
import net.minecraft.util.text.TextComponentString;
import java.util.List;

public class Helpers {

    public static String emergeWord(String message, boolean rest){

        char[] charArray = message.toCharArray();

        int breakPlace = -1;
        for(int i = 0; i < charArray.length;i++){
            if(charArray[i] == ' '){
                breakPlace = i;
            }
        }

        String actual_command = "";
        String rest_of_command = "";
        if(breakPlace > 0) {
            actual_command = message.substring(0, breakPlace);
            rest_of_command = message.substring(breakPlace+1,message.length());
        } else {
            actual_command = message;
        }
        System.out.println(actual_command);
        if(rest){
            return rest_of_command;
        } else {
            return actual_command;
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
        String message = "[ " + Minecraft.getMinecraft().player.getName() + "@DYNAMO ] ";
        return message;
    }

    public static List getTargetList(){
        EntityPlayer player = Minecraft.getMinecraft().player;
        List<EntityPlayer> player_list =  Minecraft.getMinecraft().world.playerEntities;
        return player_list;
    }

    public static double[] LookAt(double xcord, double ycord, double zcord){
        EntityPlayer player = Minecraft.getMinecraft().player;
        double[] vector = new double[3];

        //normalize the vector
        vector[0] = player.posX - xcord;
        vector[1] = player.posY - ycord;
        vector[2] = player.posZ - zcord;

        double len = Math.sqrt(vector[0]*vector[0] + vector[1]*vector[1] + vector[2]*vector[2] );

        for(int i = 0 ; i < 3; i ++){
            vector[i] /= len;
        }

        //calculate pitch and yaw
        double pitch = Math.asin(vector[1]);
        double yaw = Math.atan2(vector[2], vector[0]);

        //to degree
        pitch = pitch * 180.0d / Math.PI;
        yaw = yaw * 180.0d / Math.PI;

        yaw += 90f;

        return new double[]{yaw,pitch};
    }

}
