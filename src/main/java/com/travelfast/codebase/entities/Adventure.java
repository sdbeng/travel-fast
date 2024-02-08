package com.travelfast.codebase.entities;

import jakarta.persistence.*;

@Entity
@Table(name="ADVENTURES")
public class Adventure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="DATE")
    private String date;

    @Column(name="COUNTRY")
    private String country;

    @Column(name="CITY")
    private String city;

    @Column(name="STATE")
    private String state;

    @Column(name="NUM_PHOTOS")
    private long numPhotos;

    @Column(name="BLOG_COMPLETED")
    private Boolean blogCompleted;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getNumPhotos() {
        return this.numPhotos;
    }

    public void setNumPhotos(long numPhotos) {
        this.numPhotos = numPhotos;
    }

    public Boolean getBlogCompleted() {
        return this.blogCompleted;
    }

    public void setBlogCompleted(Boolean blogCompleted) {
        this.blogCompleted = blogCompleted;
    }

    public boolean isBlogCompleted() {
        return this.blogCompleted;
    }
}
