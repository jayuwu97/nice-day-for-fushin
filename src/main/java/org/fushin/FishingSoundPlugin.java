package org.fushin;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.events.AnimationChanged;
import net.runelite.client.audio.AudioPlayer;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import javax.inject.Inject;

@Slf4j
@PluginDescriptor(
		name = "Nice day for fushin', ain't it?",
		description = "Plays sound clip from legendary NPC Baelin everytime you fish."

)
public class FishingSoundPlugin extends Plugin {
	@Inject
	private Client client;

	@Inject
	private AudioPlayer audioPlayer;

	@Override
	protected void startUp() {
		log.info("Fishing Sound started!");


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
				audioPlayer.play(getClass().getResourceAsStream("/fushin.wav"), 1.0f);
			} catch (Exception e) {
				log.error("Unable to play fishing sound", e);
			}
			}
		}
	}