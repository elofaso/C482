package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class provides static data for the application.
 *
 * @author Eric Lofaso
 */
public class Inventory {

    /**
     * All parts in inventory.
     */
    private static final ObservableList<Part> allParts = FXCollections.observableArrayList();

    /**
     * All products in inventory.
     */
    private static final ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * Part ID.
     */
    private static int partId = 0;

    /**
     * Product ID.
     */
    private static int productId = 0;

    /**
     * Adds a part to allParts.
     *
     * @param newPart The part object to add.
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /**
     * Adds a product to allProducts
     *
     * @param newProduct The product object to add.
     */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /**
     * Looks up a part by Part ID
     *
     * @param partId The part Id to search.
     * @return The part object if found, null if not found.
     */
    public static Part lookupPart(int partId) {
        if (!allParts.isEmpty()) {
            for (int i = 0; i < allParts.size(); i++) {
                if (allParts.get(i).getId() == partId) {
                    return allParts.get(i);
                }
            }
        }

        return null;
    }

    /**
     * Looks up parts by partial Part Name
     *
     * @param partName Text string to search for.
     * @return Array of parts if found, null if not found.
     */
    public static ObservableList<Part> lookupPart(String partName) {
        if (!allParts.isEmpty()) {
            ObservableList partSearchList = FXCollections.observableArrayList();
            for (Part p : getAllParts()) {
                if (p.getName().contains(partName)) {
                    partSearchList.add(p);

                }
            }
            return partSearchList;
        }
        return null;
    }

    /**
     * Looks up a product by Product ID.
     *
     * @param productId Product Id to search for.
     * @return Product object if found, null if not found.
     */
    public static Product lookupProduct(int productId) {
        if (!allProducts.isEmpty()) {
            for (int i = 0; i < allProducts.size(); i++) {
                if (allProducts.get(i).getId() == productId) {
                    return allProducts.get(i);
                }
            }
        }
        return null;
    }

    /**
     * Looks up products by partial Product Name.
     *
     * @param productName Text string to search for.
     * @return Array of products if found, null if not found.
     */
    public static ObservableList<Product> lookupProduct(String productName) {
        if (!allProducts.isEmpty()) {
            ObservableList productSearchList = FXCollections.observableArrayList();
            for (Product p : getAllProducts()) {
                if (p.getName().contains(productName)) {
                    productSearchList.add(p);
                }
            }
            return productSearchList;
        }
        return null;
    }

    /**
     * Updates a Part.
     *
     * @param index Index position of part in array.
     * @param selectedPart Updated part object.
     */
    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    /**
     * Updates a Product
     *
     * @param index Index position of product in array.
     * @param selectedProduct Updated product object.
     */
    public static void updateProduct(int index, Product selectedProduct) {
        allProducts.set(index, selectedProduct);
    }

    /**
     * Deletes a Part
     *
     * @param selectedPart Part object to delete.
     * @return Updated parts array.
     */
    public static boolean deletePart(Part selectedPart) {
        return allParts.remove(selectedPart);
    }

    /**
     * Deletes a Product.
     *
     * @param selectedProduct Product object to delete.
     * @return Updated product array.
     */
    public static boolean deleteProduct(Product selectedProduct) {
        return allProducts.remove(selectedProduct);
    }

    /**
     * Returns a list of all Parts
     *
     * @return List of all parts.
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * Returns a list of all Products
     *
     * @return List of all products.
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    /**
     * Returns a sequential Part ID
     *
     * @return The generated part ID.
     */
    public static int getPartId() {
        partId++;
        return partId;
    }

    /**
     * Returns a sequential Product ID
     *
     * @return The generated product ID.
     */
    public static int getProductId() {
        productId++;
        return productId;
    }
}
