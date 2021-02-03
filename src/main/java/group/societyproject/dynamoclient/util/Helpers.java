package group.societyproject.dynamoclient.util;

import net.minecraft.client.Minecraft;
import net.minecraft.launchwrapper.LogWrapper;
import net.minecraft.util.text.TextComponentString;

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
}
