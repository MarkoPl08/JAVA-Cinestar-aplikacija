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
public class PersonTableModel extends AbstractTableModel{
    
    private static final String[] COLUMN_NAMES = {"Id", "Name"};
    
    private List<Person> people;

    public PersonTableModel(List<Person> people) {
        this.people = people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
        fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        return people.size();
    }

    @Override
    public int getColumnCount() {
        return Person.class.getDeclaredFields().length - 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return people.get(rowIndex).getId();
            case 1:
                return people.get(rowIndex).getName();            
            default:
                throw new RuntimeException("No column");
        }
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }
}
