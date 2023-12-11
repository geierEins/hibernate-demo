package com.example.swingdemo.service;

import java.util.List;
import java.util.Optional;

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
			String msg = "Beat mit id %d erfolgreich persistiert".formatted(beat.getId());
			logger.info(msg);
			return new ResponseEntity<>(msg, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			String msg = "Beat mit id %d konnte nicht persistiert werden".formatted(beat.getId());
			logger.error(msg);
			return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<String> delete(Long id) {
		try {
			beatRepository.deleteById(id);
			String msg = "Beat mit id %d erfolgreich gelöscht".formatted(id);
			logger.info(msg);
			return new ResponseEntity<>("msg", HttpStatus.ACCEPTED);
		} catch (Exception e) {
			String msg = "Beat mit id %d konnte nicht gelöscht werden".formatted(id);
			logger.error(msg);
			return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<String> update(Beat beatWithUpdateFields) {
		if (beatWithUpdateFields.getId() == null)
			return new ResponseEntity<>("id fehlt in request", HttpStatus.INTERNAL_SERVER_ERROR);
		Beat existingBeat;
		try {
			existingBeat = beatRepository.findById(beatWithUpdateFields.getId()).get();
		} catch (Exception e) {
			String msg = "Beat mit id %d ist nicht in der Datenbank".formatted(beatWithUpdateFields.getId());
			return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		Beat updatedBeat = getUpdatedBeat(existingBeat, beatWithUpdateFields);
		return persist(updatedBeat);
	}

	private Beat getUpdatedBeat(Beat existingBeat, Beat beatWithUpdateFields) {
		Beat updatedBeat = new Beat();
		updatedBeat.setId(beatWithUpdateFields.getId());
		Optional<String> newName = Optional.ofNullable(beatWithUpdateFields.getName());
		Optional<Integer> newBpm = Optional.ofNullable(beatWithUpdateFields.getBpm());
		updatedBeat.setName(newName.orElse(existingBeat.getName()));
		updatedBeat.setBpm(newBpm.orElse(existingBeat.getBpm()));
		return updatedBeat;
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
