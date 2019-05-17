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
import java.util.List;

/**
 *
 * @author alexbarrett
 */
public class AlwaysFailDao implements GTNDao {

    @Override
    public List<Game> getAllGames() throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Game displaySingleGame(int gameId) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Round> getGameRounds(int gameId) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int beginGame(Game newGame) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean gameOver(int gameId, boolean isGameOver) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Round guess(Round activeRound, Game activeGame) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}