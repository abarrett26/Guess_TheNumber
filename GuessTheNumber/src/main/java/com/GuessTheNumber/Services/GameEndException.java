/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GuessTheNumber.Services;

/**
 *
 * @author alexbarrett
 */
public class GameEndException extends Exception {
   
   public GameEndException (String message){
       super(message);
   }
   
   public GameEndException (String message, Throwable innerException){
       super(message, innerException);
   }
   
}