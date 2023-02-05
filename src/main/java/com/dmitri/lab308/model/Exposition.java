package com.dmitri.lab308.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Exposition extends Event {
    public Exposition() {
    }

    public Exposition(String date, String duration, String location, String title) {
        super(date, duration, location, title);
    }

}
