package untamedwilds.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import untamedwilds.UntamedWilds;
import untamedwilds.init.ModItems;

public class ModCreativeModeTab {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, UntamedWilds.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> UNTAMEDWILDS_ITEMS = CREATIVE_MODE_TABS.register("untamedwilds_items",
        () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.untamedwilds_items"))
            .icon(() -> new ItemStack(ModItems.LOGO.get()))
            .displayItems((parameters, output) -> {
                // Add all mod items to the creative tab
                ModItems.ITEMS.getEntries().forEach(item -> output.accept(item.get()));
            })
            .build()
    );
}
