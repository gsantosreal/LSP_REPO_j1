/**
 * 
 * Gael Santos
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
        List<String[]> data = readFile(INPUT_FILE);
        if (data == null) return;  // stop if no input file

        List<String[]> transformed = transformFile(data); 
        writeFile(transformed, OUTPUT_FILE);

        int rowsRead = Math.max(data.size() - 1, 0);
        int rowsTransformed = Math.max(transformed.size() - 1, 0);
        int rowsSkipped = rowsRead - rowsTransformed;

        System.out.println("Summary:");
        System.out.println("Rows read: " + rowsRead);
        System.out.println("Rows transformed: " + rowsTransformed);
        System.out.println("Rows skipped: " + rowsSkipped);
        System.out.println("Output written to: " + OUTPUT_FILE);
    }
    
    private static List<String[]> readFile(String path) {
        Path file = Paths.get(path);

        if (!Files.exists(file)) {
            System.err.println("ERROR: " + path + " not found.");
            return null;
        }

        List<String[]> rows = new ArrayList<>();
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
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (String[] r : rows) {
                bw.write(String.join(",", r));
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("ERROR: Writing " + path + ": " + e.getMessage());
        }
    }
}