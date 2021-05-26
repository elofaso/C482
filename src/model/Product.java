package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Models a Product
 *
 * @author Eric Lofaso
 */
public class Product {

    /**
     * Parts associated with the Product
     */
    private final ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /**
     * Product ID.
     */
    private int id;

    /**
     * Product Name
     */
    private String name;

    /**
     * Product Price
     */
    private double price;

    /**
     * Product Stock Level (Inventory)
     */
    private int stock;

    /**
     * Minimum Stock Level
     */
    private int min;

    /**
     * Maximum Stock Level
     */
    private int max;

    /**
     * Constructor
     *
     * @param id Product ID.
     * @param name Product Name.
     * @param price Product Price.
     * @param stock Product Stock Level.
     * @param min Product Minimum Stock Level.
     * @param max Product Maximum Stock Level.
     */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * Getter for Product ID
     *
     * @return Product ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for Product ID
     *
     * @param id New product ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for Product Name.
     *
     * @return Product Name.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for Product Name.
     *
     * @param name New product Name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for Product Price.
     *
     * @return Product price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Setter for Product Price.
     *
     * @param price New product price.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Getter for Stock Level.
     *
     * @return Stock level.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Setter for Stock Level.
     *
     * @param stock New stock level.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Getter for Minimum Stock Level.
     *
     * @return Minium stock level.
     */
    public int getMin() {
        return min;
    }

    /**
     * Setter for Minimum Stock Level.
     *
     * @param min New minimum stock level.
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * Getter for Maximum Stock Level
     *
     * @return Maximum stock level.
     */
    public int getMax() {
        return max;
    }

    /**
     * Setter for Maximum Stock Level.
     *
     * @param max New maximum stock level.
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Adds Associated Part.
     *
     * @param part The part to add.
     */
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    /**
     * Deletes Associated Part
     *
     * @param selectedAssociatedPart The part to delete.
     * @return Status of part deletion.
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        if (associatedParts.contains(selectedAssociatedPart)) {
            associatedParts.remove(selectedAssociatedPart);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Gets all Associated Parts
     *
     * @return List of associated part.
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return this.associatedParts;
    }

}
