package unstudio.chinacraft.tileentity;

import unstudio.chinacraft.common.ChinaCraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileSericultureFrame extends TileEntity implements ISidedInventory{
	
    private static final int[] slotsTop = new int[] {0,1,2,3,4,5,6,7,8};
    private static final int[] slotsSide = new int[] {9};
    private static final int[] slotsBottom = new int[] {10};

	private ItemStack stack[] = new ItemStack[11];
	private double mortality = -1;
	
	public int getSchedule() {
		return schedule;
	}

	public void setSchedule(int schedule) {
		this.schedule = schedule;
	}

	public void setMortality(double mortality) {
		this.mortality = mortality;
	}

	private int schedule = 0;
    
	@Override
	public int getSizeInventory() {
		return stack.length;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return stack[index];
	}

	@Override
    public ItemStack decrStackSize(int index, int count) {
        if (this.stack[index] != null)
        {
                    ItemStack itemstack;

                    if (this.stack[index].stackSize <= count)
                    {
                        itemstack = this.stack[index];
                        this.stack[index] = null;
                        return itemstack;
                    }
                    else
                    {
                        itemstack = this.stack[index].splitStack(count);

                        if (this.stack[index].stackSize == 0)
                        {
                                    this.stack[index] = null;
                        }

                        return itemstack;
                    }
        }
        else
               {
                    return null;
        }
	}

	@Override
    public ItemStack getStackInSlotOnClosing(int p_70304_1_)
    {
        if (this.stack[p_70304_1_] != null)
        {
            ItemStack itemstack = this.stack[p_70304_1_];
            this.stack[p_70304_1_] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		this.stack[index] = stack;
		if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
			stack.stackSize = this.getInventoryStackLimit();
		}
	}

	@Override
	public String getInventoryName() {
		return null;
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
		return true;
	}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}
	
	@Override
	public void updateEntity() {
//		if(mortality == -1) {
//			mortality = getMortality();
//		}
//		if(getStackInSlot(0)!=null&&getStackInSlot(0).getItem().equals(ChinaCraft.silkworm)&&getStackInSlot(0).stackSize>0) {
//			float m;
//			m = mortality + (worldObj.isRaining()?worldObj.getTopSolidOrLiquidBlock(xCoord, zCoord) == yCoord?0.2F:0:0);
//			if((getStackInSlot(1)==null||getStackInSlot(1).stackSize<=0||getStackInSlot(1).getItem()!=ChinaCraft.itemMulberryLeaf)&&getStackInSlot(0).getItemDamage() == 1) {
//				m = 10.0F;			
//			}
//			if(getStackInSlot(1)!=null&&worldObj.rand.nextInt(12000)<getStackInSlot(0).stackSize) {
//				getStackInSlot(1).stackSize--;
//				if(getStackInSlot(1).stackSize<=0) {
//					setInventorySlotContents(1, null);
//				}
//			}
//			schedule++;
//			if(schedule >= getMaxSchedule(getStackInSlot(0).getItemDamage())) {
//				if(getStackInSlot(0).getItemDamage() == 2) {
//					setInventorySlotContents(2, new ItemStack(ChinaCraft.silkwormChrysalis,getStackInSlot(0).stackSize));
//					getStackInSlot(0).setItemDamage(0);
//					getStackInSlot(0).stackSize=(getStackInSlot(0).stackSize*2)>=64?64:getStackInSlot(0).stackSize*2;
//				}else {
//				getStackInSlot(0).setItemDamage(getStackInSlot(0).getItemDamage()+1);
//				}
//			}
//			if(9.325*m>worldObj.rand.nextInt(24000)) {
//				if(getStackInSlot(0).getItemDamage() == 2){
//					if(getStackInSlot(2) == null){
//						setInventorySlotContents(2, new ItemStack(ChinaCraft.silkwormChrysalis));
//					}else if(getStackInSlot(2).getItem() == ChinaCraft.itemMulberryLeaf){
//						getStackInSlot(2).stackSize++;
//					}
//				}
//				getStackInSlot(0).stackSize--;
//				if(getStackInSlot(0).stackSize<=0) {
//					setInventorySlotContents(0, null);
//				}
//			}
//		}else{
//			schedule = 0;
//		}
	}
	
	public int getMaxSchedule(int i) {
		switch (i) {
		case 0:
			return 16000;
		case 1:
			return 45000;
		case 2:
			return 9000;
		default:
			return 1;
		}
	}
	
	public double getMortality() {
		double temperature = worldObj.getBiomeGenForCoords(xCoord, zCoord).temperature < 0?0:worldObj.getBiomeGenForCoords(xCoord, zCoord).temperature>1.5F?1.5F:worldObj.getBiomeGenForCoords(xCoord, zCoord).temperature;
		double rainfall = worldObj.getBiomeGenForCoords(xCoord, zCoord).rainfall < 0?0:worldObj.getBiomeGenForCoords(xCoord, zCoord).rainfall >1.5F?1.5F:worldObj.getBiomeGenForCoords(xCoord, zCoord).rainfall;
		int height = yCoord >128?128:yCoord;
		double m = 0;
		m += -0.422*Math.pow(temperature, 4)+1.109*Math.pow(temperature, 3)-0.301*Math.pow(temperature, 2)-0.620*temperature+0.3 < 0?0:-0.422*Math.pow(temperature, 4)+1.109*Math.pow(temperature, 3)-0.301*Math.pow(temperature, 2)-0.620*temperature+0.3;
		m += -0.422*Math.pow(rainfall, 4)+1.109*Math.pow(rainfall, 3)-0.301*Math.pow(rainfall, 2)-0.620*rainfall+0.3 < 0?0:-0.422*Math.pow(rainfall, 4)+1.109*Math.pow(rainfall, 3)-0.301*Math.pow(rainfall, 2)-0.620*rainfall+0.3;
		m += 0.000048828125F*(height-64F)*(height-64F);
		return m;
	}
	
    @Override
	public void readFromNBT(NBTTagCompound p_145839_1_)
    {
        super.readFromNBT(p_145839_1_);
        NBTTagList nbttaglist = p_145839_1_.getTagList("Items", 10);
        this.stack = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.stack.length)
            {
                this.stack[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
        this.schedule = p_145839_1_.getInteger("schedule");
        this.mortality = p_145839_1_.getDouble("mortality");
    }

    @Override
	public void writeToNBT(NBTTagCompound p_145841_1_)
    {
        super.writeToNBT(p_145841_1_);
        p_145841_1_.setInteger("schedule", this.schedule);
        p_145841_1_.setDouble("mortality", this.mortality);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.stack.length; ++i)
        {
            if (this.stack[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.stack[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }
        p_145841_1_.setTag("Items", nbttaglist);
    }

	@Override
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
		return true;
	}

    @Override
	public int[] getAccessibleSlotsFromSide(int p_94128_1_)
    {
        return p_94128_1_ == 0 ? slotsBottom : p_94128_1_ == 1 ? slotsTop : slotsSide;
    }

    @Override
	public boolean canInsertItem(int p_102007_1_, ItemStack p_102007_2_, int p_102007_3_)
    {
        return this.isItemValidForSlot(p_102007_1_, p_102007_2_);
    }

    @Override
	public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_)
    {
        return p_102008_3_ != 0 || p_102008_1_ != 0;
    }

}