/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GuessTheNumber.Services;

import com.GuessTheNumber.Dtos.Round;

/**
 *
 * @author alexbarrett
 */
public class GuessGameResponse extends Response{
   
    private Round beginRound;

    /**
     * @return the beginRound
     */
    public Round getBeginRound() {
        return beginRound;
    }

    /**
     * @param beginRound the beginRound to set
     */
    public void setBeginRound(Round beginRound) {
        this.beginRound = beginRound;
    }
}
