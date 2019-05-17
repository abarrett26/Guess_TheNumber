/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GuessTheNumber.Dtos;

import java.util.List;

/**
 *
 * @author alexbarrett
 */
public class Game {
    
   private int gameId;
   private boolean isGameOver;
   private String targetNumber;
   private List<Round> gameRounds;

    /**
     * @return the gameId
     */
    public int getGameId() {
        return gameId;
    }

    /**
     * @param gameId the gameId to set
     */
    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    /**
     * @return the isGameOver
     */
    public boolean getIsGameOver() {
        return isGameOver;
    }

    /**
     * @param isGameOver the isGameOver to set
     */
    public void setIsGameOver(boolean isGameOver) {
        this.isGameOver = isGameOver;
    }

    /**
     * @return the targetNumber
     */
    public String getTargetNumber() {
        return targetNumber;
    }

    /**
     * @param targetNumber the targetNumber to set
     */
    public void setTargetNumber(String targetNumber) {
        this.targetNumber = targetNumber;
    }

    /**
     * @return the gameRounds
     */
    public List<Round> getGameRounds() {
        return gameRounds;
    }

    /**
     * @param gameRounds the gameRounds to set
     */
    public void setGameRounds(List<Round> gameRounds) {
        this.gameRounds = gameRounds;
    }
}
