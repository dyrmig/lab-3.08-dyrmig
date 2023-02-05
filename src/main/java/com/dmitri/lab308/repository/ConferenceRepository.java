package com.dmitri.lab308.repository;

import com.dmitri.lab308.model.Conference;
import com.dmitri.lab308.model.Exposition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ConferenceRepository extends JpaRepository<Conference, Long> {
    @Query("SELECT c FROM Conference c JOIN FETCH guestList WHERE c.id = :id")
    Conference conferenceWithAllGuests(@Param("id") Long id);
    @Query("SELECT c FROM Conference c JOIN FETCH speakerList WHERE c.id = :id")
    Conference conferenceWithAllSpeakers(@Param("id") Long id);
}
