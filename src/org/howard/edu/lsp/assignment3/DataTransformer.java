package org.howard.edu.lsp.assignment3;

import java.util.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Handles all data transformation logic for the ETL pipeline. Applies business rules
 * including name normalization, electronics discounts, and price range categorization.
 * This class encapsulates all transformation rules separately from I/O operations.
 * 
 * @author Gael Santos
 * @version 1.0
 */
public class DataTransformer {
	
	/**
     * Applies all transformation rules to a single product. This includes:
     * - Converting product name to uppercase
     * - Applying 10% discount for electronics products (with HALF_UP rounding)
     * - Categorizing products into price ranges
     * - Upgrading to "Premium Electronics" for high-value electronics
     * Creates a copy of the product to avoid modifying the original.
     *
     * @param product the original product to transform
     * @return a new transformed Product object
     */
    public Product transformProduct(Product product) {
    	
        Product transformed = new Product(
            product.getId(),
            product.getName(),
            product.getPrice(),
            product.getCategory()
        );
        
        transformed.setName(transformed.getName().toUpperCase());
        
        // Call private methods
        applyElectronicsTransformation(transformed);
        applyPriceRange(transformed);
        
        return transformed;
    }
    
    private void applyElectronicsTransformation(Product product) {
        if (product.getCategory().equalsIgnoreCase("Electronics")) {
            // 10% discount
            BigDecimal newPrice = product.getPrice().multiply(new BigDecimal("0.90")).setScale(2, RoundingMode.HALF_UP);
            product.setPrice(newPrice);
            
            // Premium Check
            if (newPrice.compareTo(new BigDecimal("500")) > 0) {
                product.setCategory("Premium Electronics");
            }
        }
    }
    
    private void applyPriceRange(Product product) {
    	// Price range
        BigDecimal price = product.getPrice();
        String range = (price.compareTo(new BigDecimal("10.00")) <= 0) ? "Low"
                     : (price.compareTo(new BigDecimal("100.00")) <= 0) ? "Medium"
                     : (price.compareTo(new BigDecimal("500.00")) <= 0) ? "High"
                     : "Premium";
        product.setPriceRange(range);
    }
    
    /**
     * Applies transformations to all products in a list.
     *
     * @param products the list of products to transform
     * @return a new list containing the transformed products
     */
    public List<Product> transformAllProducts(List<Product> products) {
        List<Product> transformedProducts = new ArrayList<>();
        for (Product product : products) {
            transformedProducts.add(transformProduct(product));
        }
        return transformedProducts;
    }
}