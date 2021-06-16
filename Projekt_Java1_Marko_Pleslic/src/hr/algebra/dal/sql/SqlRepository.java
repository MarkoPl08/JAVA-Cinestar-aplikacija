/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dal.sql;

import hr.algebra.dall.Repository;
import hr.algebra.models.Actor;
import hr.algebra.models.Director;
import hr.algebra.models.Movie;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;

/**
 *
 * @author Marko
 */
public class SqlRepository implements Repository {

    private static final String ID_MOVIE = "IDMovie";
    private static final String TITLE = "Title";
    private static final String GENRE = "Genre";
    private static final String PUBLISHED_DATE = "PublishedDate";
    private static final String PICTURE_PATH = "PicturePath";
    private static final String DESCRIPTION = "Description";
    private static final String DURATION = "Duration";
    
    private static final String ID_ACTOR = "IDActor";    
    private static final String ID_DIRECTOR = "IDDirector";
    private static final String NAME = "Name";
       
    private static final String CREATE_MOVIE = "{ CALL createMovie (?,?,?,?,?,?,?) }";
    private static final String UPDATE_MOVIE = "{ CALL updateMovie (?,?,?,?,?,?,?) }";
    private static final String DELETE_MOVIE = "{ CALL deleteMovie (?) }";
    private static final String SELECT_MOVIE = "{ CALL selectMovie (?) }";
    private static final String SELECT_MOVIES = "{ CALL selectMovies }";
    
    private static final String CREATE_ACTOR = "{ CALL createActor (?,?) }";
    private static final String UPDATE_ACTOR = "{ CALL updateActor (?,?) }";
    private static final String DELETE_ACTOR = "{ CALL deleteActor (?) }";
    private static final String SELECT_ACTOR = "{ CALL selectActor (?) }";
    private static final String SELECT_ACTORS = "{ CALL selectActors }";
    
    private static final String CREATE_DIRECTOR = "{ CALL createDirector (?,?) }";
    private static final String UPDATE_DIRECTOR = "{ CALL updateDirector (?,?) }";
    private static final String DELETE_DIRECTOR = "{ CALL deleteDirector (?) }";
    private static final String SELECT_DIRECTOR = "{ CALL selectDirector (?) }";
    private static final String SELECT_DIRECTORS = "{ CALL selectDirectors }";
    
    private static final String CREATE_ACTOR_MOVIE = "{ CALL createActorMovie (?,?) }";
    private static final String DELETE_ACTORS_FROM_MOVIE = "{ CALL deleteActorsFromMovie (?) }";
    private static final String SELECT_ACTORS_FROM_MOVIE = "{ CALL selectActorsFromMovie (?) }";
    
    private static final String CREATE_DIRECTOR_MOVIE = "{ CALL createDirectorMovie (?,?) }";
    private static final String DELETE_DIRECTORS_FROM_MOVIE = "{ CALL deleteDirectorsFromMovie (?) }";
    private static final String SELECT_DIRECTORS_FROM_MOVIE = "{ CALL selectDirectorsFromMovie (?) }";
    
    private static final String DELETE_EVERYTHING = "{ CALL deleteEverything }";
    
