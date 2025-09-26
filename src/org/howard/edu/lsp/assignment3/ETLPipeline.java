package org.howard.edu.lsp.assignment3;

import java.util.*;

/**
 * The main orchestrator of the ETL (Extract, Transform, Load) pipeline.
 * Coordinates the reading, transformation, and writing of product data.
 * Provides summary reporting of the pipeline execution results.
 * 
 * @author Gael Santos
 * @version 1.0
 */

public class ETLPipeline {
    private static final String INPUT_FILE = "data/products.csv";
    private static final String OUTPUT_FILE = "data/transformed_products.csv";
    
    // make objects from other classes
    private DataReader reader;
    private DataWriter writer;
    private DataTransformer transformer;
    
    /**
     * Constructs an ETLPipeline with the necessary components for
     * reading, transforming, and writing product data.
     */
    public ETLPipeline() {
        this.reader = new DataReader();
        this.writer = new DataWriter();
        this.transformer = new DataTransformer();
    }
    
    /**
     * Executes the complete ETL pipeline process:
     * 1. Extract: Reads products from the input CSV file
     * 2. Transform: Applies business rules and transformations
     * 3. Load: Writes transformed products to the output CSV file
     * 4. Reports summary statistics of the execution
     * Stops gracefully if reading fails and provides error messages.
     */
    public void execute() {
        // Read file
        List<Product> products = reader.readProducts(INPUT_FILE);
        if (products == null) return;  // Cancel if reading fails
        
        // Transform products
        List<Product> transformedProducts = transformer.transformAllProducts(products);
        
        // Write to new file
        writer.writeProducts(transformedProducts, OUTPUT_FILE);
        
        // Summary
        int rowsRead = products.size();
        int rowsTransformed = transformedProducts.size();
        int rowsSkipped = rowsRead - rowsTransformed;
        
        System.out.println("Summary:");
        System.out.println("Rows read: " + rowsRead);
        System.out.println("Rows transformed: " + rowsTransformed);
        System.out.println("Rows skipped: " + rowsSkipped);
        System.out.println("Output written to: " + OUTPUT_FILE);
    }
    
    /**
     * Main entry point for the ETL pipeline application.
     * Creates and executes the pipeline.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
    	// Make new pipeline object to execute
        ETLPipeline pipeline = new ETLPipeline();
        // Execute
        pipeline.execute();
    }
}