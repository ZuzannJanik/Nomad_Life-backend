package com.crud.nomad.exceptions;

public class NomadUserNotFoundException extends Exception {
    public NomadUserNotFoundException() {
        super("NomadUser id does not exist. Check the correctness of the data.");
    }
}
