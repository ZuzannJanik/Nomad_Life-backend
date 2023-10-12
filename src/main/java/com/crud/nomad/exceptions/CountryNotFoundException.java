package com.crud.nomad.exceptions;

public class CountryNotFoundException extends Throwable {
    public CountryNotFoundException() {
        super("Country id does not exist. Check the correctness of the data.");
    }
}
