/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GuessTheNumber.Dtos;

import java.time.LocalDateTime;

/**
 *
 * @author alexbarrett
 */
public class Round {
   
   private int roundId;
   private int gameId;
   private int roundNumber;
   private String guessNumber;
   private String guessResult;
   private int exact;
   private int partial;
   private LocalDateTime time;

    /**
     * @return the roundId
     */
    public int getRoundId() {
        return roundId;
    }

    /**
     * @param roundId the roundId to set
     */
    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

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
     * @return the roundNumber
     */
    public int getRoundNumber() {
        return roundNumber;
    }

    /**
     * @param roundNumber the roundNumber to set
     */
    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    /**
     * @return the guessNumber
     */
    public String getGuessNumber() {
        return guessNumber;
    }

    /**
     * @param guessNumber the guessNumber to set
     */
    public void setGuessNumber(String guessNumber) {
        this.guessNumber = guessNumber;
    }

    /**
     * @return the guessResult
     */
    public String getGuessResult() {
        return guessResult;
    }

    /**
     * @param guessResult the guessResult to set
     */
    public void setGuessResult(String guessResult) {
        this.guessResult = guessResult;
    }

    /**
     * @return the exact
     */
    public int getExact() {
        return exact;
    }

    /**
     * @param exact the exact to set
     */
    public void setExact(int exact) {
        this.exact = exact;
    }

    /**
     * @return the partial
     */
    public int getPartial() {
        return partial;
    }

    /**
     * @param partial the partial to set
     */
    public void setPartial(int partial) {
        this.partial = partial;
    }

    /**
     * @return the time
     */
    public LocalDateTime getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}