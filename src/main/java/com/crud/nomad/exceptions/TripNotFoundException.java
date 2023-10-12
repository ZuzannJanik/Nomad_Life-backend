package com.crud.nomad.exceptions;

public class TripNotFoundException extends Exception {
    public TripNotFoundException() {
        super("Trip id does not exist. Check the correctness of the data.");
    }
}
