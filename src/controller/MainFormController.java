package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MainFormController {

    @FXML
    private TextField txtPartSearch;

    @FXML
    private TableView<?> tvParts;

    @FXML
    private Button btnAddParts;

    @FXML
    private Button btnModifyParts;

    @FXML
    private Button btnDeleteParts;

    @FXML
    private TextField txtProductSearch;

    @FXML
    private TableView<?> tvProducts;

    @FXML
    private Button btnAddProducts;

    @FXML
    private Button btnModifyProducts;

    @FXML
    private Button btnDeleteProducts;

    @FXML
    private Button btnExit;

    @FXML
    void exitButton(ActionEvent event) {
        System.exit(0);
    }
}
