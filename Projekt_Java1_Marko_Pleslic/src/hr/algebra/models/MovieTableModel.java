/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.models;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Marko
 */
public class MovieTableModel extends AbstractTableModel {
    
    private static final String[] COLUMN_NAMES = {"Id", "Title", "Genre", "Duration", "Published Date", "Picture path", "Description"};
    
    private List<Movie> movies;

    public MovieTableModel(List<Movie> movies) {
        this.movies = movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        return movies.size();
    }

    @Override
    public int getColumnCount() {
        return Movie.class.getDeclaredFields().length - 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return movies.get(rowIndex).getId();
            case 1:
                return movies.get(rowIndex).getTitle();
            case 2:
                return movies.get(rowIndex).getGenre();
            case 3:
                return movies.get(rowIndex).getDuration();
            case 4:
                return movies.get(rowIndex).getPublishedDate();
            case 5:
                return movies.get(rowIndex).getPicturePath();
             case 6:
                return movies.get(rowIndex).getDescription();
            default:
                throw new RuntimeException("No column");
        }
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }
}
