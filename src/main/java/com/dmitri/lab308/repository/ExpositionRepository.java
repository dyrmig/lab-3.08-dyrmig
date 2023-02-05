package com.dmitri.lab308.repository;

import com.dmitri.lab308.model.Exposition;
import com.dmitri.lab308.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpositionRepository extends JpaRepository<Exposition, Long> {
    @Query("SELECT e FROM Exposition e JOIN FETCH guestList WHERE e.id = :id")
    Exposition expositionWithAllGuests(@Param("id") Long id);
}
