
package com.fallinghazards;

import java.awt.Color;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("fallinghazards")
public interface FallingHazardsConfig extends Config
{
    @ConfigItem(
        keyName = "outlineColor",
        name = "Tile outline color",
        description = "Color used for visible graphics effect tiles"
    )
    default Color outlineColor()
    {
        return Color.RED;
    }

    @ConfigItem(
        keyName = "outlineWidth",
        name = "Outline width",
        description = "Width of the tile outline in pixels"
    )
    default int outlineWidth()
    {
        return 2;
    }

    @ConfigItem(
        keyName = "showGraphicsObjectIds",
        name = "Show effect IDs",
        description = "Display graphics object IDs for debugging"
    )
    default boolean showGraphicsObjectIds()
    {
        return false;
    }
}
