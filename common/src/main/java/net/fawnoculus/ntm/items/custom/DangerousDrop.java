package net.fawnoculus.ntm.items.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.phys.Vec3;

public interface DangerousDrop {
    void onDropped(ServerLevel world, Vec3 pos, ItemEntity entity);

    void onTouchBlock(ServerLevel world, Vec3 pos, ItemEntity entity);
}
