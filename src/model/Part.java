package model;

/**
 * Abstract base class for Part
 *
 * @author Eric Lofaso
 */
public abstract class Part {

    /**
     * Part ID
     */
    private int id;

    /**
     * Part Name
     */
    private String name;

    /**
     * Part Price
     */
    private double price;

    /**
     * Part stock level (inventory)
     */
    private int stock;

    /** Minimum stock level of Part
     *
     */
    private int min;

    /** Maximum stock level of Part
     *
     */
    private int max;

    /**
     * Part Constructor
     *
     * @param id Part ID.
     * @param name Part Name.
     * @param price Part Price.
     * @param stock Part Stock Level.
     * @param min Part Minimum Stock Level.
     * @param max Part Maximum Stock Level.
     */
    public Part(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * Getter for Part ID
     *
     * @return The part ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for Part ID
     *
     * @param id The part ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for Part Name
     *
     * @return The part name.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for Part Name
     *
     * @param name The part name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for Part Price
     *
     * @return The part price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Setter for Part Price
     *
     * @param price The part price to set.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Getter for Stock Level
     *
     * @return The stock level.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Setter for Stock Level
     *
     * @param stock The new stock level.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Getter for Minimum Stock Level
     *
     * @return The minimum stock level.
     */
    public int getMin() {
        return min;
    }

    /**
     * Setter for Minimum Stock Level
     *
     * @param min The new minimum stock level.
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * Getter for Maximum Stock Level
     *
     * @return The maximum stock level.
     */
    public int getMax() {
        return max;
    }

    /**
     * Setter for Maximum Stock Level
     *
     * @param max The new maximum stock level.
     */
    public void setMax(int max) {
        this.max = max;
    }
}