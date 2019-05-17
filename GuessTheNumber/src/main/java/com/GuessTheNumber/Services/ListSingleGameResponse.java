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
public class ListSingleGameResponse extends Response {
    private Game singleGame;

    /**
     * @return the singleGame
     */
    public Game getSingleGame() {
        return singleGame;
    }

    /**
     * @param singleGame the singleGame to set
     */
    public void setSingleGame(Game singleGame) {
        this.singleGame = singleGame;
    }
}
