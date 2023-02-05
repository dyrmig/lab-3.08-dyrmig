package com.dmitri.lab308.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Conference extends Event {
    @ManyToMany(mappedBy = "conferenceList")
    private List<Speaker> speakerList;

    public Conference() {
    }
    public Conference(String date, String duration, String location, String title) {
        super(date, duration, location, title);
    }

    public List<Speaker> getSpeakerList() {
        return speakerList;
    }

    public void setSpeakerList(List<Speaker> speakerList) {
        this.speakerList = speakerList;
    }
}
