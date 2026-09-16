package org.fushin;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Range;

@ConfigGroup("fishingsound")
public interface FishingSoundConfig extends Config
{
    @Range(min = 0, max = 100)
    @ConfigItem(
            keyName = "volume",
            name = "Volume",
            description = "Volume of the fishing sound"
    )
    default int volume()
    {g
        return 100;
    }
}