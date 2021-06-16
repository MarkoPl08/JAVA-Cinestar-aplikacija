/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dall;

import hr.algebra.models.Actor;
import hr.algebra.models.Director;
import hr.algebra.models.Movie;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Marko
 */
public interface Repository {
    int createActor(Actor actor) throws Exception;
    void createActors(List<Actor> actors) throws Exception;
    void updateActor(int id, Actor data) throws Exception;
    void deleteActor(int id) throws Exception;
    Optional<Actor> selectActor(int id) throws Exception;
    List<Actor> selectActors() throws Exception;

    int createDirector(Director director) throws Exception;
    void createDirectors(List<Director> directors) throws Exception;    
    void updateDirector(int id, Director data) throws Exception;
    void deleteDirector(int id) throws Exception;
    Optional<Director> selectDirector(int id) throws Exception;
    List<Director> selectDirectors() throws Exception;

    int createMovie(Movie movie) throws Exception;
    void createMovies(List<Movie> movies) throws Exception;    
    void updateMovie(int id, Movie data) throws Exception;
    void deleteMovie(int id) throws Exception;
    Optional<Movie> selectMovie(int id) throws Exception;
    List<Movie> selectMovies() throws Exception;
    
    void createActorMovie(int idActor, int idMovie) throws Exception;
    void createDirectorMovie(int idDirector, int idMovie) throws Exception;
    
    void deleteActorsFromMovie(int id)throws Exception;
    void deleteDirectorsFromMovie(int id)throws Exception;
    
    List<Actor> selectActorsFromMovie(int id)throws Exception;
    List<Director> selectDirectorsFromMovie(int id)throws Exception;
    
    void deleteEverything() throws Exception;

    int createUser(String username, String password) throws Exception;
    int checkUser(String username, String password) throws Exception;
}
