package unstudio.chinacraft.common.config;

import net.minecraftforge.common.config.Configuration;

/**
 * Created by trychen on 15/11/15.
 */
public class ConfigLoader {
    public static Configuration mainConfig;

    public ConfigLoader(Configuration config) {
        ConfigLoader.mainConfig = config;
        load();
    }

    public static void load() {
        mainConfig.load();
        new FeatureConfig();
        mainConfig.save();
    }

    public static Configuration getMainConfig() {
        return mainConfig;
    }
}
