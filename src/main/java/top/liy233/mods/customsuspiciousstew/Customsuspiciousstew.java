package top.liy233.mods.customsuspiciousstew;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialRecipeSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.liy233.mods.customsuspiciousstew.config.ConfigManager;

import java.io.IOException;
import java.nio.file.Path;

public class Customsuspiciousstew implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("Custom Suspicious Stew");
    public static final Path CONFIG_PATH = FabricLoader.getInstance().getConfigDir().resolve("customsuspiciousstew.json");
    public static final RecipeSerializer<CustomSuspiciousStewRecipe> CUSTOM_SUSPICIOUS_STEW_SERIALIZER = RecipeSerializer.register("craft_css", new SpecialRecipeSerializer<>(CustomSuspiciousStewRecipe::new));

    @Override
    public void onInitialize() {
        try {

            LOGGER.info("Custom Suspicious Stew is initializing");
            ConfigManager.initConfigFile();

            LOGGER.info("Custom Suspicious Stew is loading config");
            ConfigManager.loadConfig();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
