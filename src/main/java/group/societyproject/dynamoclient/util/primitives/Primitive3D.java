package group.societyproject.dynamoclient.util.primitives;

import net.minecraft.util.math.BlockPos;

abstract public class Primitive3D {

    Primitive3D(BlockPos _center){
        super();
        center = _center;
    }

    public BlockPos getCenter() {
        return center;
    }

    protected BlockPos center;

    abstract public boolean isInPrimitive3D(BlockPos block);

    abstract public int getFurthestReach();
}
