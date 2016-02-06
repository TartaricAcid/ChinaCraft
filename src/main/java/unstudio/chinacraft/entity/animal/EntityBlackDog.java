package unstudio.chinacraft.entity.animal;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.World;

/**
 * Created by Trychen on 2015/10/2.
 */
public class EntityBlackDog extends EntityWolf {

    public EntityBlackDog(World p_i1696_1_) {
        super(p_i1696_1_);
    }

    @Override
    public EntityWolf createChild(EntityAgeable p_90011_1_) {
        EntityBlackDog entitywolf = new EntityBlackDog(this.worldObj);
        String s = this.func_152113_b();

        if (s != null && s.trim().length() > 0) {
            entitywolf.func_152115_b(s);
            entitywolf.setTamed(true);
        }

        return entitywolf;
    }
}
