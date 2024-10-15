package net.Nico.rubberduckmod.block;

import com.mojang.logging.LogUtils;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import org.slf4j.Logger;

public class rubber_duck_feather_block extends Block {

    private static final Logger LOGGER = LogUtils.getLogger();

    public rubber_duck_feather_block() {
        super(BlockBehaviour.Properties.of()
                .strength(0.5F) // Adjust hardness and resistance (easy to mine)
                .sound(SoundType.AZALEA) // Set sound type
                .noOcclusion()); // Allow entities to pass through the block if needed
    }

    @Override
    public void updateEntityAfterFallOn(BlockGetter level, Entity entity) {
        Vec3 velocity = entity.getDeltaMovement();
        if (velocity.y < 0.0D && !entity.isSuppressingBounce()) {  // Check if the entity should be allowed to bounce
            double bounceFactor = 0.9D;  // Adjust the bounce factor as needed (lower = less bounce, higher = more)
            entity.setDeltaMovement(velocity.x, -velocity.y * bounceFactor, velocity.z);  // Apply bounce effect
            entity.resetFallDistance();  // Reset fall distance
        }
    }
}
