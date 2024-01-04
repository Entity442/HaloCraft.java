package com.harby.halocraft.datagen;

import com.harby.halocraft.HaloCraft;
import com.harby.halocraft.core.HaloBlocks;
import com.harby.halocraft.core.HaloItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, HaloCraft.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (RegistryObject<Item> item : HaloItems.ITEMS.getEntries()) {
            if (ConfigDataGenerator.excludesItemsGenerate.contains(item.get())) {continue;}
            ResourceLocation id = item.getId();
            if (HaloBlocks.BLOCKS.getEntries().stream().noneMatch(block -> block.getId().equals(id))) {
                simpleItem(item);
            }
        }
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(HaloCraft.MODID, "item/" + item.getId().getPath()));
    }
}
