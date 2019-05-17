/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import com.GuessTheNumber.Daos.GTNDao;
import com.GuessTheNumber.Daos.PersistenceException;
import com.GuessTheNumber.Dtos.Game;
import com.GuessTheNumber.Dtos.Round;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alexbarrett
 */
public class InMemoryDao implements GTNDao {
 
   List<Game> allGames = new ArrayList();
   List<Round> roundsGameOne = new ArrayList();
 
   public InMemoryDao() {
 
       Game game1 = new Game();
       game1.setGameId(1);
       game1.setIsGameOver(false);
       game1.setTargetNumber("2356");
       
       Round round1 = new Round();
       round1.setGameId(1);
       round1.setRoundId(1);
       round1.setGuessNumber("6789");
       round1.setExact(0);
       round1.setPartial(0);
 
       Round round2 = new Round();
       round2.setGameId(1);
       round2.setRoundId(2);
       round2.setGuessNumber("2378");
       round2.setExact(2);
       round2.setPartial(0);
 
       roundsGameOne.add(round1);
       roundsGameOne.add(round2);
 
       game1.setGameRounds(roundsGameOne);
 
       allGames.add(game1);
 
   }
 
   @Override
   public List<Game> getAllGames() {
       return allGames;
   }
 
   @Override
   public List<Round> getGameRounds(int gameId) {
       return roundsGameOne;
   }
 
   @Override
   public int beginGame(Game toBegin) {
       int gameId = generateGameId(allGames);
       toBegin.setGameId(gameId);
       allGames.add(toBegin);
       return toBegin.getGameId();
   }
 
   @Override
   public Round guess(Round currentRound, Game currentGame) {
       
       int roundId = generateRoundId(roundsGameOne);
       int roundNumber = calculateNumberOfRounds(currentGame);
       currentRound.setRoundNumber(roundNumber);
       currentRound.setRoundId(roundId);
       roundsGameOne.add(currentRound);
       return currentRound;
   }
 
   private int generateGameId(List<Game> allGames) {
       int toReturn = Integer.MIN_VALUE;
 
       if (allGames.isEmpty()) {
           toReturn = 1;
       } else {
           for (Game toInspect : allGames) {
               if (toInspect.getGameId() > toReturn) {
                   toReturn = toInspect.getGameId();
               }
           }
 
           toReturn++;
       }
 
       return toReturn;
   }
 
   private int generateRoundId(List<Round> gameOneRounds) {
       int toReturn = Integer.MIN_VALUE;
 
       if (gameOneRounds.isEmpty()) {
           toReturn = 1;
       } else {
           for (Round toInspect : gameOneRounds) {
               if (toInspect.getRoundId() > toReturn) {
                   toReturn = toInspect.getRoundId();
               }
           }
 
           toReturn++;
       }
 
       return toReturn;
   }
 
   private int calculateNumberOfRounds(Game currentGame) {
       List<Round> gameRounds = currentGame.getGameRounds();
       int toReturn = Integer.MIN_VALUE;
 
       if (gameRounds == null) {
           toReturn = 1;
       } else if (gameRounds.isEmpty()) {
           toReturn = 1;
       } else {
           for (Round toInspect : gameRounds) {
               if (toInspect.getRoundNumber() > toReturn) {
                   toReturn = toInspect.getRoundNumber();
               }
           }
 
           toReturn++;
       }
 
       return toReturn;
   }

    @Override
    public Game displaySingleGame(int gameId) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean gameOver(int gameId, boolean isGameOver) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}