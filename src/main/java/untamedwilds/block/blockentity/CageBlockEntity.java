package untamedwilds.block.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.HolderLookup;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import untamedwilds.UntamedWilds;
import untamedwilds.config.ConfigGamerules;
import untamedwilds.init.ModBlock;
import untamedwilds.init.ModTags;
import untamedwilds.util.EntityUtils;

import javax.annotation.Nullable;
import java.util.Objects;

public class CageBlockEntity extends BlockEntity {

    private CompoundTag data;
    private boolean locked;

    public CageBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlock.TILE_ENTITY_CAGE.get(), pos, state);
    }

    public static boolean isBlacklisted(Entity entity) {
        return entity.getType().is(ModTags.EntityTags.CAGE_BLACKLIST);
    }

    public boolean cageEntity(Mob entity) {
        if (!this.isLocked()) {
            if (!isBlacklisted(entity) && (ConfigGamerules.easyMobCapturing.get() || entity.getTarget() == null)) {
                this.setTagCompound(EntityUtils.writeEntityToNBT(entity));
                this.setLocked(true);
                entity.discard();
                setChanged();
                return true;
            }
        }
        return false;
    }

    public boolean spawnCagedCreature(ServerLevel worldIn, BlockPos pos, boolean offsetHitbox) {
        if (!worldIn.isClientSide && this.isLocked()) {
            EntityType<?> entity = EntityUtils.getEntityTypeFromTag(this.getTagCompound(), null);
            if (entity != null) {
                if (worldIn.noCollision(untamedwilds.util.EntityUtils.getEntityTypeAABB(entity, pos.getX() + 0.5F, pos.getY() - (offsetHitbox ? entity.getHeight() + 1.2F : 0), pos.getZ() + 0.5F))) {
                    if (worldIn.getEntity(this.data.getCompound("EntityTag").getUUID("UUID")) != null) {
                        UntamedWilds.LOGGER.info("UUID is already present in the Level; Randomizing UUID for the new mob");
                        this.data.getCompound("EntityTag").putUUID("UUID", Mth.createInsecureUUID(worldIn.random));
                    }
                    final Entity[] created = new Entity[1];
                    created[0] = entity.create(worldIn, (e) -> {
                        if (this.data != null && this.data.contains("EntityTag")) {
                            e.load(this.data.getCompound("EntityTag"));
                        }
                    }, pos, MobSpawnType.DISPENSER, true, !Objects.equals(pos, this.getBlockPos()));
                    Entity caged_entity = created[0];
                    if (caged_entity != null) {
                        caged_entity.moveTo(pos.getX() + 0.5F, pos.getY() - (offsetHitbox ? caged_entity.getBbHeight() + 1.2 : 0.8), pos.getZ() + 0.5F, Mth.wrapDegrees(worldIn.random.nextFloat() * 360.0F), 0.0F);
                        if (!worldIn.tryAddFreshEntityWithPassengers(caged_entity)) {
                            caged_entity.setUUID(Mth.createInsecureUUID(worldIn.random));
                            worldIn.addFreshEntityWithPassengers(caged_entity);
                        }
                        this.setTagCompound(null);
                        this.setLocked(true);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Nullable
    public CompoundTag getTagCompound() { return this.data; }

    private void setTagCompound(@Nullable CompoundTag nbt) { this.data = nbt; }

    public boolean hasTagCompound() { return this.data != null; }

    public boolean isLocked() { return this.locked; }

    private void setLocked(boolean locked) { this.locked = locked; }

    /**
     * Read a tag coming from an ItemStack (used when placing the block with saved entity data).
     * This avoids calling protected/private block entity APIs from external classes.
     */
    public void readFromItemTag(@Nullable CompoundTag tag) {
        if (tag == null) {
            this.data = null;
            this.locked = false;
        } else {
            this.data = tag.copy();
            this.locked = tag.getBoolean("closed");
        }
        setChanged();
    }

    @Override
    protected void loadAdditional(CompoundTag compound, HolderLookup.Provider registries) {
        super.loadAdditional(compound, registries);
        this.setTagCompound(compound.copy());
        this.setLocked(compound.getBoolean("closed"));
    }

    @Override
    protected void saveAdditional(CompoundTag compound, HolderLookup.Provider registries) {
        super.saveAdditional(compound, registries);
        compound.putBoolean("closed", this.isLocked());
        if (this.getTagCompound() != null) {
            compound.put("EntityTag", this.getTagCompound().getCompound("EntityTag"));
        }
    }
}