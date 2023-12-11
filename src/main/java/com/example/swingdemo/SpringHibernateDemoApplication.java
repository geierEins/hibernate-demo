package com.example.swingdemo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.swingdemo.model.Beat;
import com.example.swingdemo.service.BeatService;

@SpringBootApplication
public class SpringHibernateDemoApplication implements CommandLineRunner {
	
	@Autowired
	BeatService beatService;

	public static void main(String[] args) {
		SpringApplication.run(SpringHibernateDemoApplication.class, args);
	}
	
	@Override
    public void run(String... args) throws Exception {
        creatInitialDbEntries();
    }

	public void creatInitialDbEntries() {
		List<Beat> beats = new ArrayList<Beat>();
		beats.add(new Beat("The Trail", 80));
        beats.add(new Beat("New York", 90));
        beats.add(new Beat("Trap Beat", 132));
        beats.forEach(beat -> beatService.persist(beat));
	}
	
}
