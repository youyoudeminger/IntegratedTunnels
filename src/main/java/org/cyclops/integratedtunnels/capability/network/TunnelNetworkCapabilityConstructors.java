package org.cyclops.integratedtunnels.capability.network;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import org.cyclops.cyclopscore.modcompat.capabilities.DefaultCapabilityProvider;
import org.cyclops.integrateddynamics.Reference;
import org.cyclops.integrateddynamics.api.network.AttachCapabilitiesEventNetwork;
import org.cyclops.integratedtunnels.api.network.IItemNetwork;
import org.cyclops.integratedtunnels.core.network.ItemNetwork;

/**
 * Constructor event for network capabilities.
 * @author rubensworks
 */
public class TunnelNetworkCapabilityConstructors {

    @SubscribeEvent
    public void onNetworkLoad(AttachCapabilitiesEventNetwork event) {
        ItemNetwork itemNetwork = new ItemNetwork();
        event.addCapability(new ResourceLocation(Reference.MOD_ID, "itemNetwork"),
                new DefaultCapabilityProvider<IItemNetwork>(ItemNetworkConfig.CAPABILITY, itemNetwork));
        event.addCapability(new ResourceLocation(Reference.MOD_ID, "itemStorageNetwork"),
                new DefaultCapabilityProvider<IItemHandler>(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, itemNetwork));
    }

}
