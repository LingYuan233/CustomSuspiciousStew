package top.liy233.mods.customsuspiciousstew.config;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import top.liy233.mods.customsuspiciousstew.Customsuspiciousstew;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import static top.liy233.mods.customsuspiciousstew.Customsuspiciousstew.LOGGER;

public class ConfigManager {
    private static final Gson GSON = new Gson();
    public static ArrayList<ConfigData> recipeList = new ArrayList<>();

    public static void loadConfig() {
        File config = Customsuspiciousstew.CONFIG_PATH.toFile();
        Type type = new TypeToken<List<ConfigData>>(){}.getType();
        ArrayList<ConfigData> configList = new ArrayList<>(GSON.fromJson(readFile(config), type));
        recipeList = configList;
        LOGGER.info("Config loaded, {} recipes loaded.", recipeList.size());
    }


    public static void initConfigFile() throws IOException {
        File config = Customsuspiciousstew.CONFIG_PATH.toFile();
        if (!config.exists()) {
            LOGGER.info("Config file not found, creating new config file: {}", config.getAbsolutePath());
            config.createNewFile();
            writeFile(config, DEFAULT_CONFIG);
        }
    }

    public static ArrayList<ConfigData> getInstance(){
        return recipeList;
    }



    private static final String DEFAULT_CONFIG = """
            [
              {
                "raw": "minecraft:diamond_block",
                "effectId": "minecraft:jump_boost",
                "duration": 10000
              }
            ]
            """;

    public static String readFile(File file){
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
            String text;
            while ((text = reader.readLine()) != null){
                buffer.append(text).append(System.getProperty("line.separator"));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return buffer.toString();
    }
    public static void writeFile(File target, String text){
        try {
            if (!target.exists()){
                target.createNewFile();
            }
            FileWriter writer = new FileWriter(target);
            writer.write(text);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
