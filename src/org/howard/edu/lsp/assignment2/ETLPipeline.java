/**
 * Gael Santos
 * 
 * README
 * 
 * When making this program, I had originally assumed that the idea was to read a file into an ArrayList of String arrays.
 * So I started to write as such. However, I was faced with a problem. I wasn't sure how to read from .csv files. 
 * I had previously only read from .txt files when I was learning Java back in highschool. This is where I called upon the help of ChatGPT.
 * 
 * A real prompt I asked ChatGPT was:
 * "how do i read from an csv file on java and store the results into an array list of string array"
 * Not very proper english, I'm aware, but Chat understood me either way. It spat out some example code and then the following explanation:
 * 
 * ------------------------------------------
 * Locate the file
 * The method takes a path (data/products.csv) and uses Paths.get(path) to create a file reference.
 * If the file does not exist, it prints an error message and returns null.
 * 
 * Open the file
 * A BufferedReader is created to read the file line by line.
 * The try (...) block ensures the file is automatically closed after reading.
 * 
 * Read each line
 * The loop while ((line = br.readLine()) != null) reads the file one line at a time until the end.
 * Each line is split into columns using line.split(",").
 * 
 * Store the data
 * Each row (as a String[]) is added to a List<String[]>.
 * The first row in this list is the header (ProductID,Name,Price,Category).
 * 
 * Return the rows
 * At the end, the method returns the list of all rows for further processing.
 * ------------------------------------------
 * 
 * I tested the code on Eclipse by creating a products.csv file that used the simple text case provided in the instructions. 
 * The correct output was produced. 
 * 
 */

package org.howard.edu.lsp.assignment2;

import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.math.RoundingMode;
import java.math.BigDecimal;

public class ETLPipeline {

    private static final String INPUT_FILE = "data/products.csv";
    private static final String OUTPUT_FILE = "data/transformed_products.csv";

    public static void main(String[] args) {
        List<String[]> data = readFile(INPUT_FILE); // Create Array list of String Arrays from the file
        if (data == null) return;  // Stop if theres no input file

        List<String[]> transformed = transformFile(data); 
        writeFile(transformed, OUTPUT_FILE);

        int rowsRead = data.size() - 1; // Ignore header
        int rowsTransformed = transformed.size() - 1;
        int rowsSkipped = rowsRead - rowsTransformed;

        System.out.println("Summary:");
        System.out.println("Rows read: " + rowsRead);
        System.out.println("Rows transformed: " + rowsTransformed);
        System.out.println("Rows skipped: " + rowsSkipped);
        System.out.println("Output written to: " + OUTPUT_FILE);
    }
    
    private static List<String[]> readFile(String path) {
        Path file = Paths.get(path);

        // If file isnt found throw error message
        if (!Files.exists(file)) {
            System.err.println("ERROR: " + path + " not found.");
            return null;
        }

        List<String[]> rows = new ArrayList<>(); // returned list
        // Avoid crashing
        try (BufferedReader br = new BufferedReader(new FileReader(file.toFile()))) {
            String line;
            while ((line = br.readLine()) != null ) {
                rows.add(line.split(","));
            }
        } catch (IOException e) {
            System.err.println("ERROR: Reading " + path + ": " + e.getMessage());
            return null;
        }

        return rows;
    }

    private static List<String[]> transformFile(List<String[]> rows) {
        List<String[]> result = new ArrayList<>();
        result.add(new String[]{"ProductID","Name","Price","Category","PriceRange"});
        if (rows.size() <= 1) return result; // only header, no data

        for (int i = 1; i < rows.size(); i++) {
            String[] r = rows.get(i);

            // get variables
            String id = r[0];
            String name = r[1].toUpperCase();
            BigDecimal price = new BigDecimal(r[2]).setScale(2, RoundingMode.HALF_UP);
            String category = r[3];

            // apply discount if Electronics
            if (category.equalsIgnoreCase("Electronics")) {
                price = price.multiply(new BigDecimal("0.90")).setScale(2, RoundingMode.HALF_UP);
                if (price.compareTo(new BigDecimal("500")) > 0) {
                    category = "Premium Electronics";
                }
            }

            // determine price range
            String range = (price.compareTo(new BigDecimal("10.00")) <= 0) ? "Low"
                         : (price.compareTo(new BigDecimal("100.00")) <= 0) ? "Medium"
                         : (price.compareTo(new BigDecimal("500.00")) <= 0) ? "High"
                         : "Premium";
            
            result.add(new String[]{id, name, price.toString(), category, range});
        }

        return result;
    }

    private static void writeFile(List<String[]> rows, String path) {
        // Avoid crashing
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (String[] r : rows) {
                bw.write(String.join(",", r)); // seperate with commas
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("ERROR: Writing " + path + ": " + e.getMessage());
        }
    }
}