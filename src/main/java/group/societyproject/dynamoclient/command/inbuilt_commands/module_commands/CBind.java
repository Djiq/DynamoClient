package group.societyproject.dynamoclient.command.inbuilt_commands.module_commands;

import group.societyproject.dynamoclient.command.Command;
import group.societyproject.dynamoclient.command.Module;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static group.societyproject.dynamoclient.util.Helpers.emergeWord;
import static group.societyproject.dynamoclient.util.Helpers.sendLocalMessage;
import static group.societyproject.dynamoclient.util.Helpers.sendMessageAsPlayer;

public class CBind extends Command {

    public CBind() {
        super();
        bindManager = new BindManager();
        bindManager.setState(true);
    }

    BindManager bindManager;

    @Override
    public String getCallname() {
        return "bind";
    }

    @Override
    public String getDescription() {
        return "Allows you to bind any key to any chat input, =bind [key] [message]. for example =bind x =sprint";
    }

    @Override
    public ArrayList<String> getExtensiveDescription() {
        return null;
    }

    @Override
    public void digestCommand(String message) {

        if(message.equals("")){
            return;
        }

        String comm = emergeWord(message,false);
        if(comm.equals("")){
            return;
        }

        char[] some_char_arr = emergeWord(emergeWord(message, true),false).toCharArray();

        if(some_char_arr.length == 0){
            sendLocalMessage("Bind error: No character detected, use syntax =bind <func> <key> <msg>");
            return;
        }
        if(some_char_arr.length > 1){
            sendLocalMessage("Bind error: Illegal character, use only single characters, such us u, r, d, etc.");
            return;
        }

        char key_char = some_char_arr[0];

        if(comm.equals("set")){
            String some_rest = emergeWord(emergeWord(message, true),true);
            bindManager.addBind(key_char,some_rest);
            return;
        }
        if(comm.equals("del")){
            bindManager.removeBind(key_char);
            return;

        }

    }

    private class BindManager extends Module{

        HashMap<String, String> bound_keys;

        public BindManager() {
            this.bound_keys = new HashMap<String, String>();
        }

        public void addBind(char key, String sequence){

            String string_key = ("" + key).toUpperCase();
            if (!bound_keys.getOrDefault(string_key,"").equals("")) {
                _removeBind(string_key);
            }
            _addBind(string_key,sequence);
        }

        public void removeBind(char key){
            _removeBind("" + key);
        }


        private void _addBind(String key, String sequence){
            bound_keys.put(key,sequence);
        }

        private void _removeBind(String key){
            bound_keys.remove(key);
        }

        @SubscribeEvent
        public void onKeyPress(InputEvent.KeyInputEvent event) {
            for(String bind : bound_keys.keySet()){
                if(Keyboard.isKeyDown(Keyboard.getKeyIndex(bind))){
                    String response = bound_keys.get(bind);
                    sendMessageAsPlayer(response);
                }
            }
        }

    }


}
