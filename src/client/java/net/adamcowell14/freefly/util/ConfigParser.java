package net.adamcowell14.freefly.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.adamcowell14.freefly.Freefly;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigParser {
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static final ConfigParser INSTANCE = new ConfigParser(FabricLoader.getInstance().getConfigDir().resolve("freefly.json").toFile());
    static {
        INSTANCE.load();
    }

    public final File file;

    public ConfigParser(File file) {
        this.file = file;
    }

    public void changeRunning(boolean running) {
        Freefly.running = running;
        save();
    }

    public void load() {
        if (file.exists()) {
            try {
                String json_string = Files.readString(Path.of(file.toString()), StandardCharsets.US_ASCII);
                Freefly.running = runningFromJson(json_string);
            } catch (Exception e) {
                //
            }
        }
        save();
    }

    public void save() {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(toJson());
        } catch (Exception e) {
            //
        }
    }

    protected boolean runningFromJson(String json_string) {
        JsonParser jsparser = new JsonParser();
        JsonObject object = jsparser.parse(json_string).getAsJsonObject();
        return object.getAsJsonPrimitive("running").getAsBoolean();
    }

    protected String toJson(){
        return "{\"running\":" + Freefly.running + "}";
    }
}