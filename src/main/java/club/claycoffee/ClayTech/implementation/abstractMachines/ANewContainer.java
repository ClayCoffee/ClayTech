package club.claycoffee.ClayTech.implementation.abstractMachines;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import club.claycoffee.ClayTech.ClayTech;
import club.claycoffee.ClayTech.api.listeners.MachineTickEvent;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.interfaces.InventoryBlock;
import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.api.energy.ChargableBlock;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;

@SuppressWarnings("deprecation")
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

	protected void tick(Block b) {
		new BukkitRunnable() {

			@Override
			public void run() {
				Bukkit.getPluginManager().callEvent(new MachineTickEvent(b));
			}

		}.runTask(ClayTech.getInstance());
		BlockMenu inv = BlockStorage.getInventory(b);

		if (isProcessing(b)) {
			int timeleft = progress.get(b);

			if (timeleft > 0) {
				ChestMenuUtils.updateProgressbar(inv, 22, timeleft, processing.get(b).getTicks(), getProgressBar());

				if (ChargableBlock.isChargable(b)) {
					if (ChargableBlock.getCharge(b) < getEnergyConsumption())
						return;
					ChargableBlock.addCharge(b, -getEnergyConsumption());
					progress.put(b, timeleft - 1);
				} else
					progress.put(b, timeleft - 1);
			} else {
				inv.replaceExistingItem(22, new CustomItem(Material.BLACK_STAINED_GLASS_PANE, " "));

				for (ItemStack output : processing.get(b).getOutput()) {
					inv.pushItem(output.clone(), getOutputSlots());
				}

				progress.remove(b);
				processing.remove(b);
			}
		} else {
			MachineRecipe r = null;
			Map<Integer, Integer> found = new HashMap<>();

			for (MachineRecipe recipe : recipes) {
				for (ItemStack input : recipe.getInput()) {
					for (int slot : getInputSlots()) {
						if (SlimefunManager.isItemSimilar(inv.getItemInSlot(slot), input, true)) {
							found.put(slot, input.getAmount());
							break;
						}
					}
				}
				if (found.size() == recipe.getInput().length) {
					r = recipe;
					break;
				} else
					found.clear();
			}

			if (r != null) {
				if (!fits(b, r.getOutput()))
					return;

				for (Map.Entry<Integer, Integer> entry : found.entrySet()) {
					inv.consumeItem(entry.getKey(), entry.getValue());
				}

				processing.put(b, r);
				progress.put(b, r.getTicks());
			}
		}
	}
}