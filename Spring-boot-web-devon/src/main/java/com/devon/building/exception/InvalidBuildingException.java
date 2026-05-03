package com.devon.building.exception;

public class InvalidBuildingException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 6871075746487735697L;

    public InvalidBuildingException(String message)
    {
        super(message);
    }
}

