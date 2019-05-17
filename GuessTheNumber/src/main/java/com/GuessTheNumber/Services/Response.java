/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GuessTheNumber.Services;

public abstract class Response {
    private boolean isSuccessful;
    private String message;

    /**
     * @return the isSuccessful
     */
    public boolean isSuccessful() {
        return isSuccessful;
    }

    /**
     * @param isSuccessful the isSuccessful to set
     */
    public void setIsSuccessful(boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
