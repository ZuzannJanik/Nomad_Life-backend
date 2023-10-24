package com.crud.nomad.exceptions;

public class SearchException extends Exception {
    public SearchException() {
    super("The drug could not be found.");
    }
}
