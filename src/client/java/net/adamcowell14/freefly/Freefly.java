package net.adamcowell14.freefly;

import net.adamcowell14.freefly.util.KeyBind;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.adamcowell14.freefly.util.ConfigParser;

public class Freefly implements ModInitializer {
    public static final ConfigParser config = ConfigParser.INSTANCE;
    public static final String MOD_ID = "freeFly";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    public static boolean running = false;

    @Override
    public void onInitialize() {
        config.load();
        KeyBind.register();
    }
}
