package group.societyproject.dynamoclient.util;

import group.societyproject.dynamoclient.util.filters.BlocksListFilter;
import group.societyproject.dynamoclient.util.primitives.Primitive3D;
import jdk.nashorn.internal.ir.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.launchwrapper.LogWrapper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

import java.lang.reflect.Array;
import java.util.ArrayList;
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
        if (Reference.mc.player != null) {
            Reference.mc.player.sendMessage(new TextComponentString(message));

        } else {
            LogWrapper.info(message);
        }
    }

    public static void sendLocalMessage(String message){
        sendRawMessage(getDynamoPrefix() + message);
    }

    public static String getDynamoPrefix() {
        String message = TextFormatting.BLUE +  "[ " + Reference.mc.player.getName() + "@DYNAMO ] ";
        return message;
    }

    public static List getTargetList(){
        EntityPlayer player = Reference.mc.player;
        List<EntityPlayer> player_list =  Reference.mc.world.playerEntities;
        return player_list;
    }

    public static double[] LookAt(double xcord, double ycord, double zcord){
        EntityPlayer player = Reference.mc.player;
        double[] vector = {player.posX - xcord,player.posY - ycord,player.posZ - zcord};

        double len = Math.sqrt(vector[0]*vector[0] + vector[1]*vector[1] + vector[2]*vector[2] );

        for(int i = 0 ; i < 3; i ++){
            vector[i] /= len;
        }

        double pitch = Math.asin(vector[1]) * 180.0d / Math.PI;
        double yaw = Math.atan2(vector[2], vector[0]) * 180.0d / Math.PI + 90f;

        return new double[]{yaw,pitch};
    }

    public static ArrayList<BlockPos> GetBlocksInPrimitive(Primitive3D some_primitive){
        int reach = some_primitive.getFurthestReach();
        int fullReach = reach * 2;
        Minecraft localMc = Reference.mc;
        ArrayList<BlockPos> BlockArr = new ArrayList<BlockPos>();

        BlockPos centre = some_primitive.getCenter();
        BlockPos startPos = new BlockPos(centre.getX() - reach,centre.getY() - reach,centre.getZ() - reach );
        BlockPos endPos = new BlockPos(centre.getX() + reach,centre.getY() + reach,centre.getZ() + reach );

        for(int xiter = startPos.getX() ; xiter < endPos.getX() ; xiter++){

            for(int yiter = startPos.getY() ; yiter < endPos.getY() ; yiter++){

                for(int ziter = startPos.getZ() ; ziter < endPos.getZ() ; ziter++){

                    BlockPos some_block = new BlockPos(xiter,yiter,ziter);
                    if(some_primitive.isInPrimitive3D(some_block)){
                        BlockArr.add(some_block);
                    }
                }
            }
        }

        return BlockArr;
    }

    public static ArrayList<BlockPos>  FilterBlocksArrayList(ArrayList<BlockPos> blocksList, BlocksListFilter filter){
        ArrayList<BlockPos> newList = new ArrayList<BlockPos>();
        for(BlockPos pos : blocksList){
            if(filter.Filter(pos)){
                newList.add(pos);
            }
        }

        return newList;
    }

    //I have no clue how to access this function located in GuiScreen, until then this sits here.
    public static void sendMessageAsPlayer(String msg) {

       msg = net.minecraftforge.event.ForgeEventFactory.onClientSendMessage(msg);
        if (msg.isEmpty()) return;

        Reference.mc.ingameGUI.getChatGUI().addToSentMessages(msg);

        if (net.minecraftforge.client.ClientCommandHandler.instance.executeCommand(Reference.mc.player, msg) != 0) return;

        Reference.mc.player.sendChatMessage(msg);
    }


}
