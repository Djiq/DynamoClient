package group.societyproject.dynamoclient;

import group.societyproject.dynamoclient.command.CommandHandler;
import group.societyproject.dynamoclient.util.Reference;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventHandler {

    public static CommandHandler commands;

    public EventHandler() {
        commands = new CommandHandler();
    }

    @SubscribeEvent
    public void ClientChatMessage(ClientChatEvent event){
        commands.ClientChatMessage(event);
    }

}
