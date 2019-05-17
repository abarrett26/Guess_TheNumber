/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GuessTheNumber.Services;

import com.GuessTheNumber.Dtos.Round;
import java.util.List;

/**
 *
 * @author alexbarrett
 */
public class ListRoundResponse extends Response{
    private List<Round> allRounds;

    /**
     * @return the allRounds
     */
    public List<Round> getAllRounds() {
        return allRounds;
    }

    /**
     * @param allRounds the allRounds to set
     */
    public void setAllRounds(List<Round> allRounds) {
        this.allRounds = allRounds;
    }
}
