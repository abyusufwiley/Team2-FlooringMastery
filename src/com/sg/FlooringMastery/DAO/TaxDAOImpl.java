package com.sg.FlooringMastery.DAO;

import com.sg.FlooringMastery.DTO.TaxDTO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.*;

public class TaxDAOImpl implements TaxDAO{
    private final String TAXES_FILE = "Taxes.txt";
    private final String DELIMITER = ",";
    private Map<String, TaxDTO> taxes = new HashMap<>();

    public TaxDAOImpl(){
        loadTaxes();
    }
    public void loadTaxes() {
        Scanner scanner;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(TAXES_FILE)));
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
    public TaxDTO unmarshallTax(String taxAsText){
        String[] taxTokens = taxAsText.split(DELIMITER);
        String state = taxTokens[0];
        BigDecimal taxRate = new BigDecimal(taxTokens[1]);
        String stateName = taxTokens[2];
        return new TaxDTO(state, stateName, taxRate);

    }

    @Override
    public List<TaxDTO> getAllTaxes() {
        return new ArrayList<>(taxes.values());
    }

    @Override
    public TaxDTO getTax(String state) {
        return taxes.get(state);
    }
}
