package com.vizientinc.dungeonbase;

import com.vizientinc.dungeonbase.models.Event;
import com.vizientinc.dungeonbase.repositories.EventRepository;
import com.vizientinc.dungeonbase.requests.NewEventRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DungeonbaseApplication implements ApplicationRunner {

	@Autowired
	EventRepository eventRepository;

	private static final Logger LOG = LoggerFactory.getLogger(DungeonbaseApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DungeonbaseApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Event start = eventRepository.findByType("Start");

		if(start == null){
			start = eventRepository.save(
				new Event("Welcome to the Dungeonbase", null, "Start")
			);
			eventRepository.save(
				new Event(
					new NewEventRequest(
						"The Window",
						start.getId(),
						"Location",
						null,
						null
					)
				)
			);
			eventRepository.save(
				new Event(
					new NewEventRequest(
						"The Elevator",
						start.getId(),
						"Location",
						null,
						null
					)
				)
			);
			LOG.info("Start not found creating one with id " + start.getId());
		} else {
			LOG.info("Start found with id " + start.getId());
		}
	}
}
