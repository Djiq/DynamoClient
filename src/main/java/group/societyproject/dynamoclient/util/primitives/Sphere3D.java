package group.societyproject.dynamoclient.util.primitives;

import net.minecraft.util.math.BlockPos;

public class Sphere3D extends Primitive3D{

    int radius;

    Sphere3D(BlockPos _center, int _radius) {
        super(_center);
        radius = _radius;
    }

    @Override
    public boolean isInPrimitive3D(BlockPos block) {
        if(block.getDistance(center.getX(),center.getY(),center.getZ()) > radius){
            return false;
        }
        return true;
    }

    @Override
    public int getFurthestReach() {
        return radius;
    }
}
