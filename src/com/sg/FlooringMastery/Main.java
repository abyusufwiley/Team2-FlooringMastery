package com.sg.FlooringMastery;

import com.sg.FlooringMastery.Controller.Controller;
import com.sg.FlooringMastery.DAO.OrderDAO;
import com.sg.FlooringMastery.DAO.OrderDAOException;
import com.sg.FlooringMastery.DAO.OrderDAOImpl;
import com.sg.FlooringMastery.DAO.ProductDAO;
import com.sg.FlooringMastery.DAO.ProductDAOImpl;
import com.sg.FlooringMastery.DAO.TaxDAO;
import com.sg.FlooringMastery.DAO.TaxDAOImpl;
import com.sg.FlooringMastery.Service.ServiceLayer;
import com.sg.FlooringMastery.Service.ServiceLayerImpl;
import com.sg.FlooringMastery.UI.UserIO;
import com.sg.FlooringMastery.UI.UserIOImpl;
import com.sg.FlooringMastery.UI.View;

public class Main {
    public static void main(String[] args) throws OrderDAOException {
        UserIO myIO = new UserIOImpl();
        OrderDAO myOrderDAO = new OrderDAOImpl();
        ProductDAO myProductDAO = new ProductDAOImpl();
        TaxDAO myTaxDAO = new TaxDAOImpl();
        ServiceLayer serviceLayer = new ServiceLayerImpl(myOrderDAO, myProductDAO, myTaxDAO);
        View view = new View(myIO);

        Controller controller = new Controller(view, serviceLayer);
        controller.run();


    }
}