/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.parser.rss;

import hr.algebra.factory.ParserFactory;
import hr.algebra.factory.UrlConnectionFactory;
import hr.algebra.models.Actor;
import hr.algebra.models.Director;
import hr.algebra.models.Movie;
import hr.algebra.utils.FileUtils;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author Marko
 */
public class MovieParser {
    
    private static final String RSS_URL = "https://www.blitz-cinestar.hr/rss.aspx?najava=1";
    private static final int TIMEOUT = 10000;
    private static final String REQUEST_METHOD = "GET";
    private static final String EXT = ".jpg";
    private static final String DIR = "assets" + File.separator + "movies";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.RFC_1123_DATE_TIME;
    private static final Random RANDOM = new Random();
    
    public static List<Movie> parse() throws IOException, XMLStreamException {
        List<Movie> movies = new ArrayList<>();
        HttpURLConnection con = UrlConnectionFactory.getHttpUrlConnection(RSS_URL, TIMEOUT, REQUEST_METHOD);
        XMLEventReader reader = ParserFactory.createStaxParser(con.getInputStream());

        Optional<TagType> tagType = Optional.empty();
        Movie movie = null;
        StartElement startElement = null;
        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            switch (event.getEventType()) {
                case XMLStreamConstants.START_ELEMENT:
                    startElement = event.asStartElement();
                    String qName = startElement.getName().getLocalPart();
                    tagType = TagType.from(qName);
                    break;
                case XMLStreamConstants.CHARACTERS:
                    if (tagType.isPresent()) {
                        Characters characters = event.asCharacters();
                        String data = characters.getData().trim();
                        switch (tagType.get()) {
                            case ITEM:
                                movie = new Movie();
                                movies.add(movie);                                
                                break;
                            case TITLE:
                                if (movie != null && !data.isEmpty()) {
                                    movie.setTitle(data);
                                }
                                break;
                            case PUB_DATE:
                                if (movie != null && !data.isEmpty()) {
                                    LocalDateTime publishedDate = LocalDateTime.parse(data, DATE_FORMATTER);
                                    movie.setPublishedDate(publishedDate);
                                }
                                break;
                            case DESCRIPTION:
                                if (movie != null && !data.isEmpty()) {
                                    movie.setDescription(filterDescription(data));
                                }
                                break;
                            case REDATELJ:
                                if (movie != null && !data.isEmpty()) {
                                    movie.setDirectors(getDirectors(data));
                                }
                                break;
                            case GLUMCI:
                                if (movie != null && !data.isEmpty()) {
                                    movie.setActors(getActors(data));
                                }
                                break;
                            case TRAJANJE:
                                if (movie != null && !data.isEmpty()) {
                                    movie.setDuration(Integer.parseInt(data));
                                }
                                break;
                            case ZANR:
                                if (movie != null && !data.isEmpty()) {
                                    movie.setGenre(data);
                                }
                                break;
                            case PLAKAT:
                                if (movie != null && !data.isEmpty()) {
                                   handlePicture(movie, data.trim());
                                }
                                break;
                        }
                    }
                    break;
            }
        }
        return movies;
    }
    
    private static void handlePicture(Movie movie, String pictureUrl) throws IOException
    {
        String ext = pictureUrl.substring(pictureUrl.lastIndexOf("."));
        if (ext.length() > 4) {
            ext = EXT;
        }
        String pictureName = Math.abs(RANDOM.nextInt()) + ext;
        String localPicturePath = DIR + File.separator + pictureName;

        FileUtils.copyFromUrl(pictureUrl, localPicturePath);
        movie.setPicturePath(localPicturePath);
    }
    
    private static List<Director> getDirectors(String data)
    {            
        String[] directors = filterPeople(data);
        List<Director> allDirectors = new ArrayList<Director>();
        for (String director : directors) {
            allDirectors.add(new Director(director));
        }
        return allDirectors;
    }
    private static List<Actor> getActors(String data)
    {
            
        String[] actors = filterPeople(data);
        List<Actor> allActors = new ArrayList<Actor>();
        for (String actor : actors) {
            allActors.add(new Actor(actor));
        }
        return allActors;
    }
    private static String[] filterPeople(String data)
    {
                
        String[] people = data.split(", ");
        
        return people;     
    }
    private static String filterDescription(String data)
    {
        return data;
    }
    private enum TagType
    {
        ITEM("item"),
        TITLE("title"),
        PUB_DATE("pubDate"),
        DESCRIPTION("description"),
        REDATELJ("redatelj"),
        GLUMCI("glumci"),
        TRAJANJE("trajanje"),
        ZANR("zanr"),
        PLAKAT("plakat");

        private final String name;

        private TagType(String name) {
            this.name = name;
        }

        private static Optional<TagType> from(String name) {
            for (TagType value : values()) {
                if (value.name.equals(name)) {
                    return Optional.of(value);
                }
            }
            return Optional.empty();
        }
    }
}
