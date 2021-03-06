package unstudio.chinacraft.block.especial;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import unstudio.chinacraft.client.gui.GuiID;
import unstudio.chinacraft.common.ChinaCraft;
import unstudio.chinacraft.tileentity.TileJadeBench;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class JadeWorkingTable extends BlockContainer {
    @SideOnly(Side.CLIENT)
    private IIcon field_150035_a;
    @SideOnly(Side.CLIENT)
    private IIcon field_150034_b;

    public JadeWorkingTable() {
        super(Material.rock);
        this.setCreativeTab(ChinaCraft.tabCore);
        setHardness(2.0F);
        setBlockName("jade_table");
    }

    @Override
    public boolean onBlockActivated(World p_149727_1_, int x, int y, int z, EntityPlayer p_149727_5_, int p_149727_6_,
            float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        if (p_149727_1_.isRemote) {
            return true;
        } else {
            p_149727_5_.openGui(ChinaCraft.instance, GuiID.GUI_JadeBench, p_149727_1_, x, y, z);
            return true;
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
        return p_149691_1_ == 1 ? this.field_150035_a
                : (p_149691_1_ == 0 ? Blocks.planks.getBlockTextureFromSide(p_149691_1_)
                        : (p_149691_1_ != 2 && p_149691_1_ != 4 ? this.blockIcon : this.field_150034_b));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister p_149651_1_) {
        this.blockIcon = p_149651_1_.registerIcon(this.getTextureName() + "_side");
        this.field_150035_a = p_149651_1_.registerIcon(this.getTextureName() + "_top");
        this.field_150034_b = p_149651_1_.registerIcon(this.getTextureName() + "_front");
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileJadeBench();
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return Item.getItemFromBlock(this);
    }
}
