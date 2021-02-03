package group.societyproject.dynamoclient.util;

import group.societyproject.dynamoclient.command.Command;
import group.societyproject.dynamoclient.command.inbuilt_commands.CBuildCommandList;
import net.minecraft.client.Minecraft;

import java.nio.file.Path;

public class Reference{

    public static final String MOD_ID = "dyn";

    public static final String NAME = "Dynamo";

    public static final String VERSION= "0.0.1";

    public static final String ACCEPTED_VERSION= "[1.12.2]";

    public static final String CLIENT_PROXY_CLASS = "group.societyproject.dynamoclient.proxy.ProxyClient";

    public static final String COMMON_PROXY_CLASS = "group.societyproject.dynamoclient.proxy.ProxyCommon";

    public static Path modFolder = Minecraft.getMinecraft().mcDataDir.toPath().resolve("dynamo");

    public static String commandPrefix = "=";

}
