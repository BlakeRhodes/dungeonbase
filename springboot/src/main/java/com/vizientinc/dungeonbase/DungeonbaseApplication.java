package com.vizientinc.dungeonbase;

import com.vizientinc.dungeonbase.models.ItemRecord;
import com.vizientinc.dungeonbase.models.LocationRecord;
import com.vizientinc.dungeonbase.models.PlayerRecord;
import com.vizientinc.dungeonbase.repositories.ItemRepository;
import com.vizientinc.dungeonbase.repositories.LocationRepository;
import com.vizientinc.dungeonbase.repositories.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static java.util.Arrays.asList;

@SpringBootApplication
public class DungeonbaseApplication implements ApplicationRunner {

	final LocationRepository locationRepository;
	final ItemRepository itemRepository;
	final PlayerRepository playerRepository;

	private static final Logger LOG = LoggerFactory.getLogger(DungeonbaseApplication.class);

	public DungeonbaseApplication(
		LocationRepository locationRepository,
		ItemRepository itemRepository,
		PlayerRepository playerRepository
	) {this.locationRepository =
		locationRepository;
		this.itemRepository = itemRepository;
		this.playerRepository = playerRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(DungeonbaseApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args){
		LocationRecord start = locationRepository.findByName("Start");

		if(start == null){
			start = locationRepository.save(
				new LocationRecord(
					"Start",
					"Following the goblins’ trail, you come across a large cave in a hillside five miles from the scene of the ambush.",
					asList("Cave Mouth", "Goblin Blind")
				)
			);
			LocationRecord caveMouth = new LocationRecord(
				"Cave Mouth",
				"A shallow stream flows out of the cave mouth, which is screened by dense briar thickets. A narrow dry path leads into the cave on the right-hand side of the stream.",
				asList("Start", "Kennel", "Steep Passage", "Overpass")
			);
			locationRepository.save(caveMouth);
			ItemRecord itemRecord = new ItemRecord(null, "Bag of Holding", "This bag has an interior space considerably larger than its outside dimensions, roughly 2 feet in diameter at the mouth and 4 feet deep. The bag can hold up to 500 pounds, not exceeding a volume of 64 cubic feet. The bag weighs 15 pounds, regardless of its contents. Retrieving an item from the bag requires an action.", caveMouth.getId());
			itemRepository.save(itemRecord);
			LocationRecord goblinBlind = new LocationRecord(
				"Goblin Blind",
				"On the east side of the stream flowing from the cave mouth, a small area in the briar thickets has been hollowed out to form a lookout post or blind. Wooden planks flatten out the briars and provide room for guards to lie hidden and watch the area — including a pair of goblins lurking there right now!",
				asList("Start", "Cave Mouth")
			);
			 locationRepository.save(goblinBlind);
			LocationRecord kennel = new LocationRecord(
				"Kennel",
				"Just inside the cave mouth, a few uneven stone steps lead up to a small, dank chamber on the east side of the passage. The cave narrows to a steep fissure at the far end, and is filled with the stench of animals. Savage snarls and the sounds of rattling chains greet your ears where three wolves are chained up just inside the opening. Each wolf’s chain leads to an iron rod driven into the base of a stalagmite.",
				asList("Cave Mouth", "Klarg's Cave")
			);
			locationRepository.save(kennel);
			LocationRecord steepPassage = new LocationRecord(
				"Steep Passage",
				"The main passage from the cave mouth climbs steeply upward, the stream plunging and splashing down its west side. In the shadows, a side passage leads west across the other side of the stream. In the shadows of the ceiling to the north, you can just make out the dim shape of a rickety bridge of wood and rope crossing over the passage ahead of you. Another passage intersects this one, twenty feet above the floor.",
				asList("Cave Mouth", "Goblin Den")
			);
			locationRepository.save(steepPassage);
			LocationRecord overpass = new LocationRecord(
				"Overpass",
				"The stream passage continues up beyond another set of uneven steps ahead, bending eastward as it goes. A waterfall sounds out from a larger cavern somewhere ahead of you.",
				asList("Goblin Den", "Twin Pools", "Cave Mouth")
			);
			locationRepository.save(overpass);
			LocationRecord goblinDen = new LocationRecord(
				"Goblin Den",
				"This large cave is divided in half by a ten-foot-high escarpment. A steep natural staircase leads from the lower portion to the upper ledge. The air is hazy with the smoke of a cooking fire, and pungent from the smell of poorly cured hides and unwashed goblins.",
				asList("Steep Passage", "Overpass")
			);
			locationRepository.save(goblinDen);
			LocationRecord twinPools = new LocationRecord(
				"Twin Pools",
				"This cavern is half filled with two large pools of water. A narrow waterfall high in the eastern wall feeds the pool, which drains out the western end of the chamber to form the stream that flows out of the cave mouth below. Low fieldstone walls serve as dams holding the water in. A wide exit stands to the south, while two smaller passages lead west. The sound of the waterfall echoes through the cavern, making it difficult to hear.",
				asList("Klarg's Cave", "Overpass")
			);
			locationRepository.save(twinPools);
			LocationRecord klargsCave = new LocationRecord(
				"Klarg's Cave",
				"Sacks and crates of looted provisions are piled up in the south end of this large cave. To the west, the floor slopes toward a narrow opening that descends into darkness. A larger opening leads north down a set of natural stone steps, the roar of falling water echoing from beyond. In the middle of the cavern, the coals of a large fire smolder.",
				asList("Kennel", "Twin Pools")
			);
			locationRepository.save(klargsCave);
			LOG.info("Start not found creating one with id " + start.getId());
			PlayerRecord fatherSimon = new PlayerRecord(
				null, "Father Simon", caveMouth.getId()
			);
			fatherSimon = playerRepository.save(fatherSimon);
			ItemRecord rodOfThePactkeeper = new ItemRecord(
				null, "Rod of the Pact Keeper", "Gives warlocks +2 to spell attacks and DCs.", fatherSimon.getId()
			);
			itemRepository.save(rodOfThePactkeeper);

		} else {
			LOG.info("Start found with id " + start.getId());
		}
		LOG.info("http://localhost:8080/v1/locations/" + start.getId());
	}
}
