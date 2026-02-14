package untamedwilds.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.neoforged.neoforge.common.ModConfigSpec;
import untamedwilds.UntamedWilds;

public class ConfigBase {
    private static final ModConfigSpec.Builder common_builder = new ModConfigSpec.Builder();
    public static final ModConfigSpec common_config;

    public static final ConfigFeatureControl FEATURES;
    public static final ConfigGamerules GAMERULES;
    public static final ConfigMobControl MOBS;
    public static final ConfigModCompat COMPAT;

    static {
        FEATURES = new ConfigFeatureControl(common_builder);
        GAMERULES = new ConfigGamerules(common_builder);
        MOBS = new ConfigMobControl(common_builder);
        COMPAT = new ConfigModCompat(common_builder);

        common_config = common_builder.build();
    }

    public static void loadConfig(ModConfigSpec config, String path) {
        final CommentedFileConfig configData = CommentedFileConfig.builder(path).sync().autosave().writingMode(WritingMode.REPLACE).build();
        configData.load();
    }
}
