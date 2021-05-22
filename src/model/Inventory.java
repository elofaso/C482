package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private static int partId = 0;
    private static int productId = 0;

    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }


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


    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }


    public static boolean deletePart(Part selectedPart) {
        return allParts.remove(selectedPart);
    }


    public static boolean deleteProduct(Product selectedProduct) {
        return allProducts.remove(selectedProduct);
    }


    public static ObservableList<Part> getAllParts() {
        return allParts;
    }


    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    public static int getPartId() {
        partId++;
        return partId;
    }

    public static int getProductId() {
        productId++;
        return productId;
    }
}