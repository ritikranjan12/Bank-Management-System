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
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class PinChange extends HelloController{
    @FXML
    public Label updatepinlabel;
    private int pin;
    private long mobile;
    @FXML
    public PasswordField oldpin;
    @FXML
    public PasswordField newpinid;
    @FXML
    public Button submitpin;
    @FXML
    private Stage stage;
    @FXML
    private Parent root;
    @FXML
    private Scene scene;

    public void initialize() throws SQLException {
        Connection conn = getconnection();
        String sql = "select pin from bank where userid = ?";
        Statement myst = conn.prepareStatement(sql);
        ((PreparedStatement) myst).setString(1, String.valueOf(finaluser));
        ResultSet resultset = ((PreparedStatement) myst).executeQuery();
        while (resultset.next()) {
            pin = Integer.parseInt(resultset.getString("pin"));

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



    public void updatepin(ActionEvent actionEvent) throws SQLException {
        int lastpin = Integer.parseInt(oldpin.getText());
        int pintochange = Integer.parseInt(newpinid.getText());
        if(pintochange == lastpin){
            updatepinlabel.setText("Old Pin and New Pin cannot be equal!");
        } else if (lastpin == pin) {
            Connection conn1 = getconnection();
            String sql1 = "UPDATE bank set pin= ? where userid= ? ";
//            System.out.println(sql);
            Statement myst1 = conn1.prepareStatement(sql1);
            ((PreparedStatement) myst1).setString(1, String.valueOf(pintochange));
            ((PreparedStatement) myst1).setString(2, String.valueOf(finaluser));
            ((PreparedStatement) myst1).executeUpdate();
            System.out.println("Data updated");

            updatepinlabel.setText("Pin Changed Successfully");
        }  else{
            updatepinlabel.setText("Please Enter correct Credentials ");
        }
    }
}
