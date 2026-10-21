
package com.fallinghazards;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Point;

import javax.inject.Inject;

import net.runelite.api.GraphicsObject;
import net.runelite.api.LocalPoint;
import net.runelite.api.WorldView;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayUtil;
import net.runelite.api.Perspective;

class FallingHazardsOverlay extends Overlay
{
    private final FallingHazardsPlugin plugin;

    @Inject
    FallingHazardsOverlay(FallingHazardsPlugin plugin)
    {
        this.plugin = plugin;
        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ABOVE_SCENE);
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        FallingHazardsConfig config = plugin.getConfig();
        Color color = config.outlineColor();

        graphics.setColor(color);
        graphics.setStroke(new BasicStroke(
            Math.max(1, Math.min(8, config.outlineWidth()))
        ));

        WorldView worldView = plugin.getClient().getTopLevelWorldView();

        for (GraphicsObject effect : worldView.getGraphicsObjects())
        {
            LocalPoint location = effect.getLocation();

            if (location == null || effect.getLevel() != worldView.getPlane())
            {
                continue;
            }

            Polygon tile = Perspective.getCanvasTilePoly(
                plugin.getClient(), location
            );

            if (tile == null)
            {
                continue;
            }

            graphics.drawPolygon(tile);

            if (config.showGraphicsObjectIds())
            {
                Point textPoint = Perspective.getCanvasTextLocation(
                    plugin.getClient(),
                    graphics,
                    location,
                    Integer.toString(effect.getId()),
                    0
                );

                if (textPoint != null)
                {
                    OverlayUtil.renderTextLocation(
                        graphics,
                        textPoint,
                        Integer.toString(effect.getId()),
                        color
                    );
                }
            }
        }

        return null;
    }
}
