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
public class EmptyGameException extends Exception {
   
   public EmptyGameException (String message){
       super(message);
   }
   
   public EmptyGameException (String message, Throwable innerException){
       super(message, innerException);
   }
   
}