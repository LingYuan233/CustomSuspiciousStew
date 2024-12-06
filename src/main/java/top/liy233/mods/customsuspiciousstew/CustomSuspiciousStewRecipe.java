package top.liy233.mods.customsuspiciousstew;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.liy233.mods.customsuspiciousstew.config.ConfigData;
import top.liy233.mods.customsuspiciousstew.config.ConfigManager;

public class CustomSuspiciousStewRecipe extends SpecialCraftingRecipe {
    private static final Logger log = LoggerFactory.getLogger(CustomSuspiciousStewRecipe.class);

    public CustomSuspiciousStewRecipe(Identifier id) {
        super(id);
    }


    @Override
    public boolean matches(CraftingInventory inventory, World world) {
        int keyItem = 0;
        boolean hasOtherItem = false;
        for (int i = 0; i < inventory.size(); ++i){
            ItemStack itemStack = inventory.getStack(i);
            if (!itemStack.isEmpty()){
                if (itemStack.getItem() == Items.BROWN_MUSHROOM){
                    keyItem ++;
                }
                else if (itemStack.getItem() == Items.RED_MUSHROOM){
                    keyItem ++;
                }
                else if (itemStack.getItem() == Items.BOWL){
                    keyItem ++;
                }
                else if (hasItemInRaws(itemStack)){
                    keyItem ++;
                }
                else {
                    hasOtherItem = true;
                }
            }
        }
        // LOGGER.info("keyItem: {} hasOtherItem: {}", keyItem, hasOtherItem);
        return keyItem == 4 && !hasOtherItem;
    }

    @Override
    public ItemStack craft(CraftingInventory inventory) {

        boolean hasBrownMushroom = false;
        boolean hasRedMushroom = false;
        boolean hasBowl = false;
        boolean hasRaw = false;
        String  rawItem = "";
        int itemCount = 0;

        for (int i = 0; i < inventory.size(); ++i){
            ItemStack item = inventory.getStack(i);
            if (!item.isEmpty()){

                if (item.getItem() == Items.BROWN_MUSHROOM && !hasBrownMushroom){
                    hasBrownMushroom = true;
                    itemCount++;
                }
                if (item.getItem() == Items.RED_MUSHROOM && !hasRedMushroom){
                    hasRedMushroom = true;
                    itemCount++;
                }
                if (item.getItem() == Items.BOWL && !hasBowl){
                    hasBowl = true;
                    itemCount++;
                }

                if (hasItemInRaws(item) && !hasRaw){
                    hasRaw = true;
                    rawItem = Registry.ITEM.getId(item.getItem()).toString();
                    itemCount++;
                }

            }
        }
        log.info("hasBrownMushroom: {}, hasRedMushroom: {}, hasBowl: {}, hasRaw: {}, rawItem: {}, itemCount: {}", hasBrownMushroom, hasRedMushroom, hasBowl, hasRaw, rawItem, itemCount);


        if (hasBrownMushroom && hasRedMushroom && hasBowl && hasRaw && itemCount == 4){
            ConfigData data = getDataByRaw(rawItem);
            if (data!= null){
                return getItemWithNBT(data.getEffectId(), data.getDuration(), data.getAmplifier());
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean fits(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Customsuspiciousstew.CUSTOM_SUSPICIOUS_STEW_SERIALIZER;
    }



    private ItemStack getItemByName(String name){
        return Registry.ITEM.get(new Identifier(name)).getDefaultStack();
    }
    private StatusEffect getEffectByName(String name){
        return Registry.STATUS_EFFECT.get(new Identifier(name));
    }
    private boolean hasItemInRaws(ItemStack item){
        String name = Registry.ITEM.getId(item.getItem()).toString();
        boolean isRaw = false;
        for (ConfigData data : ConfigManager.getInstance()){
            if (data.getRaw().equals(name)) {
                isRaw = true;
                break;
            }
        }
        return isRaw;
    }
    private ConfigData getDataByRaw(String raw){
        for (ConfigData data : ConfigManager.getInstance()){
            if (data.getRaw().equals(raw)){
                return data;
            }
        }
        return null;
    }
    private ItemStack getItemWithNBT(String effect, int time, int level){
        //  Effects[ { I_EffectDuration, Byte_EffectId } ]
        ItemStack stew = Items.SUSPICIOUS_STEW.getDefaultStack();
        NbtCompound root = stew.getOrCreateNbt();
        NbtList Effects = new NbtList();
        NbtCompound eff = new NbtCompound();

        eff.putByte("EffectId", (byte) StatusEffect.getRawId(getEffectByName(effect)));
        eff.putInt("EffectDuration", time);

        Effects.add(eff);
        root.put("Effects", Effects);
        stew.setNbt(root);
        return stew;

    }
}
