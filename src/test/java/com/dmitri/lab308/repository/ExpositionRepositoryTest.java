package com.dmitri.lab308.repository;

import com.dmitri.lab308.enums.GuestStatus;
import com.dmitri.lab308.model.Exposition;
import com.dmitri.lab308.model.Guest;
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
class ExpositionRepositoryTest {
    @Autowired
    private ExpositionRepository expositionRepository;
    @Autowired
    private GuestRepository guestRepository;
    private Exposition exposition1;
    private Guest guest1;
    private List<Guest> guestList;
    @BeforeEach
    void setUp() {
        exposition1 = new Exposition("22/03/2023","4 days","Barcelona","Java Expo");
        expositionRepository.save(exposition1);
        guest1 = new Guest("Dmitri", GuestStatus.ATTENDING, exposition1);
        guestRepository.save(guest1);
        guestList = new ArrayList<Guest>();
        guestList.add(guest1);
        exposition1.setGuestList(guestList);
        expositionRepository.save(exposition1);
        System.out.println("------------------------------------------");
        System.out.println(exposition1.getId());
        System.out.println(guest1.getId());
        System.out.println("------------------------------------------");
    }

    @AfterEach
    void tearDown() {
        guest1.setEvent(null);
        guestRepository.deleteAll();
        exposition1.setGuestList(null);
        expositionRepository.deleteAll();

    }
    @Test
    void add_guest_addGuest(){
        Optional<Guest> optionalGuest = guestRepository.findById(guest1.getId());
        assertEquals("Dmitri",optionalGuest.get().getName());
    }
    @Test
    void add_guests_addGuestsToExposition(){
        Exposition optionalExposition = expositionRepository.expositionWithAllGuests(exposition1.getId());
        assertEquals("Dmitri",optionalExposition.getGuestList().get(0).getName());
    }
}