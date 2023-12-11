package com.example.swingdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.swingdemo.model.Beat;

public interface BeatRepository extends JpaRepository<Beat, Long> {

    @Modifying
    @Query("UPDATE Beat b SET b.name = :name WHERE b.id = :id")
    int updateBeatNameById(Long id, String name);
    
    @Modifying
    @Query("UPDATE Beat b SET b.bpm = :bpm WHERE b.id = :id")
    int updateBeatBpmById(Long id, int bpm);
	
}
