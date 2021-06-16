/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Marko
 */

@XmlRootElement(name = "gameArchive")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"currentPlayer", "threeInARow", "removeEnemyChip", "switching", "sliding", "selectedIndex"})
public class Game {

    @XmlElement(name = "currentPlayer")
    private int currentPlayer;
    @XmlElement(name = "threeInARow")
    private Boolean threeInARow;
    @XmlElement(name = "removeEnemyChip")
    private Boolean removeEnemyChip;
    @XmlElement(name = "switching")
    private Boolean switching;
    @XmlElement(name = "sliding")
    private Boolean sliding;
    @XmlElement(name = "selectedIndex")
    private int selectedIndex;

    public Game() {
    }

    public Game(int currentPlayer, Boolean threeInARow, Boolean removeEnemyChip, Boolean switching, Boolean sliding, int selectedIndex) {
        this.currentPlayer = currentPlayer;
        this.threeInARow = threeInARow;
        this.removeEnemyChip = removeEnemyChip;
        this.switching = switching;
        this.sliding = sliding;
        this.selectedIndex = selectedIndex;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Boolean getThreeInARow() {
        return threeInARow;
    }

    public void setThreeInARow(Boolean threeInARow) {
        this.threeInARow = threeInARow;
    }

    public Boolean getRemoveEnemyChip() {
        return removeEnemyChip;
    }

    public void setRemoveEnemyChip(Boolean removeEnemyChip) {
        this.removeEnemyChip = removeEnemyChip;
    }

    public Boolean getSwitching() {
        return switching;
    }

    public void setSwitching(Boolean switching) {
        this.switching = switching;
    }

    public Boolean getSliding() {
        return sliding;
    }

    public void setSliding(Boolean sliding) {
        this.sliding = sliding;
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
    }

}
