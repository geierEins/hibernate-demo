package com.example.swingdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.swingdemo.model.Beat;

public interface BeatRepository extends JpaRepository<Beat, Long> {

}
