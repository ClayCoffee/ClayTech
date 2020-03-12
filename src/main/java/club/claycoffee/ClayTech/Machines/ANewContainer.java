package club.claycoffee.ClayTech.Machines;

import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.interfaces.InventoryBlock;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;

public abstract class ANewContainer extends AContainer implements InventoryBlock, EnergyNetComponent {

	@SuppressWarnings("static-access")
	public ANewContainer(Category category, SlimefunItemStack item, String id, RecipeType recipeType,
			ItemStack[] recipe) {

		super(category, item, recipeType, recipe);

		createPreset(this, getInventoryTitle(), this::constructMenu);

		registerBlockHandler(id, (p, b, tool, reason) -> {
			BlockMenu inv = BlockStorage.getInventory(b);
			if (inv != null) {
				for (int slot : getInputSlots()) {
					if (inv.getItemInSlot(slot) != null) {
						b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
						inv.replaceExistingItem(slot, null);
					}
				}

				for (int slot : getOutputSlots()) {
					if (inv.getItemInSlot(slot) != null) {
						b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
						inv.replaceExistingItem(slot, null);
					}
				}
			}

			super.progress.remove(b);
			super.processing.remove(b);
			return true;
		});

		this.registerDefaultRecipes();
	}
}