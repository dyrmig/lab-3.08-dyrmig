package com.dmitri.lab308.repository;

import com.dmitri.lab308.enums.GuestStatus;
import com.dmitri.lab308.model.Conference;
import com.dmitri.lab308.model.Exposition;
import com.dmitri.lab308.model.Guest;
import com.dmitri.lab308.model.Speaker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ConferenceRepositoryTest {
    @Autowired
    private ConferenceRepository conferenceRepository;
    @Autowired
    private GuestRepository guestRepository;
    @Autowired
    private SpeakerRepository speakerRepository;
    private Conference conference1;
    private Guest guest1;
    private List<Guest> guestList;
    private Speaker speaker1;
    private List<Speaker> speakerList;
    private List<Conference> conferenceList;
    @BeforeEach
    void setUp() {
        conference1 = new Conference("22/03/2023","4 days","Barcelona","Java Expo");
        conferenceRepository.save(conference1);
        guest1 = new Guest("Dmitri", GuestStatus.ATTENDING, conference1);
        guestRepository.save(guest1);
        guestList = new ArrayList<Guest>();
        guestList.add(guest1);
        speaker1 = new Speaker("Dr. Pepe", "1 hour");
        conferenceList = new ArrayList<Conference>();
        conferenceList.add(conference1);
        speaker1.setConferenceList(conferenceList);
        speakerRepository.save(speaker1);
        speakerList = new ArrayList<Speaker>();
        speakerList.add(speaker1);
        conference1.setGuestList(guestList);
        conference1.setSpeakerList(speakerList);
        conferenceRepository.save(conference1);
    }

    @AfterEach
    void tearDown() {
        guest1.setEvent(null);
        speaker1.setConferenceList(null);
        conference1.setSpeakerList(null);
        conference1.setGuestList(null);
        guestRepository.deleteAll();
        speakerRepository.deleteAll();
        conferenceRepository.deleteAll();
    }

    @Test
    void add_guest_addGuest(){
        Optional<Guest> optionalGuest = guestRepository.findById(guest1.getId());
        assertEquals("Dmitri",optionalGuest.get().getName());
    }

    @Test
    void conferenceWithAllGuests() {
        Conference optionalConference = conferenceRepository.conferenceWithAllGuests(conference1.getId());
        assertEquals(1,optionalConference.getGuestList().size());
    }

    @Test
    void conferenceWithAllSpeakers() {
        Conference optionalConference = conferenceRepository.conferenceWithAllSpeakers(conference1.getId());
        assertEquals("Dr. Pepe",optionalConference.getSpeakerList().get(0).getName());
    }
}