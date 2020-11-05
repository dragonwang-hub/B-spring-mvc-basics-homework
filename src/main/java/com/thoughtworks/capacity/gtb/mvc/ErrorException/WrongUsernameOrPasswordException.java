package com.thoughtworks.capacity.gtb.mvc.ErrorException;

public class WrongUsernameOrPasswordException extends Exception {
    public WrongUsernameOrPasswordException(String message) {
        super(message);
    }
}
