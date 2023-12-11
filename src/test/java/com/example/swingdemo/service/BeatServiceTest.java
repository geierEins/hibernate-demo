package com.example.swingdemo.service;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.swingdemo.model.Beat;

@SpringBootTest
public class BeatServiceTest {
	
    @Autowired
    private BeatService beatService;
    
    @Test
    public void whenApplicationStarts_checkInitialEntries() {
        List<Beat> beats = beatService.list();
        beats.forEach(beat -> System.out.print(beat));
        System.out.println("\nAnzahl der Datensätze: " + beats.size());
        Assert.assertEquals(beats.size(), 3);
    }

}
