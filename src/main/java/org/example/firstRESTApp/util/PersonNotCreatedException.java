package org.example.firstRESTApp.util;

public class PersonNotCreatedException extends RuntimeException {
    public  PersonNotCreatedException(String msg) {
        super(msg);
    }
}
