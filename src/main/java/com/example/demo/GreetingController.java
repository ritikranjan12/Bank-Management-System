package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class GreetingController extends HelloController {
    private String name1;

    @FXML
    public Label usergreeting;
    @FXML
    public Button withdraw;
    @FXML
    public Button balance;

    @FXML
    private Stage stage;
    @FXML
    private Parent root;
    @FXML
    private Scene scene;

    @FXML
    public void initialize()  throws SQLException {
        Connection conn = getconnection();
        String sql = "select name from bank where userid = ?";
        Statement myst = conn.prepareStatement(sql);
        ((PreparedStatement) myst).setString(1, String.valueOf(finaluser));
        ResultSet resultset = ((PreparedStatement) myst).executeQuery();
        while(resultset.next()){
            name1 = resultset.getString("name");
        }

        usergreeting.setText("Welcome " + name1);
        usergreeting.setFont(Font.font("Verdana",23));

    }
    @FXML
    public void balance(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("check-balance.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }
    @FXML
    public void withdraw(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("withdraw.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    @FXML
    public void changepin(ActionEvent actionEvent) {
    }
    @FXML
    public void changemobile(ActionEvent actionEvent) {
    }

    public Connection getconnection() {
        try{
            String driver = "com.mysql.cj.jdbc.Driver";
            String databaseurl = "jdbc:mysql://localhost:3306/ritikbank";
            String databaseuser = "root";
            String databasepassword = "";
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(databaseurl,databaseuser,databasepassword);
            System.out.println("Connected");
            return conn;

        }
        catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public void logout(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void changepinbyverification(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("pin-change.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

    public void Showdetails(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("show-account.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();


    }
}
