package net.Nico.rubberduckmod.block;

import com.mojang.blaze3d.shaders.Uniform;
import net.Nico.rubberduckmod.Item.ModItems;
import net.Nico.rubberduckmod.RubberDuckMod;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, RubberDuckMod.MOD_ID);

    public static final RegistryObject<Block> RUBBER_DUCK_FEATHER_BLOCK = registerBlock("rubber_duck_feather_block",
            rubber_duck_feather_block::new);



    public static final RegistryObject<Block> DUCKWEED_ORE = registerBlock("duckweed_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(1.5f).requiresCorrectToolForDrops(), UniformInt.of(1,3)));

    public static final RegistryObject<Block> DUCK_FEATHER_ORE = registerBlock("duck_feather_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(2.0f).requiresCorrectToolForDrops(), UniformInt.of(2,5)));


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
