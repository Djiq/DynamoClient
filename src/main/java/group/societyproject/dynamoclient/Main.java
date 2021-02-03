package group.societyproject.dynamoclient;

import group.societyproject.dynamoclient.command.CommandHandler;
import group.societyproject.dynamoclient.proxy.ProxyClient;
import group.societyproject.dynamoclient.proxy.ProxyCommon;
import group.societyproject.dynamoclient.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;
import java.nio.file.Path;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION, useMetadata = true)
public class Main {

    public EventHandler handler;

    @Mod.Instance
    public Main instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
    public static ProxyCommon proxy;


    @Mod.EventHandler
    public void PreInit(FMLPreInitializationEvent event){

    }

    @Mod.EventHandler
    public void Init(FMLInitializationEvent event){
        MinecraftForge.EVENT_BUS.register(instance);
        initDirs();
        handler = new EventHandler();
        MinecraftForge.EVENT_BUS.register(handler);

    }

    @Mod.EventHandler
    public void PostInit(FMLPostInitializationEvent event){

    }

    private void initDirs(){
        File directory = new File(String.valueOf(Reference.modFolder));

        if (!directory.exists())
            directory.mkdir();

        directory = new File(String.valueOf(Reference.modFolder.resolve("modules")));
        if (!directory.exists())
            directory.mkdir();

        directory = new File(String.valueOf(Reference.modFolder.resolve("commands")));
        if (!directory.exists())
            directory.mkdir();

        directory = new File(String.valueOf(Reference.modFolder.resolve("settings")));
        if (!directory.exists())
            directory.mkdir();
    }


}