/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GuessTheNumber.Services;

import com.GuessTheNumber.Daos.GTNDao;
import com.GuessTheNumber.Daos.PersistenceException;
import com.GuessTheNumber.Dtos.Game;
import com.GuessTheNumber.Dtos.Round;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author alexbarrett
 */
@Service
public class GTNService {
 
   @Autowired
   GTNDao dao;
 
   public ListGameResponse getAllGames() {
       ListGameResponse toReturn = new ListGameResponse();
       List<Game> displayAll = new ArrayList();
       try {
           List<Game> allGames = dao.getAllGames();
           displayAll = HiddenGame(allGames);
           toReturn.setIsSuccessful(true);
           toReturn.setGetAllGames(displayAll);
       } catch (PersistenceException | EmptyGameException ex) {
           toReturn.setIsSuccessful(false);
           toReturn.setMessage(ex.getMessage());
       }
       return toReturn;
   }
   
   private Game getGame(int gameId, List<Game> allGames) throws InvalidGameIdException {
       Game toReturn = new Game();
       for (Game toCheck : allGames) {
           if (gameId == toCheck.getGameId()) {
               toReturn = toCheck;
               break;
           } else {
               toReturn = null;
           }
       }
       if (toReturn == null) {
           throw new InvalidGameIdException("Unable to get game from database");
       }
       return toReturn;
   }
 
   public BeginGameResponse beginGame() {
       Game newGame = new Game();
       BeginGameResponse toReturn = new BeginGameResponse();
       try {
           String targetNumber = generateTargetNumber();
           validateDuplicate(targetNumber);
           validateNumber(targetNumber);
           newGame.setTargetNumber(targetNumber);
           newGame.setIsGameOver(false);
           int gameId = dao.beginGame(newGame);
           newGame.setGameId(gameId);
           toReturn.setBeginGame(newGame);
           toReturn.setIsSuccessful(true);
       } catch (PersistenceException | InvalidNumberException ex) {
           toReturn.setIsSuccessful(false);
           toReturn.setMessage(ex.getMessage());
       }
       return toReturn;
   }
 
   public ListSingleGameResponse getGameById(int gameId) {
       ListSingleGameResponse toReturn = new ListSingleGameResponse();
       try {
           Game singleGame = dao.displaySingleGame(gameId);
           if (singleGame == null) {
               toReturn.setIsSuccessful(false);
               toReturn.setMessage("Unable to retrieve game from database");
           } else if (!singleGame.getIsGameOver()) {
               toReturn.setIsSuccessful(true);
               singleGame.setTargetNumber("");
               toReturn.setSingleGame(singleGame);
           } else {
               toReturn.setIsSuccessful(true);
               toReturn.setSingleGame(singleGame);
           }
       } catch (PersistenceException ex) {
           toReturn.setIsSuccessful(false);
           toReturn.setMessage(ex.getMessage());
       }
       return toReturn;
   }
 
   public ListRoundResponse getGameRounds(int gameId) {
       ListRoundResponse toReturn = new ListRoundResponse();
       List<Round> allRounds = new ArrayList();
       List<Game> allGames = new ArrayList();
       try {
           allGames = dao.getAllGames();
           getGame(gameId, allGames);
           allRounds = dao.getGameRounds(gameId);
           validateRound(allRounds);
           if (allRounds == null) {
               toReturn.setIsSuccessful(false);
               toReturn.setMessage("Unable to retrieve rounds from database");
           } else {
               toReturn.setIsSuccessful(true);
               toReturn.setAllRounds(allRounds);
           }
       } catch (PersistenceException | EmptyRoundException | InvalidGameIdException ex) {
           toReturn.setIsSuccessful(false);
           toReturn.setMessage(ex.getMessage());
       }
       return toReturn;
   }

   
   public GuessGameResponse makeGuess(int gameId, String guess) {
       Game activeGame = new Game();
       Round activeRound = new Round();
       GuessGameResponse toReturn = new GuessGameResponse();
       try {
           List<Game> allGames = dao.getAllGames();
           activeGame = getGame(gameId, allGames);
           validateOver(activeGame);
           validateNumber(guess);
           activeRound.setGuessNumber(guess);
           Round checkRoundGuess = validateGuess(guess, activeGame.getTargetNumber(), activeRound);
           activeRound.setExact(checkRoundGuess.getExact());
           activeRound.setExact(checkRoundGuess.getPartial());
           activeRound = dao.guess(activeRound, activeGame);
           if (activeRound.getExact() == 4) {
               activeGame.setIsGameOver(true);
           }
           dao.gameOver(gameId, activeGame.getIsGameOver());
           toReturn.setBeginRound(activeRound);
           toReturn.setIsSuccessful(true);
       } catch (PersistenceException | InvalidGameIdException | InvalidNumberException ex) {
           toReturn.setIsSuccessful(false);
           toReturn.setMessage(ex.getMessage());
       }
       return toReturn;
   }
 
