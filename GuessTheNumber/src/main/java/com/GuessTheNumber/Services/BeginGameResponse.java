/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GuessTheNumber.Services;

import com.GuessTheNumber.Dtos.Game;

/**
 *
 * @author alexbarrett
 */
public class BeginGameResponse extends Response {
    
    private Game beginGame;

    /**
     * @return the beginGame
     */
    public Game getBeginGame() {
        return beginGame;
    }

    /**
     * @param beginGame the beginGame to set
     */
    public void setBeginGame(Game beginGame) {
        this.beginGame = beginGame;
    }
}
   