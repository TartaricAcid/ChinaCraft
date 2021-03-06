package unstudio.chinacraft.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;

import unstudio.chinacraft.common.ChinaCraft;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSilkworm extends Item {

    public static final String[] name = new String[] { "graine", "young", "chrysalis" };
    private IIcon[] icon;

    public ItemSilkworm() {
        setMaxStackSize(64);
        setHasSubtypes(true);
        setMaxDamage(0);
        setCreativeTab(ChinaCraft.tabFarming);
        setUnlocalizedName("silkworm");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int p_77617_1_) {
        int j = MathHelper.clamp_int(p_77617_1_, 0, 2);
        return this.icon[j];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister p_94581_1_) {
        icon = new IIcon[name.length];

        for (int i = 0; i < name.length; ++i) {
            this.icon[i] = p_94581_1_.registerIcon("chinacraft:silkworm_" + name[i]);
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack p_77667_1_) {
        int i = MathHelper.clamp_int(p_77667_1_.getItemDamage(), 0, 2);
        return super.getUnlocalizedName() + "_" + name[i];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item p_150895_1_, CreativeTabs p_150895_2_, List p_150895_3_) {
        for (int i = 0; i < name.length; ++i) {
            p_150895_3_.add(new ItemStack(p_150895_1_, 1, i));
        }
    }
}
