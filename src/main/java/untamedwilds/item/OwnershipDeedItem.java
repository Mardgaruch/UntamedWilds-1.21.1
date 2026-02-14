package untamedwilds.item;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import java.util.List;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.UUID;

public class OwnershipDeedItem extends Item {
    
    public OwnershipDeedItem(Item.Properties properties) {
        super(properties);
    }

    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag flagIn) {
        CompoundTag tag = stack.getOrDefault(net.minecraft.core.component.DataComponents.CUSTOM_DATA, net.minecraft.world.item.component.CustomData.EMPTY).copyTag();
        if (tag != null) {
            tooltip.add(net.minecraft.network.chat.Component.translatable("item.untamedwilds.ownership_deed_desc_4", tag.getString("entityname")).withStyle(ChatFormatting.GRAY));
            tooltip.add(net.minecraft.network.chat.Component.translatable("item.untamedwilds.ownership_deed_desc_5").withStyle(ChatFormatting.GRAY));
            tooltip.add(net.minecraft.network.chat.Component.translatable("item.untamedwilds.ownership_deed_desc_6",  tag.getString("ownername")).withStyle(ChatFormatting.ITALIC, ChatFormatting.GRAY));
        }
        else {
            tooltip.add(net.minecraft.network.chat.Component.translatable("item.untamedwilds.ownership_deed_desc_1").withStyle(ChatFormatting.GRAY));
        }
    }

    public boolean isFoil(ItemStack stack) {
    return stack.has(net.minecraft.core.component.DataComponents.CUSTOM_DATA);
    }

    @Nonnull
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);

        CompoundTag tag = itemstack.getOrDefault(net.minecraft.core.component.DataComponents.CUSTOM_DATA, net.minecraft.world.item.component.CustomData.EMPTY).copyTag();
        if (tag != null) {
            if (!tag.getString("entityid").isEmpty()) {
                List<LivingEntity> list = worldIn.getEntitiesOfClass(LivingEntity.class, playerIn.getBoundingBox().inflate(8.0D));
                for(LivingEntity entity : list) {
                    if (entity.getUUID().equals(UUID.fromString(tag.getString("entityid")))) {
                        entity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 80, 0, false, false));
                    }
                }
            }
        }
        return new InteractionResultHolder<>(InteractionResult.SUCCESS, itemstack);
    }


    /*@Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player playerIn, LivingEntity target, Hand hand) {
        UntamedWilds.LOGGER.log(Level.INFO, "Trying to get entity");

        ItemStack itemstack = playerIn.getItemInHand(hand);
        if (target instanceof TameableEntity) {
            TameableEntity entity_target = (TameableEntity) target;

            if (entity_target.isTame()) {
                if (entity_target.getOwnerId().equals(playerIn.getUUID()) && !itemstack.hasTag()) {
                    CompoundTag nbt = new CompoundTag();
                    nbt.putString("ownername", playerIn.getName().getString());
                    nbt.putString("entityname", entity_target.getName().getString());
                    nbt.putString("ownerid", playerIn.getUUID().toString());
                    nbt.putString("entityid", entity_target.getUUID().toString());
                    itemstack.setTag(nbt);
                    if (UntamedWilds.DEBUG) {
                        UntamedWilds.LOGGER.log(Level.INFO, "Pet owner signed a deed for a " + entity_target.getName().getString());
                    }
                    return InteractionResult.SUCCESS;
                }

                else if (itemstack.hasTag()) {
                    if (entity_target.getOwnerId().toString().equals(itemstack.getTag().getString("ownerid")) && entity_target.getUUID().toString().equals(itemstack.getTag().getString("entityid"))) {
                        entity_target.setOwnerId(playerIn.getUUID());
                        if (!playerIn.isCreative()) {
                            itemstack.shrink(1);
                        }
                        // playerIn.addStat(Stats.getObjectUseStats(this));
                        if (UntamedWilds.DEBUG) {
                            UntamedWilds.LOGGER.log(Level.INFO, "Pet ownership transferred to " + playerIn.getName().getString());
                        }
                        return InteractionResult.CONSUME;
                    }
                }
                return InteractionResult.FAIL;
            }
        }
        return InteractionResult.FAIL;
    }*/
}