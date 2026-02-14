package net.minecraftforge.event.entity.player;

import net.minecraft.world.entity.player.Player;

public class PlayerEvent {
    private final Player player;

    public PlayerEvent(Player player) { this.player = player; }
    public Player getEntity() { return player; }

    public static class PlayerLoggedInEvent extends PlayerEvent {
        public PlayerLoggedInEvent(Player player) { super(player); }
    }
}
