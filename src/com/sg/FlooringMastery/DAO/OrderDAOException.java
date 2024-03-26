package com.sg.FlooringMastery.DAO;

public class OrderDAOException extends Exception{ // Custom order exceptions for handling exceptions specific order operations

    public OrderDAOException(String message) {
        super(message);
    }

    public OrderDAOException(String message, Throwable cause) {
        super(message, cause);
    }

}
