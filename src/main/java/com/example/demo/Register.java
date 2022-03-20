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

public class Register extends HelloController{
    private static long accno =100000001;
    @FXML
    public TextField givenname;
    @FXML
    public TextField givenusername;
    @FXML
    public TextField givenemail;
    @FXML
    public TextField givenmb;
    @FXML
    public TextField givenadd;
    @FXML
    public TextField givenpan;
    @FXML
    public PasswordField givenpass;
    @FXML
    public PasswordField givenconpass;
    @FXML
    public PasswordField givenpin;
    @FXML
    public PasswordField givenconpin;
    @FXML
    public Button submit;
    @FXML
    public Label accountlabel;
    @FXML
    public TextField givenaadhar;
    @FXML
    public Button backtologin;
    @FXML
    private Stage stage;
    @FXML
    private Parent root;
    @FXML
    private Scene scene;

    public void submit(ActionEvent actionEvent) throws SQLException {
        String name1 = givenname.getText();
        String userid1 = givenusername.getText();
        String email1 = givenemail.getText();
        String add1 = givenadd.getText();
        String pan1 = givenpan.getText();
        String aadhar1 = givenaadhar.getText();
        long mobile1 = Long.parseLong(givenmb.getText());
        long pass1 = Long.parseLong(givenpass.getText());
        long conpass1 = Long.parseLong(givenconpass.getText());
        int pin1 = Integer.parseInt(givenpin.getText());
        int conpin1 = Integer.parseInt(givenconpin.getText());
        if(pass1==conpass1 && pin1==conpin1){
            Connection conn = getconnection();
            String sql = "insert into bank values(?,?,?,?,?,?,?,?,?,?,?)";
            Statement myst = conn.prepareStatement(sql);
            ((PreparedStatement) myst).setString(1, name1);
            ((PreparedStatement) myst).setString(2, String.valueOf(userid1));
            ((PreparedStatement) myst).setString(3, String.valueOf(accno));
            ((PreparedStatement) myst).setString(4, String.valueOf(pass1));
            ((PreparedStatement) myst).setString(5, String.valueOf(pin1));
            ((PreparedStatement) myst).setString(6, String.valueOf(mobile1));
            ((PreparedStatement) myst).setString(7, add1);
            ((PreparedStatement) myst).setString(8, pan1);
            ((PreparedStatement) myst).setString(9, String.valueOf(10000));
            ((PreparedStatement) myst).setString(10, aadhar1);
            ((PreparedStatement) myst).setString(11, email1);
            ((PreparedStatement) myst).execute();
            accountlabel.setText("Your Account no is " + accno++);

        } else if(pass1!=conpass1){
            accountlabel.setText("Password and Confirm password must be same");
        } else if(pin1!=conpin1){
            accountlabel.setText("Pin and Confirm Pin must be same");
        } else {
            accountlabel.setText("Something Went wrong !");
        }
    }

    public void backtologin(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
