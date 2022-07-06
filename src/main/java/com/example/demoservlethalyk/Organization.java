package com.example.demoservlethalyk;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDate;

public class Organization {
    public Organization() {
    }

    public long getId() {
        return id;
    }

    public Organization( long id, String title, String address, LocalDate creationDate) {

        this.id = id;
        this.title = title;
        this.address = address;

        this.creationDate = creationDate;

    }

    public String getTitle() {
        return title;
    }

    public String getAddress() {
        return address;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    private  long id;
    private  String title;
    private  String address;
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate creationDate;

}
