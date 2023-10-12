package com.crud.nomad.exceptions;

public class MedicineNotFoundException extends Throwable {
    public MedicineNotFoundException() {
        super("Medicine id does not exist. Check the correctness of the data.");
    }
}
