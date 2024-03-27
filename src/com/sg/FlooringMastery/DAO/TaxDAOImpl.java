package com.sg.FlooringMastery.DAO;

import com.sg.FlooringMastery.DTO.TaxDTO;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.*;
@Component
public class TaxDAOImpl implements TaxDAO{ // TaxDAOImpl implements TaxDAO
    private final String TAXES_FILE;
    private final String DELIMITER = ",";
    private Map<String, TaxDTO> taxes = new HashMap<>();

    public TaxDAOImpl(){ // Constructor that sets the default value of TAXES_FILE
        TAXES_FILE = "Data/Taxes.txt";
    }

    // Constructor
    public TaxDAOImpl(String taxesTextFile){ //Constructor that sets the value of TAXES_FILE to the value of taxesTextFile
        TAXES_FILE = taxesTextFile;
    }

    //LoadTaxes reads from the Taxes.txt file and populates the taxes map with the data
    public void loadTaxes() {
        Scanner scanner;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(TAXES_FILE)));
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                TaxDTO tax = unmarshallTax(line);
                taxes.put(tax.getState(), tax);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Could not load taxes data.");
        }
    }

    public TaxDTO unmarshallTax(String taxAsText){ //Converts a line of text from the taxes file into a TaxDTO object
        String[] taxTokens = taxAsText.split(DELIMITER);
        String state = taxTokens[0];
        BigDecimal taxRate = new BigDecimal(taxTokens[2]);
        String stateName = taxTokens[1];
        return new TaxDTO(state, stateName, taxRate);

    }

    @Override
    public List<TaxDTO> getAllTaxes() { //Retrieves all taxes stored in the taxes map
        loadTaxes();
        return new ArrayList<>(taxes.values());
    }

    @Override
    public TaxDTO getTax(String state) { //Retrieves the tax rate for a given state
        loadTaxes();
        return taxes.get(state);
    }
}