   private String generateTargetNumber() {
       Random rng = new Random();
       String toReturn = "";
       List<Integer> genNum = new ArrayList();
       while (genNum.size() < 4) {
           int randomNumber = rng.nextInt(10);
           if (genNum.size() < 1 && randomNumber == 0) {
           } else if (!genNum.contains(randomNumber)) {
               genNum.add(randomNumber);
               toReturn += randomNumber;
           }
       }
       return toReturn;
   }
 
   private List<Game> HiddenGame(List<Game> allGames) throws EmptyGameException {
       List<Game> toReturn = new ArrayList();
       if (allGames.isEmpty()) {
           throw new EmptyGameException("Empty game list.");
       } else {
           for (Game toCheck : allGames) {
               if (!toCheck.getIsGameOver()) {
                   toCheck.setTargetNumber("secret");
               }
               toReturn.add(toCheck);
           }
       }
       return toReturn;
   }
   
   private boolean validateNumber(String number) throws InvalidNumberException {
       boolean isValid = true;
       List<Character> target = new ArrayList<>();
       if (number.length() != 4) {
           throw new InvalidNumberException("Input hould be 4 numbers");
       }
       for (Character toCheck : number.toCharArray()) {
           int numCheck = Character.getNumericValue(toCheck);
           if (numCheck < 0) {
               throw new InvalidNumberException("Input numbers only");
           } else if (target.contains(toCheck)) {
               isValid = false;
               throw new InvalidNumberException("No duplicate numbers");
           }else{
               target.add(toCheck);
           }
       }
       return isValid;
   }
   
   private void validateOver(Game currentGame) throws InvalidGameIdException {
       if (currentGame.getIsGameOver()) {
           throw new InvalidGameIdException("Game is over");
       }
   }
   
   private Round validateGuess(String guessNumber, String targetNumber, Round toGuess) {
       Round toReturn = toGuess;
       int exact = 0;
       int partial = 0;
       List<Character> guess = new ArrayList<>();
       List<Character> targetNum = new ArrayList<>();
       for (Character toCheck : guessNumber.toCharArray()) {
           guess.add(toCheck);
       }
       for (Character toCheck : targetNumber.toCharArray()) {
           targetNum.add(toCheck);
       }
       for (int i = 0; i < targetNum.size(); i++) {
           if (targetNum.get(i) == guess.get(i)) {
               exact++;
           } else if (targetNum.contains(guess.get(i))) {
               partial++;
           }
       }
       toReturn.setExact(exact);
       toReturn.setPartial(partial);
       return toReturn;
   }
 
   private void validateRound(List<Round> allRounds) throws EmptyRoundException {
       if (allRounds.isEmpty()) {
           throw new EmptyRoundException("Empty round list.");
       }
   }
   
   private static boolean validateDuplicate(String targetNumber) {
       boolean toReturn = false;
       for (int i = 0; i < 4; i++) {
           char left = targetNumber.charAt(i);
           for (int j = i + 1; j < 4; j++) {
               char right = targetNumber.charAt(j);
               if (left == right) {
                   toReturn = true;
                   i = 4;
                   j = 4;
               }
           }
       }
       return toReturn;
   }
 
}