package org.fushin;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.events.AnimationChanged;
import net.runelite.client.audio.AudioPlayer;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import com.google.inject.Provides;
import net.runelite.client.config.ConfigManager;

import javax.inject.Inject;

@Slf4j
@PluginDescriptor(
		name = "Nice day for fushin', ain't it?",
		description = "Plays sound clip from legendary NPC Baelin everytime you fish.",
		tags = {"fishing", "sound", "baelin"}
)
public class FishingSoundPlugin extends Plugin {
	@Inject
	private Client client;

	@Inject
	private AudioPlayer audioPlayer;

	@Inject
	private FishingSoundConfig config;

	@Override
	protected void startUp() {
		log.info("Fishing Sound started!");


	}

	@Provides
	FishingSoundConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(FishingSoundConfig.class);
	}

	@Override
	protected void shutDown() {
		log.info("Fishing Sound stopped!");
	}

	@Subscribe
	public void onAnimationChanged(AnimationChanged event) {
		if (event.getActor() != client.getLocalPlayer()) {
			return;
		}

		if (event.getActor().getAnimation() == 622
				|| event.getActor().getAnimation() == 9349
				|| event.getActor().getAnimation() == 6703
				|| event.getActor().getAnimation() == 619
				|| event.getActor().getAnimation() == 618
				|| event.getActor().getAnimation() == 621
				|| event.getActor().getAnimation() == 620) {
			try {
				float volume = config.volume();

				float gain = volume == 0
						? -80.0f
						: -60.0f + (volume / 100.0f) * 60.0f;

				audioPlayer.play(
						getClass().getResourceAsStream("/fushin.wav"),
						gain
				);
			} catch (Exception e) {
				log.error("Unable to play fishing sound", e);
			}
			}
		}
	}