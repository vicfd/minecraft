package plugin;

import java.util.Arrays;
import org.bukkit.block.Chest;
import org.bukkit.inventory.DoubleChestInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SortChest 
{
	private Chest c;
	
	public SortChest(Chest c)
	{
		this.c = c;
	}
	
	public void sort()
	{
		Inventory chestInv =  c.getInventory();
		ItemStack[] items = getItems(chestInv);
		int size = items.length;
		
		stackItems(items, size);
		size = clean(items, size);
		sort(items, size);
		save(items, chestInv);
	}
	
	private ItemStack[] getItems(Inventory chestInv)
	{
		ItemStack[] items;
		
        if (chestInv instanceof DoubleChestInventory)
            items = ((DoubleChestInventory) chestInv).getHolder().getInventory().getContents();
        else
            items = chestInv.getContents();
        
        return items;
	}
	
    private void stackItems(ItemStack[] items, int size) 
    {
		for (int i = 0; i < size; i++)
		{
			if (items[i] != null)
			{
				int maxStackSize = items[i].getMaxStackSize();
				
				if (items[i].getAmount() < maxStackSize)
				{
					for (int j = i + 1; j < size; j++)
					{
						if (items[j] != null)
						{
							if (items[i].isSimilar(items[j]))
							{								
				                int total = items[i].getAmount() + items[j].getAmount();
				                
				                if (total > maxStackSize)
				                {
				                    items[i].setAmount(maxStackSize);
				                    items[j].setAmount(total - maxStackSize);
				                } 
				                else
				                {
				                    items[i].setAmount(total);
				                    items[j] = null;
				                }
							}
						}
					}
				}
			}
		}
    }

	private int clean(ItemStack[] items, int size)
	{
    	int i = 0, j = 0;
    	
    	while(i < size && items[i] != null)
    		i++;
    	
    	j = i;
    	i++;
    	
        while (i < size)
        {
        	if (items[i] != null)
        	{
        		items[j] = items[i];
        		items[i] = null;
        		j++;
        	}
        	
        	i++;
        }
        
        return j;
	}
    
    private void sort(ItemStack[] items, int size)
    {
		for (int i = 0; i < size; i++)
		{
			for (int j = i + 1; j < size; j++)
			{
				if (items[i].getType().ordinal() > items[j].getType().ordinal()) 
				{
					ItemStack aux = items[i];
					items[i] = items[j];
					items[j] = aux;
	            }
			}
		}
    }

    private void save(ItemStack[] items, Inventory chestInv)
    {
        if (chestInv instanceof DoubleChestInventory) 
        {
 			ItemStack[] chestA = Arrays.copyOfRange(items, 0, 27);
 			ItemStack[] chestB = items.length > 26 ? Arrays.copyOfRange(items, 27, 54) : new ItemStack[]{};
 			
 			DoubleChestInventory dChest = (DoubleChestInventory) chestInv;
 			dChest.getLeftSide().setContents(chestA);
 			dChest.getRightSide().setContents(chestB);
        }
        else
     	   chestInv.setContents(items);	
    }
}
