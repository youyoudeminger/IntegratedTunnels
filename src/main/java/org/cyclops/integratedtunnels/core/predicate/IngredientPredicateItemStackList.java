package org.cyclops.integratedtunnels.core.predicate;

import net.minecraft.item.ItemStack;
import org.cyclops.commoncapabilities.api.ingredient.IngredientComponent;
import org.cyclops.integrateddynamics.api.evaluate.variable.IValueTypeListProxy;
import org.cyclops.integrateddynamics.core.evaluate.variable.ValueObjectTypeItemStack;
import org.cyclops.integratedtunnels.core.TunnelItemHelpers;
import org.cyclops.integratedtunnels.core.predicate.IngredientPredicate;

import javax.annotation.Nullable;

/**
 * @author rubensworks
 */
public class IngredientPredicateItemStackList extends IngredientPredicate<ItemStack, Integer> {
    private final boolean blacklist;
    private final IValueTypeListProxy<ValueObjectTypeItemStack, ValueObjectTypeItemStack.ValueItemStack> itemStacks;
    private final boolean checkStackSize;
    private final boolean checkItem;
    private final boolean checkDamage;
    private final boolean checkNbt;

    public IngredientPredicateItemStackList(boolean blacklist, int amount, boolean exactAmount, IValueTypeListProxy<ValueObjectTypeItemStack, ValueObjectTypeItemStack.ValueItemStack> itemStacks, boolean checkStackSize, boolean checkItem, boolean checkDamage, boolean checkNbt) {
        super(IngredientComponent.ITEMSTACK, blacklist, false, amount, exactAmount);
        this.blacklist = blacklist;
        this.itemStacks = itemStacks;
        this.checkStackSize = checkStackSize;
        this.checkItem = checkItem;
        this.checkDamage = checkDamage;
        this.checkNbt = checkNbt;
    }

    @Override
    public boolean test(@Nullable ItemStack input) {
        for (ValueObjectTypeItemStack.ValueItemStack itemStack : itemStacks) {
            if (!itemStack.getRawValue().isEmpty()
                    && TunnelItemHelpers.areItemStackEqual(input, itemStack.getRawValue(), checkStackSize, checkItem, checkDamage, checkNbt)) {
                return !blacklist;
            }
        }
        return blacklist;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof IngredientPredicateItemStackList)) {
            return false;
        }
        IngredientPredicateItemStackList that = (IngredientPredicateItemStackList) obj;
        return super.equals(obj)
                && this.blacklist == that.blacklist
                && this.checkItem == that.checkItem
                && this.checkStackSize == that.checkStackSize
                && this.checkDamage == that.checkDamage
                && this.checkNbt == that.checkNbt
                && this.itemStacks.equals(that.itemStacks);
    }

    @Override
    public int hashCode() {
        return super.hashCode()
                ^ (this.blacklist ? 1 : 0) << 1
                ^ (this.checkItem ? 1 : 0) << 2
                ^ (this.checkStackSize ? 1 : 0) << 3
                ^ (this.checkDamage ? 1 : 0) << 4
                ^ (this.checkNbt ? 1 : 0) << 5
                ^ this.itemStacks.hashCode();
    }
}
