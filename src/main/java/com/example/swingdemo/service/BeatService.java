package com.example.swingdemo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.swingdemo.model.Beat;
import com.example.swingdemo.repository.BeatRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BeatService {

	@Autowired
	BeatRepository beatRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(BeatService.class);

	public List<Beat> list() {
		return beatRepository.findAll();
	}
	
	public ResponseEntity<String> persist(Beat beat) {
		try {
			beatRepository.save(beat);
			logger.info("Beat mit id {} erfolgreich persistiert", beat.getId());
			return new ResponseEntity<>("Beat erfolgreich gelöscht", HttpStatus.ACCEPTED);
		}catch(Exception e) {
			logger.error("Beat mit id {} konnte nicht persistiert werden.", beat.getId());
			return new ResponseEntity<>("Fehler beim Persistieren des Beats", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<String> delete(Long id) {
		try {
			beatRepository.deleteById(id);
			logger.info("Beat mit id {} erfolgreich gelöscht", id);
			return new ResponseEntity<>("Beat erfolgreich gelöscht", HttpStatus.ACCEPTED);
		}catch(Exception e) {
			logger.error("Beat mit id {} konnte nicht gelöscht werden.", id);
			return new ResponseEntity<>("Fehler beim Löschen des Beats", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public String entriesToJsonArray() {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.writeValueAsString(list());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}

}
