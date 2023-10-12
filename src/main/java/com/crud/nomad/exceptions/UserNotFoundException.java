package com.crud.nomad.exceptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
        super("User id does not exist. Check the correctness of the data.");
    }
}
