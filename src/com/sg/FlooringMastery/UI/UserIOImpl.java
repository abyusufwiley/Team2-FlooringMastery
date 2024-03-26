package com.sg.FlooringMastery.UI;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
@Component
public class UserIOImpl implements UserIO{
    final private Scanner console = new Scanner(System.in); //Scanner object to read input from the user


    @Override
    //Prints a message to the console
    public void print(String msg) {
        System.out.println(msg);
    }
    @Override
    //Reads a double from the console
    public double readDouble(String msgPrompt) {
        while (true) {
            try {
                return Double.parseDouble(this.readString(msgPrompt));
            } catch (NumberFormatException e) {
                this.print("Input error. Please try again.");
            }
        }
    }
    @Override
    //Reads a double from the console within a specified range
    public double readDouble(String msgPrompt, double min, double max) {
        double result;
        do {
            result = readDouble(msgPrompt);
        } while (result < min || result > max);
        return result;
    }
    @Override
    // Reads a float from the console
    public float readFloat(String msgPrompt) {
        while (true) {
            try {
                return Float.parseFloat(this.readString(msgPrompt));
            } catch (NumberFormatException e) {
                this.print("Input error. Please try again.");
            }
        }
    }
    @Override
    // Reads a float from the console within a specified range
    public float readFloat(String msgPrompt, float min, float max) {
        float result;
        do {
            result = readFloat(msgPrompt);
        } while (result < min || result > max);

        return result;
    }

    @Override
    // Reads an integer from the console
    public int readInt(String msgPrompt) {

        boolean invalidInput = true;
        int num = 0;
        while (invalidInput) {
            try {
                // print the message msgPrompt (ex: asking for the # of cats!)
                String stringValue = this.readString(msgPrompt);
                // Get the input line, and try and parse
                num = Integer.parseInt(stringValue); // if it's 'bob' it'll break
                invalidInput = false; // or you can use 'break;'
            } catch (NumberFormatException e) {
                // If it explodes, it'll go here and do this.
                this.print("Input error. Please try again.");
            }
        }
        return num;
    }

    @Override
    // Reads an integer from the console within a specified range
    public int readInt(String msgPrompt, int min, int max) {
        int result;
        do {
            result = readInt(msgPrompt);
        } while (result < min || result > max);

        return result;
    }

    @Override
    // Reads a long from the console
    public long readLong(String msgPrompt) {
        while (true) {
            try {
                return Long.parseLong(this.readString(msgPrompt));
            } catch (NumberFormatException e) {
                this.print("Input error. Please try again.");
            }
        }
    }
    @Override
    // Reads a long from the console within a specified range
    public long readLong(String msgPrompt, long min, long max) {
        long result;
        do {
            result = readLong(msgPrompt);
        } while (result < min || result > max);

        return result;
    }

    @Override
    // Reads a string from the console
    public String readString(String msgPrompt) {
        System.out.println(msgPrompt);
        return console.nextLine();
    }

    @Override
    // Reads a BigDecimal from the console
    public BigDecimal readBigDecimal(String Prompt) {
        System.out.println(Prompt);
        while(true){
            try{
                return new BigDecimal(console.nextLine());
            }catch(NumberFormatException e){
                System.out.println("Please Enter a Valid Number");
            }
        }
    }

    @Override
    // Reads a LocalDate from the console
    public LocalDate readLocalDate(String prompt) {
        while (true) {
            try {
                System.out.println(prompt);
                String dateInput = console.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                return LocalDate.parse(dateInput, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Please Enter a Valid Date in the format YYYY-MM-DD");
            }
        }
    }

}