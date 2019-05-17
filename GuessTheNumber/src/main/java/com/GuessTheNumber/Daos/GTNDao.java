/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GuessTheNumber.Daos;

import com.GuessTheNumber.Dtos.Game;
import com.GuessTheNumber.Dtos.Round;
import java.util.List;

/**
 *
 * @author alexbarrett
 */
public interface GTNDao {
    
    
   public List<Game> getAllGames() throws PersistenceException;
   
   public Game displaySingleGame(int gameId) throws PersistenceException;
   
   public List<Round> getGameRounds(int gameId) throws PersistenceException;
  
   public int beginGame(Game newGame) throws PersistenceException;
   
   public boolean gameOver(int gameId, boolean isGameOver) throws PersistenceException;
   
   public Round guess(Round activeRound, Game activeGame) throws PersistenceException;

}