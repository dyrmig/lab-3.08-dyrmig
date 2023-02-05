package com.dmitri.lab308.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Speaker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String presentationDuration;
    @ManyToMany
    @JoinTable(
            name = "speaker_conference", //definimos el nomobre de la tabla de relaciona
            joinColumns = {@JoinColumn(name = "speaker_id")},
            inverseJoinColumns = {@JoinColumn(name = "conference_id")}
    )
    private List<Conference> conferenceList;

    public Speaker() {
    }

    public Speaker(String name, String presentationDuration) {
        this.name = name;
        this.presentationDuration = presentationDuration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPresentationDuration() {
        return presentationDuration;
    }

    public void setPresentationDuration(String presentationDuration) {
        this.presentationDuration = presentationDuration;
    }

    public List<Conference> getConferenceList() {
        return conferenceList;
    }

    public void setConferenceList(List<Conference> conferenceList) {
        this.conferenceList = conferenceList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
