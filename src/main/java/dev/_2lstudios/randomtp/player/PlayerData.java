package dev._2lstudios.randomtp.player;

import com.dotphin.milkshakeorm.entity.Entity;
import com.dotphin.milkshakeorm.entity.ID;
import com.dotphin.milkshakeorm.entity.Prop;

public class PlayerData extends Entity {
    @ID
    public String _id;

    @Prop
    public String uuid;

    @Prop
    public double spawnX = -1;

    @Prop
    public double spawnY = -1;

    @Prop
    public double spawnZ = -1;
}
