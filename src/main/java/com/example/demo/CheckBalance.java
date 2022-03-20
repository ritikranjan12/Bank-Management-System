package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class CheckBalance extends HelloController {

    private  long acno;
    private int pin;
    private long balance;
    @FXML
    public PasswordField checkpin;
    @FXML
    public Button checkbalance;
    @FXML
    private Label showbalance;
    @FXML
    private TextField account;
    @FXML
    private Stage stage;
    @FXML
    private Parent root;
    @FXML
    private Scene scene;

    @FXML
    public void initialize() throws SQLException {
        Connection conn = getconnection();
        String sql = "select accountno,pin,balance from bank where userid = ?";
        Statement myst = conn.prepareStatement(sql);
        ((PreparedStatement) myst).setString(1, String.valueOf(finaluser));
        ResultSet resultset = ((PreparedStatement) myst).executeQuery();
        while (resultset.next()) {
            acno = Long.parseLong(resultset.getString("accountno"));
            pin = Integer.parseInt(resultset.getString("pin"));
            balance = Long.parseLong(resultset.getString("balance"));
        }

        account.setText(String.valueOf(acno));
    }


    public void checkingbalance(ActionEvent actionEvent) {
        int getpin = Integer.parseInt(checkpin.getText());
        if(getpin==pin){
            showbalance.setText("Your Balance : " + "Rs " + balance);
            showbalance.setFont(Font.font("Verdana",19));
        } else {
            showbalance.setText("Please Enter Correct Pin");
            showbalance.setFont(Font.font("Verdana",19));
        }

    }

    public void backtomenu(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("base-view.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
