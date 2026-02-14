package net.minecraftforge.event.village;

import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VillagerTradesEvent {
    private final Map<Integer, List<VillagerTrades.ItemListing>> trades = new HashMap<>();

    public VillagerTradesEvent() {
        // Provide a couple of levels so mods can add offers without null checks
        trades.put(3, new ArrayList<>());
        trades.put(4, new ArrayList<>());
    }

    // Minimal stubs to match expected usage in mods
    public VillagerProfession getType() {
        return null;
    }

    public Map<Integer, List<VillagerTrades.ItemListing>> getTrades() {
        return trades;
    }
}
