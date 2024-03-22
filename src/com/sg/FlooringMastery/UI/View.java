package com.sg.FlooringMastery.UI;

public class View {
    private UserIO io = new UserIOImpl();

    /*
     * 
     *   * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
  * <<Flooring Program>>
  * 1. Display Orders
  * 2. Add an Order
  * 3. Edit an Order
  * 4. Remove an Order
  * 5. Export All Data
  * 6. Quit
  *
  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    public int printMenuAndGetSelection() {
        io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        io.print("<<Flooring Program>>");
        io.print("1. Display Orders");
        io.print("2. Add an Order");
        io.print("3. Edit an Order");
        io.print("4. Remove an Order");
        io.print("5. Export All Data");
        io.print("6. Quit");
        io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");

        return io.readInt("Please select from the above choices.", 1, 6);
    }
}
