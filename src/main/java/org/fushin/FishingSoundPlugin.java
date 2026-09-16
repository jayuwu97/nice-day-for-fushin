package org.fushin;

import net.runelite.api.Client;
import net.runelite.api.events.AnimationChanged;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import javax.inject.Inject;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
@PluginDescriptor(
		name = "Nice day for fushin', ain't it?",
		description = "Plays sound clip from legendary NPC Baelin everytime you fish."

)
public class FishingSoundPlugin extends Plugin {
	@Inject
	private Client client;

	private Clip fishingSound;

	@Override
	protected void startUp() {
		System.out.println("Fishing Sound started!");

		try {
			fishingSound = AudioSystem.getClip();
			fishingSound.open(AudioSystem.getAudioInputStream(
					getClass().getResourceAsStream("/fushin.wav")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void shutDown() {
		System.out.println("Fishing Sound stopped!");
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
			if (fishingSound != null) {
				fishingSound.setFramePosition(0);
				fishingSound.start();
			}
		}
	}
}