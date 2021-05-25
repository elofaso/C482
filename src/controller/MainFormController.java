package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/***
 * Controller class which provides control logic for the Main Form.
 */
public class MainFormController implements Initializable {

    /***
     * Text field for parts search.
     */
    @FXML
    private TextField txtSearchParts;

    /***
     * Table view for the parts.
     */
    @FXML
    private TableView<Part> tvParts;

    /***
     * Part ID column.
     */
    @FXML
    private TableColumn<Part, Integer> colPartId;

    /***
     * Part Name column.
     */
    @FXML
    private TableColumn<Part, String> colPartName;

    /***
     * Part Inventory Level column.
     */
    @FXML
    private TableColumn<Part, Integer> colPartInventoryLevel;

    /***
     * Part Cost column.
     */
    @FXML
    private TableColumn<Part, Double> colPartPerUnitCost;

    /***
     * Text field for product search.
     */
    @FXML
    private TextField txtSearchProducts;

    /***
     * Table view for products.
     */
    @FXML
    private TableView<Product> tvProducts;

    /***
     * Product ID Column.
     */
    @FXML
    private TableColumn<Product, Integer> colProductId;

    /***
     * Product Name Column.
     */
    @FXML
    private TableColumn<Product, String> colProductName;

    /***
     * Product Inventory Level column.
     */
    @FXML
    private TableColumn<Product, Integer> colProductInventoryLevel;

    /***
     * Product Cost column.
     */
    @FXML
    private TableColumn<Product, Double> colProductPerUnitCost;

    /***
     * Button action to delete part.
     *
     * @param event Action event.
     */
    @FXML
    void deletePartButtonAction(ActionEvent event) {
        Part selectedPart = tvParts.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Parts");
        alert.setHeaderText("DELETE");
        alert.setContentText("Do you want to delete this part");
        alert.showAndWait();

        if (alert.getResult() == ButtonType.OK) {
            Inventory.deletePart(selectedPart);
            tvParts.setItems(Inventory.getAllParts());
        }
    }

    /***
     * Button action to delete product.
     *
     * @param event Action event.
     */
    @FXML
    void deleteProductButtonAction(ActionEvent event) {
        Product selectedProduct = tvProducts.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Products");
        alert.setHeaderText("DELETE");
        alert.setContentText("Do you want to delete this product");
        alert.showAndWait();

        if (alert.getResult() == ButtonType.OK) {
            Inventory.deleteProduct(selectedProduct);
            tvProducts.setItems(Inventory.getAllProducts());
        }
    }

    /***
     * Button action to exit program.
     *
     * @param event Action event.
     */
    @FXML
    void exitButtonAction(ActionEvent event) {
        System.exit(0);
    }

    /***
     * Key pressed in search box action.
     *
     * @param event Action event.
     */
    @FXML
    void searchPartKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String searchString = txtSearchParts.getText();
            if (searchString.matches("\\d+")) {
                int id = Integer.parseInt(searchString);
                Part searchPart = Inventory.lookupPart(id);
                tvParts.getSelectionModel().select(searchPart);
            } else {
                tvParts.setItems(Inventory.lookupPart(searchString));
            }
        }
    }

    /***
     * Key pressed in product search action.
     *
     * @param event Action event.
     */
    @FXML
    void searchProductKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String searchString = txtSearchProducts.getText();
            if (searchString.matches("\\d+")) {
                int id = Integer.parseInt(searchString);
                Product searchProduct = Inventory.lookupProduct(id);
                tvProducts.getSelectionModel().select(searchProduct);
            } else {
                tvProducts.setItems(Inventory.lookupProduct(searchString));
            }
        }
    }

    /***
     * Loads scene.
     *
     * @param event Event pass-through.
     * @param file File to load.
     * @throws IOException Exception from FXMLLoader.
     */
    private void loadScene(ActionEvent event, String file) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource(file));
        Scene scene = new Scene(parent);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /***
     * Add Part Button action.
     *
     * @param event Action event.
     * @throws IOException Exception from FXMLLoader.
     */
    @FXML
    void addPartButtonAction(ActionEvent event) throws IOException {
        loadScene(event, "../view/AddPartForm.fxml");

    }

    /***
     * Modify Part Button action.
     *
     * @param event Action event.
     * @throws IOException Exception from FXMLLoader.
     */
    @FXML
    void modifyPartButtonAction(ActionEvent event) throws IOException {
        int selectedPartIndex = tvParts.getSelectionModel().getSelectedIndex();
        Part selectedPart = tvParts.getSelectionModel().getSelectedItem();
        ModifyPartController.setPart(selectedPartIndex, selectedPart);

        loadScene(event, "../view/ModifyPartForm.fxml");

    }

    /***
     * Modify Product Button action.
     *
     * @param event Action event.
     * @throws IOException Exception from FXMLLoader.
     */
    @FXML
    void modifyProductButtonAction(ActionEvent event) throws IOException {
        int selectedProductIndex = tvProducts.getSelectionModel().getSelectedIndex();
        Product selectedProduct = tvProducts.getSelectionModel().getSelectedItem();
        ModifyProductController.setProduct(selectedProductIndex, selectedProduct);

        loadScene(event, "../view/ModifyProductForm.fxml");

    }

    /***
     * Add Product Button Action.
     *
     * @param event Action event.
     * @throws IOException Exception from FXMLLoader.
     */
    @FXML
    void addProductButtonAction(ActionEvent event) throws IOException {
        loadScene(event, "../view/AddProductForm.fxml");

    }


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tvParts.setItems(Inventory.getAllParts());
        colPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPartInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        colPartPerUnitCost.setCellValueFactory(new PropertyValueFactory<>("price"));

        tvProducts.setItems(Inventory.getAllProducts());
        colProductId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colProductName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colProductInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        colProductPerUnitCost.setCellValueFactory(new PropertyValueFactory<>("price"));

    }


}
