/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GuessTheNumber.Daos;

import com.GuessTheNumber.Dtos.Game;
import com.GuessTheNumber.Dtos.Round;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author alexbarrett
 */
@Repository
public class GTNDbDao implements GTNDao {
 
   @Autowired
   private JdbcTemplate jdbc;
 
   public GTNDbDao(JdbcTemplate jdbc) {
       this.jdbc = jdbc;
   }
 
   @Override
   public List<Game> getAllGames() throws PersistenceException {
       List<Game> allGames = new ArrayList();
       try {
           final String SELECT_ALL_GAMES = "SELECT * FROM games";
           allGames = jdbc.query(SELECT_ALL_GAMES, new gameMapper());
           return allGames;
       } catch (DataAccessException ex) {
           throw new PersistenceException("Unable to retrieve games from database");
       }
   }
 
   @Override
   public Game displaySingleGame(int gameId) throws PersistenceException {
       final String SELECT_GAME_BY_ID = "SELECT * FROM games WHERE gameId = ?";
       Game singleGame = jdbc.queryForObject(SELECT_GAME_BY_ID, new gameMapper(), gameId);
       return singleGame;
   }
 
   @Override
   public List<Round> getGameRounds(int gameId) throws PersistenceException {
       List<Round> allRoundsByGame = new ArrayList();
       try {
           final String SELECT_ROUNDS_BY_GAME = "SELECT * FROM rounds WHERE gameId = ?";
           allRoundsByGame = jdbc.query(SELECT_ROUNDS_BY_GAME, new roundsMapper(), gameId);
       } catch (DataAccessException ex) {
           throw new PersistenceException("Unable to retrieve rounds from database");
       }
       return allRoundsByGame;
   }
 
   @Override
   @Transactional
   public int beginGame(Game newGame) throws PersistenceException {
       final String INSERT_GAME_INTO_GAMES = "INSERT INTO games(isGameOver, targetNumber) VALUES(?,?);";
       GeneratedKeyHolder key = new GeneratedKeyHolder();
       try {
           jdbc.update(INSERT_GAME_INTO_GAMES, newGame.getIsGameOver(), newGame.getTargetNumber());
           newGame.setGameId(key.getKey().intValue());
       } catch (DataAccessException ex) {
           throw new PersistenceException("Unable to insert data to database");
       }
       return newGame.getGameId();
   }
 
   @Override
   public boolean gameOver(int gameId, boolean isGameOver) throws PersistenceException {
       final String GAME_OVER = "UPDATE Games SET isGameOver = ? WHERE gameId = ?;";
       boolean done = false;
       try {
           done = jdbc.update(GAME_OVER, gameId, isGameOver) == 1;
       } catch (DataAccessException ex) {
           throw new PersistenceException("Unable to update game from database");
       }
       return done;
   }
 
   @Override
   public Round guess(Round activeRound, Game activeGame) throws PersistenceException {
       int activeRoundId = addRounds(activeGame);
       activeRound.setRoundId(activeRoundId);
 
       final String LOG_GUESS = "INSERT INTO rounds(roundId, gameId, guessNumber, exact, partial, time)"
               + "VALUES(?,?,?,?,?,?);";
       try {
           jdbc.update(LOG_GUESS, activeRound.getRoundId(), activeRound.getGameId(), activeRound.getGuessNumber(), activeRound.getExact(), activeRound.getPartial(), activeRound.getTime());
           activeRound.setTime(LocalDateTime.now());
       } catch (DataAccessException ex) {
           throw new PersistenceException("Unable to insert data from database");
       }
       return activeRound;
   }
 
   private int addRounds(Game activeGame) {
       List<Round> gameRounds = activeGame.getGameRounds();
       int toReturn = Integer.MIN_VALUE;
       if (gameRounds == null) {
           toReturn = 1;
       } else if (gameRounds.isEmpty()) {
           toReturn = 1;
       } else {
           for (Round toCheck : gameRounds) {
               if (toCheck.getRoundId() > toReturn) {
                   toReturn = toCheck.getRoundId();
               }
           }
           toReturn++;
       }
       return toReturn;
   }
 
   private static final class gameMapper implements RowMapper<Game> {
 
       @Override
       public Game mapRow(ResultSet rs, int index) throws SQLException {
           Game toGame = new Game();
           toGame.setGameId(rs.getInt("gameId"));
           toGame.setIsGameOver(rs.getBoolean("isGameOver"));
           toGame.setTargetNumber(rs.getString("targetNumber"));
           return toGame;
       }
 
   }
 
   private static final class roundsMapper implements RowMapper<Round> {
 
       @Override
       public Round mapRow(ResultSet rs, int index) throws SQLException {
           Round toRound = new Round();
           toRound.setRoundId(rs.getInt("roundId"));
           toRound.setGameId(rs.getInt("gameId"));
           toRound.setGuessNumber(rs.getString("guessNumber"));
           toRound.setExact(rs.getInt("exact"));
           toRound.setPartial(rs.getInt("partial"));
           toRound.setTime(rs.getTimestamp("time").toLocalDateTime());
           return toRound;
       }
   }
}