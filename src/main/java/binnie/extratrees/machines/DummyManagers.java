package binnie.extratrees.machines;

import com.google.common.collect.ImmutableSet;

import javax.annotation.Nullable;
import java.util.Set;

import net.minecraft.item.ItemStack;

import net.minecraftforge.fluids.FluidStack;

import binnie.core.api.IBinnieRecipe;
import binnie.core.api.ICraftingManager;
import binnie.extratrees.api.recipes.IBreweryManager;
import binnie.extratrees.api.recipes.IBreweryRecipe;

public class DummyManagers {

	public static abstract class DummyCraftingManager<T extends IBinnieRecipe> implements ICraftingManager<T> {
		@Override
		public boolean addRecipe(T recipe) {
			return false;
		}

		@Override
		public boolean removeRecipe(T recipe) {
			return false;
		}

		@Override
		public Set<T> recipes() {
			return ImmutableSet.of();
		}
	}

	public static class DummyBreweryManager extends DummyCraftingManager<IBreweryRecipe> implements IBreweryManager {
		@Override
		public void addRecipe(FluidStack input, FluidStack output) {
		}

		@Override
		public void addRecipe(FluidStack input, FluidStack output, ItemStack yeast) {
		}

		@Override
		public void addGrainRecipe(String grainOreName, FluidStack output) {
		}

		@Override
		public void addGrainRecipe(String grainOreName, FluidStack output, String ingredientOreName) {
		}

		@Override
		public void addGrainRecipe(String grainOreName, FluidStack output, @Nullable String ingredientOreName, ItemStack yeast) {
		}
	}
}