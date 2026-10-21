
package com.fallinghazards;

import com.google.inject.Provides;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.GraphicsObject;
import net.runelite.api.WorldView;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@PluginDescriptor(
    name = "Falling Hazards",
    description = "Highlights tiles occupied by visible graphics effects"
)
public class FallingHazardsPlugin extends Plugin
{
    @Inject
    private Client client;

    @Inject
    private OverlayManager overlayManager;

    @Inject
    private FallingHazardsOverlay overlay;

    @Inject
    private FallingHazardsConfig config;

    @Provides
    FallingHazardsConfig provideConfig(ConfigManager configManager)
    {
        return configManager.getConfig(FallingHazardsConfig.class);
    }

    @Override
    protected void startUp()
    {
        overlayManager.add(overlay);
    }

    @Override
    protected void shutDown()
    {
        overlayManager.remove(overlay);
    }

    Client getClient()
    {
        return client;
    }

    FallingHazardsConfig getConfig()
    {
        return config;
    }
}
