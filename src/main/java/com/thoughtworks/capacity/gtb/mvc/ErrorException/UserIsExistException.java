package com.thoughtworks.capacity.gtb.mvc.ErrorException;

public class UserIsExistException extends Exception{
    public UserIsExistException (String message){
        super(message);
    }
}
