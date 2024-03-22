package com.sg.FlooringMastery.Controller;

import com.sg.FlooringMastery.UI.UserIO;
import com.sg.FlooringMastery.UI.UserIOImpl;
import com.sg.FlooringMastery.UI.View;

//hello
public class Controller {
    private View view = new View();
    private UserIO io = new UserIOImpl();

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        while (keepGoing) {

            menuSelection = getMenuSelection();

            switch (menuSelection) {
                case 1:
                    io.print("DISPLAY ORDERS");
                    break;
                case 2:
                    io.print("ADD ORDER");
                    break;
                case 3:
                    io.print("EDIT ORDER");
                    break;
                case 4:
                    io.print("REMOVE ORDER");
                    break;
                case 5:
                    io.print("EXPORT ALL DATA");
                    break;
                case 6:
                    keepGoing = false;
                    break;
                default:
                    io.print("UNKNOWN COMMAND");
            }

        }
        io.print("GOOD BYE");
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

}
