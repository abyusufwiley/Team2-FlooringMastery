package com.sg.FlooringMastery.DAO;

public class ProductDAOException extends OrderDAOException{
    public ProductDAOException(String message) {
        super(message);
    }

    public ProductDAOException(String message, Throwable cause) {
        super(message, cause);
    }

}
