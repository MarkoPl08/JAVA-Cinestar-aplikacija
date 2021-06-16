/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Marko
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "id", "title", "genre", "publishedDate", "picturePath", "description", "duration", "actors", "directors"})
public class Movie {
    
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;
    
    @XmlAttribute
    private int id;
    @XmlElement(name = "title")
    private  String title;
    @XmlElement(name = "genre")
    private  String genre;
    @XmlElement(name = "publishedDate")
    private  LocalDateTime publishedDate;
    @XmlElement(name = "picturePath")
    private  String picturePath;
    @XmlElement(name = "description")
    private  String description;
    @XmlElement(name = "duration")
    private  int duration;
    @XmlElementWrapper
    @XmlElement(name = "actor")
    private List<Actor> actors;
    @XmlElementWrapper
    @XmlElement(name = "director")
    private List<Director> directors;

    public Movie() {
    }

    public Movie(String title, String genre, LocalDateTime publishedDate, String picturePath, String description, int duration, List<Actor> actors, List<Director> directors) {
        this.title = title;
        this.genre = genre;
        this.publishedDate = publishedDate;
        this.picturePath = picturePath;
        this.description = description;
        this.duration = duration;
        this.actors = actors;
        this.directors = directors;
    }

    public Movie(int id, String title, String genre, LocalDateTime publishedDate, String picturePath, String description, int duration, List<Actor> actors, List<Director> directors) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.publishedDate = publishedDate;
        this.picturePath = picturePath;
        this.description = description;
        this.duration = duration;
        this.actors = actors;
        this.directors = directors;
    }

    public Movie(int id, String title, String genre, LocalDateTime publishedDate, String picturePath, String description, int duration) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.publishedDate = publishedDate;
        this.picturePath = picturePath;
        this.description = description;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public LocalDateTime getPublishedDate() {
        return publishedDate;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public String getDescription() {
        return description;
    }

    public int getDuration() {
        return duration;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public List<Director> getDirectors() {
        return directors;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPublishedDate(LocalDateTime publishedDate) {
        this.publishedDate = publishedDate;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public void setDirectors(List<Director> directors) {
        this.directors = directors;
    }

    @Override
    public String toString() {
        return "Movie{" + "title=" + title + ", genre=" + genre + ", publishedDate=" + publishedDate + ", picturePath=" + picturePath + ", description=" + description + ", duration=" + duration + ", actors=" + actors + ", directors=" + directors + '}';
    }     
    
    
}
