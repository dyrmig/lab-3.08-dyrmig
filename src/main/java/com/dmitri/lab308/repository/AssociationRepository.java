package com.dmitri.lab308.repository;

import com.dmitri.lab308.model.Association;
import com.dmitri.lab308.model.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssociationRepository extends JpaRepository<Association, Long> {
    @Query("SELECT a FROM Association a JOIN FETCH a.chapters WHERE a.id = :id")
    Optional<Association> findByIdWithChapters(@Param("id") Long id);
}
