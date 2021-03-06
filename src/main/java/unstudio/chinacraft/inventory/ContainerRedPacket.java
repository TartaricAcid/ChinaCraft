package unstudio.chinacraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import unstudio.chinacraft.common.ChinaCraft;
import unstudio.chinacraft.common.config.FeatureConfig;

public class ContainerRedPacket extends Container {

    private ItemStack itemStack;

    public ContainerRedPacket(EntityPlayer player, ItemStack itemStack) {
        this.itemStack = itemStack;
        Slot slot0 = new Slot(new InventoryBasic("Redpacket", false, 1), 0, 80, 25);
        NBTTagCompound par1NBTTagCompound = itemStack.getTagCompound();
        if (par1NBTTagCompound != null) {
            NBTTagCompound itemnbt = (NBTTagCompound) par1NBTTagCompound.getTag("Item");
            if (itemnbt != null) {
                ItemStack item = ItemStack.loadItemStackFromNBT(itemnbt);
                if (item != null && FeatureConfig.ItemBombInRedPackerExplosion&&item.getItem() == ChinaCraft.bomb){
                    player.worldObj.createExplosion(player, player.posX, player.posY, player.posZ, 1.5F, true);
                    
                    slot0.putStack(null);
                }
                else slot0.putStack(item);
            }
        }

        this.addSlotToContainer(slot0);
        int var3;
        for (var3 = 0; var3 < 3; ++var3) {
            for (int var4 = 0; var4 < 9; ++var4) {
                this.addSlotToContainer(new Slot(player.inventory, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
            }
        }

        for (var3 = 0; var3 < 9; ++var3) {
            if (var3 == player.inventory.currentItem) {
                this.addSlotToContainer(new Slot(player.inventory, var3, 8 + var3 * 18, 142) {
                    @Override
                    public boolean canTakeStack(EntityPlayer p_82869_1_) {
                        return false;
                    }

                    @Override
                    public boolean isItemValid(ItemStack p_75214_1_) {
                        return false;
                    }
                });
            } else {
                this.addSlotToContainer(new Slot(player.inventory, var3, 8 + var3 * 18, 142));
            }
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer p_75145_1_) {
        return true;
    }

    @Override
    public void onContainerClosed(EntityPlayer p_75134_1_) {
        super.onContainerClosed(p_75134_1_);
        if (p_75134_1_.inventory.getCurrentItem() != null
                && p_75134_1_.inventory.getCurrentItem().getItem().equals(ChinaCraft.redPacket)) {
            NBTTagCompound nbtitem = new NBTTagCompound();
            NBTTagCompound nbtitem2 = new NBTTagCompound();
            if (getSlot(0).getStack() != null) {
                getSlot(0).getStack().writeToNBT(nbtitem);
            }
            nbtitem2.setTag("Item", nbtitem);
            itemStack.setTagCompound(nbtitem2);
            p_75134_1_.inventory.setInventorySlotContents(p_75134_1_.inventory.currentItem, itemStack);
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack var3 = null;
        Slot var4 = (Slot) this.inventorySlots.get(par2);
        if (var4 != null && var4.getHasStack()) {
            ItemStack var5 = var4.getStack();
            var3 = var5.copy();
            // 点击到Slot的ID为0的时候，将物品送回玩家的背包中
            if (par2 == 0) {
                if (!this.mergeItemStack(var5, 1, 28, false)) {
                    return null;
                }
                var4.onSlotChange(var5, var3);
            }
            // 点击到玩家的背包的时候将物品送到玩家的快捷栏中
            else if (par2 > 0 && par2 < 28) {
                if (!this.mergeItemStack(var5, 28, 37, false)) {
                    return null;
                }
            }
            // 点击到玩家的快捷栏的时候将物品送到背包中
            else if (par2 >= 28 && par2 < 37) {
                if (!this.mergeItemStack(var5, 1, 28, false)) {
                    return null;
                }
            }
            if (var5.stackSize == 0) {
                var4.putStack((ItemStack) null);
            } else {
                var4.onSlotChanged();
            }
            if (var5.stackSize == var3.stackSize) {
                return null;
            }
            var4.onPickupFromSlot(par1EntityPlayer, var5);
        }

        return var3;
    }
}