    private static final String CREATE_USER = "{ CALL createUser(?,?,?) }";
    
    
    @Override
    public int createActor(Actor actor) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_ACTOR)) {
            
            stmt.setString(1, actor.getName());
            stmt.registerOutParameter(2, Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt(2);
        }
    }

    @Override
    public void createActors(List<Actor> actors) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_ACTOR)) {

            for (Actor actor : actors) {
                stmt.setString(1, actor.getName());
                stmt.registerOutParameter(2, Types.INTEGER);
                
                stmt.executeUpdate();
            }
                
        } 
    }

    @Override
    public void updateActor(int id, Actor data) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_ACTOR)) {
            
                stmt.setInt(1, id);
                stmt.setString(2, data.getName());  

            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteActor(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_ACTOR)) {
            
                stmt.setInt(1, id);
            
            stmt.executeUpdate();
        } 
    }

    @Override
    public Optional<Actor> selectActor(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_ACTOR)) {
           
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                
                if (rs.next()) {
                    return Optional.of(new Actor(
                            rs.getInt(ID_ACTOR),
                            rs.getString(NAME)));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Actor> selectActors() throws Exception {
        List<Actor> actors = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_ACTORS);
                ResultSet rs = stmt.executeQuery()) {
          
            while (rs.next()) {
                actors.add(new Actor(
                                rs.getInt(ID_ACTOR),
                                rs.getString(NAME)));
            }
        } 
        return actors;
    }

    @Override
    public int createDirector(Director director) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_DIRECTOR)) {
            
            stmt.setString(1, director.getName());
            stmt.registerOutParameter(2, Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt(2);
        }

    }

    @Override
    public void createDirectors(List<Director> directors) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_DIRECTOR)) {

            for (Director director : directors) {
                stmt.setString(1, director.getName());
                stmt.registerOutParameter(2, Types.INTEGER);
                
                stmt.executeUpdate();
            }
        } 
        
    }

    @Override
    public void updateDirector(int id, Director data) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_DIRECTOR)) {
            
                stmt.setInt(1, id);
                stmt.setString(2, data.getName());                

            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteDirector(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_DIRECTOR)) {
            
                stmt.setInt(1, id);
            
            stmt.executeUpdate();
        } 
    }

    @Override
    public Optional<Director> selectDirector(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_DIRECTOR)) {
           
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                
                if (rs.next()) {
                    return Optional.of(new Director(
                            rs.getInt(ID_ACTOR),
                            rs.getString(NAME)));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Director> selectDirectors() throws Exception {
        List<Director> directors = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_DIRECTORS);
                ResultSet rs = stmt.executeQuery()) {
          
            while (rs.next()) {
                directors.add(new Director(
                                rs.getInt(ID_DIRECTOR),
                                rs.getString(NAME)));
            }
        } 
        return directors;
    }

    @Override
    public int createMovie(Movie movie) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_MOVIE)) {
            
            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getGenre());
            stmt.setString(3, movie.getPublishedDate().format(Movie.DATE_FORMATTER));
            stmt.setString(4, movie.getPicturePath());
            stmt.setString(5, movie.getDescription());
            stmt.setInt(6, movie.getDuration());
            stmt.registerOutParameter(7, Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt(7);
        }
    }

    @Override
    public void createMovies(List<Movie> movies) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_MOVIE)) {

            for (Movie movie : movies) {
                stmt.setString(1, movie.getTitle());
                stmt.setString(2, movie.getGenre());
                stmt.setString(3, movie.getPublishedDate().format(Movie.DATE_FORMATTER));
                stmt.setString(4, movie.getPicturePath());
                stmt.setString(5, movie.getDescription());
                stmt.setInt(6, movie.getDuration());
                stmt.registerOutParameter(7, Types.INTEGER);
                
                stmt.executeUpdate();
            }
        }
    }

    @Override
    public void updateMovie(int id, Movie data) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_MOVIE)) {
            
                stmt.setInt(1, id);
                stmt.setString(2, data.getTitle());
                stmt.setString(3, data.getGenre());
                stmt.setString(4, data.getPublishedDate().format(Movie.DATE_FORMATTER));
                stmt.setString(5, data.getPicturePath());
                stmt.setString(6, data.getDescription());
                stmt.setInt(7, data.getDuration());
                

            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteMovie(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_MOVIE)) {
            
                stmt.setInt(1, id);
            
                stmt.executeUpdate();
        } 
    }

    @Override
    public Optional<Movie> selectMovie(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_MOVIE)) {
           
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                
                if (rs.next()) {
                    return Optional.of(new Movie(
                            rs.getInt(ID_MOVIE),
                            rs.getString(TITLE),
                            rs.getString(GENRE),
                            LocalDateTime.parse(rs.getString(PUBLISHED_DATE), Movie.DATE_FORMATTER),
                            rs.getString(PICTURE_PATH),
                            rs.getString(DESCRIPTION),
                            rs.getInt(DURATION)));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Movie> selectMovies() throws Exception {
        List<Movie> movies = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_MOVIES);
                ResultSet rs = stmt.executeQuery()) {
          
            while (rs.next()) {
                movies.add(new Movie(
                                rs.getInt(ID_MOVIE),
                                rs.getString(TITLE),
                                rs.getString(GENRE),
                                LocalDateTime.parse(rs.getString(PUBLISHED_DATE), Movie.DATE_FORMATTER),
                                rs.getString(PICTURE_PATH),
                                rs.getString(DESCRIPTION),
                                rs.getInt(DURATION)));
            }
        } 
        return movies;
    }

    @Override
    public void deleteActorsFromMovie(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_ACTORS_FROM_MOVIE)) {
            
            stmt.setInt(1, id);
            
            stmt.executeUpdate();
        } 
    }

    @Override
    public void deleteDirectorsFromMovie(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_DIRECTORS_FROM_MOVIE)) {
            
            stmt.setInt(1, id);
            
            stmt.executeUpdate();
        } 
    }

    @Override
    public List<Actor> selectActorsFromMovie(int id) throws Exception {
        List<Actor> actors = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_ACTORS_FROM_MOVIE);
                ResultSet rs = stmt.executeQuery()) {
          
            while (rs.next()) {
                actors.add(new Actor(
                                rs.getInt(ID_ACTOR),
                                rs.getString(NAME)));
            }
        } 
        return actors;
    }

    @Override
    public List<Director> selectDirectorsFromMovie(int id) throws Exception {
        List<Director> directors = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_DIRECTORS_FROM_MOVIE);
                ResultSet rs = stmt.executeQuery()) {
          
            while (rs.next()) {
                directors.add(new Director(
                                rs.getInt(ID_DIRECTOR),
                                rs.getString(NAME)));
            }
        } 
        return directors;
    }

    @Override
    public void createActorMovie(int idActor, int idMovie) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_ACTOR_MOVIE)) {
            
            stmt.setInt(1, idActor);
            stmt.setInt(2, idMovie);

            stmt.executeUpdate();
        }
    }

    @Override
    public void createDirectorMovie(int idDirector, int idMovie) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_DIRECTOR_MOVIE)) {
            
            stmt.setInt(1, idDirector);
            stmt.setInt(2, idMovie);

            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteEverything() throws Exception {
        
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_EVERYTHING)) {
                        
            stmt.executeUpdate();
        } 
    }

    @Override
    public int createUser(String username, String password) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_USER)) {
            
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.registerOutParameter(3, Types.INTEGER);
            stmt.executeUpdate();
            
            return stmt.getInt(3);
        }
        
    }

    @Override
    public int checkUser(String username, String password) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_USER)) {
            
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.registerOutParameter(3, Types.INTEGER);
            stmt.executeUpdate();
            
            return stmt.getInt(3);
        }
    }
    
}
