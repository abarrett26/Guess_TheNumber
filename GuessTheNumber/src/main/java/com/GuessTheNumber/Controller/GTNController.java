/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GuessTheNumber.Controller;

import com.GuessTheNumber.Daos.PersistenceException;
import com.GuessTheNumber.Dtos.Game;
import com.GuessTheNumber.Dtos.Round;
import com.GuessTheNumber.Services.BeginGameResponse;
import com.GuessTheNumber.Services.GTNService;
import com.GuessTheNumber.Services.GuessGameResponse;
import com.GuessTheNumber.Services.ListGameResponse;
import com.GuessTheNumber.Services.ListRoundResponse;
import com.GuessTheNumber.Services.ListSingleGameResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author alexbarrett
 */
@RestController
@RequestMapping("/api")
public class GTNController {
 
   @Autowired
   private GTNService service;
 
   @PostMapping("/begin")
   @ResponseStatus(HttpStatus.CREATED)
   public int begin() {
       int toReturn;
 
       BeginGameResponse response = service.beginGame();
 
       if (!response.isSuccessful()) {
           toReturn = Integer.MIN_VALUE;
       } else {
           toReturn = response.getBeginGame().getGameId();
       }
       return toReturn;
   }
 
   @PostMapping("/guess")
   public Round guess(@RequestBody Round toReturn) {
       GuessGameResponse response = service.makeGuess(toReturn.getGameId(), toReturn.getGuessNumber());
       if (!response.isSuccessful()) {
           toReturn = null;
       } else {
           toReturn = response.getBeginRound();
       }
       return toReturn;
   }
 
   @GetMapping("/game")
   public List<Game> displayAllGames() {
       List<Game> toReturn = new ArrayList();
       ListGameResponse response = service.getAllGames();
       if (!response.isSuccessful()) {
           toReturn = null;
       } else {
           toReturn = response.getAllGames();
       }
       return toReturn;
   }
 
   @GetMapping("/game/{gameId}")
   public Game displayGameById(@PathVariable int gameId) {
       Game toReturn = new Game();
       ListSingleGameResponse response = service.getGameById(gameId);
       if (!response.isSuccessful()) {
           toReturn = null;
       } else {
           toReturn = response.getSingleGame();
       }
       return toReturn;
   }
 
   @GetMapping("/round/{gameId}")
   public List<Round> displayRoundsOfGame(@PathVariable int gameId) {
       List<Round> toReturn = new ArrayList();
       ListRoundResponse response = service.getGameRounds(gameId);
       if (!response.isSuccessful()) {
           toReturn = null;
       } else {
           toReturn = response.getAllRounds();
       }
       return toReturn;
   }
 
}