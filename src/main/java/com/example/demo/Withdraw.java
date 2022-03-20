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

public class Withdraw  extends HelloController{
    private int pin1;
    private long amount;
    @FXML
    public TextField amountofcash;
    @FXML
    public PasswordField withdrawpin;
    @FXML
    public Button withdraw;
    @FXML
    public Label amountwithdraw;
    @FXML
    public Label balance;
    @FXML
    private Stage stage;
    @FXML
    private Parent root;
    @FXML
    private Scene scene;


    public void withdrawcash(ActionEvent actionEvent) throws SQLException {
        pin1 = Integer.parseInt(withdrawpin.getText());
        amount = Long.parseLong(amountofcash.getText());
        Connection conn = getconnection();
        String sql = "select balance,pin from bank where userid = ?";
        Statement myst = conn.prepareStatement(sql);
        ((PreparedStatement) myst).setString(1, String.valueOf(finaluser));
        ResultSet resultset = ((PreparedStatement) myst).executeQuery();
        long dbbalance = 0;
        int dbpin = 0;
        while(resultset.next()){
            dbbalance = Long.parseLong(resultset.getString("balance"));
            dbpin = Integer.parseInt(resultset.getString("pin"));
        }
        if (pin1==dbpin && amount<=dbbalance){
            amountwithdraw.setText("Amount Withdrawn = "+amount);
            amountwithdraw.setFont(Font.font("Verdana",16));
            long newbalance = (dbbalance - amount);
            String sql1 = "UPDATE bank set balance= ? where userid= ? ";
//            System.out.println(sql);
            Statement myst1 = conn.prepareStatement(sql1);
            ((PreparedStatement) myst1).setString(1, String.valueOf(newbalance));
            ((PreparedStatement) myst1).setString(2, String.valueOf(finaluser));
            ((PreparedStatement) myst1).executeUpdate();
            System.out.println("Data updated");

            balance.setText("Your Balance : " + "Rs "+newbalance);
            balance.setFont(Font.font("Verdana",15));
        } else if(pin1!=dbpin) {
            amountwithdraw.setText("Please Enter Correct Pin !");
            amountwithdraw.setFont(Font.font("Verdana",16));
        } else {
            amountwithdraw.setText("Not Enough Money to Proceed !");
            amountwithdraw.setFont(Font.font("Verdana",16));
        }

    }

    public void movetomenu(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("base-view.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
