/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GuessTheNumber.Services;

import com.GuessTheNumber.Dtos.Game;
import java.util.List;

/**
 *
 * @author alexbarrett
 */
public class ListGameResponse extends Response{

    private List<Game> allGames;

    /**
     * @return the getAllGames
     */
    public List<Game> getAllGames() {
        return allGames;
    }

    /**
     * @param getAllGames the getAllGames to set
     */
    public void setGetAllGames(List<Game> getAllGames) {
        this.allGames = getAllGames;
    }
    
}
