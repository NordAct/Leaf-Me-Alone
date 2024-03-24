package nordmods.leafmealone.mixin;

import net.minecraft.block.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LeavesBlock.class)
public abstract class LeavesBlockMixin extends Block {
    public LeavesBlockMixin(Settings settings) {
        super(settings);
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (world instanceof ClientWorld) {
            if (context instanceof EntityShapeContext entityShapeContext)
                if (entityShapeContext.getEntity() != null && entityShapeContext.getEntity().getControllingVehicle() != null) return VoxelShapes.empty();
        }

        if (context instanceof EntityShapeContext entityShapeContext)
            if (entityShapeContext.getEntity() != null && entityShapeContext.getEntity().hasControllingPassenger()) return VoxelShapes.empty();
        return super.getCollisionShape(state, world, pos, context);
    }
}
