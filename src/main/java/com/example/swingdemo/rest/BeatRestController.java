package com.example.swingdemo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.swingdemo.model.Beat;
import com.example.swingdemo.service.BeatService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class BeatRestController {

	@Autowired
	BeatService beatService;

	@GetMapping("/ping")
	public String getPing() {
		return "BeatRestController am Start";
	}

	@GetMapping(path = "/getDbEntries", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getDbEntries() {
		return beatService.entriesToJsonArray();
	}

	@GetMapping(path = "/getNumberOfEntries", produces = MediaType.APPLICATION_JSON_VALUE)
	public int getNumberOfEntries() {
		return beatService.list().size();
	}

	@PostMapping("/persist")
	public ResponseEntity<String> persistBeat(@RequestBody Beat beat) {
		return beatService.persist(beat);
	}
	
    @PostMapping("/update")
    public ResponseEntity<String> updateBeat(@RequestBody Beat updatedBeat) {
    	return beatService.update(updatedBeat);
    }

	@GetMapping("/delete")
	public ResponseEntity<String> delete(@RequestParam(name = "id") Long id) {
		return beatService.delete(id);
	}

}
