package com.crud.nomad.exceptions;

public class VaccinationNotFoundException extends Exception {
    public VaccinationNotFoundException() {
        super("Vaccination id does not exist. Check the correctness of the data.");
    }
}
