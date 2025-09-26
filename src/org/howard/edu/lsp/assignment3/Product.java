package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Represents a product with each of its attributes and provides logic for
 * price formatting. This class serves as the domain model for the ETL pipeline, 
 * encapsulating all product-related data.
 * 
 * @author Gael Santos
 * @version 1.0
 */
public class Product {
	// Product variables
	private String id;
    private String name;
    private BigDecimal price;
    private String category;
    private String priceRange;
    
    /**
     * Constructs a new Product with the specified attributes.
     * The price is automatically rounded to 2 decimal places using HALF_UP rounding.
     *
     * @param id the unique identifier for the product
     * @param name the name of the product
     * @param price the price of the product (will be rounded to 2 decimal places)
     * @param category the category of the product
     */
    public Product(String id, String name, BigDecimal price, String category) {
        this.id = id;
        this.name = name;
        this.price = price.setScale(2, RoundingMode.HALF_UP);
        this.category = category;
    }
    
    /**
     * @return the product ID
     */
    public String getId() { return id; }
    
    /**
     * @return the product name
     */
    public String getName() { return name; }
    
    /**
     * @return the product price (rounded to 2 decimal places)
     */
    public BigDecimal getPrice() { return price; }
    
    /**
     * @return the product category
     */
    public String getCategory() { return category; }
    
    /**
     * @return the price range category (Low, Medium, High, Premium)
     */
    public String getPriceRange() { return priceRange; }
    
    /**
     * Sets the product name
     * @param name the new product name
     */
    public void setName(String name) { this.name = name; }
    /**
     * Sets the product price, automatically rounding to 2 decimal places
     * @param price the new product price
     */
    public void setPrice(BigDecimal price) { this.price = price.setScale(2, RoundingMode.HALF_UP); }
    
    /**
     * Sets the product category
     * @param category the new product category
     */
    public void setCategory(String category) { this.category = category; }
    
    /**
     * Sets the price range category
     * @param priceRange the price range (Low, Medium, High, Premium)
     */
    public void setPriceRange(String priceRange) { this.priceRange = priceRange; }
    
}

