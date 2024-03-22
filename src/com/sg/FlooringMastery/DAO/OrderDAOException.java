package com.sg.FlooringMastery.DAO;

public class OrderDAOException extends Exception{
    public OrderDAOException(String message) {
        super(message);
    }

    public OrderDAOException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
