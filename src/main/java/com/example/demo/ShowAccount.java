package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class ShowAccount extends HelloController{

    @FXML
    public Label accname;
    @FXML
    public Label accountno;
    @FXML
    public Label acusername;
    @FXML
    public Label mobileno;
    @FXML
    public Label Address;
    @FXML
    public Label panno;
    @FXML
    public Label aadharno;
    @FXML
    public Label email;
    @FXML
    private Stage stage;
    @FXML
    private Parent root;
    @FXML
    private Scene scene;

    @FXML
    public void initialize() throws SQLException{
        Connection conn = getconnection();
        String sql = "select * from bank where userid = ?";
        Statement myst = conn.prepareStatement(sql);
        ((PreparedStatement) myst).setString(1, String.valueOf(finaluser));
        ResultSet resultset = ((PreparedStatement) myst).executeQuery();
        String name = "";
        long acno = 0;
        String usid = "";
        long mbno = 0;
        String add = "";
        String pn = "";
        String adno = "";
        String email1 = "";
        while (resultset.next()) {
            name= resultset.getString("name");
            acno = Long.parseLong(resultset.getString("accountno"));
            usid = resultset.getString("userid");
            mbno = Long.parseLong(resultset.getString("mobileno"));
            add = resultset.getString("address");
            pn = resultset.getString("panno");
            adno = resultset.getString("aadharno");
            email1 = resultset.getString("email");
        }

        accname.setText("Name : " + name);
        accname.setFont(Font.font("Verdana",15));

        accountno.setText("Account no : " + acno);
        accountno.setFont(Font.font("Verdana",15));

        acusername.setText("Uesr Id : " + usid);
        acusername.setFont(Font.font("Verdana",15));

        mobileno.setText("Mobile no : " + mbno);
        mobileno.setFont(Font.font("Verdana",15));

        Address.setText("Address : " + add);
        Address.setFont(Font.font("Verdana",15));

        panno.setText("Panno : " + pn);
        panno.setFont(Font.font("Verdana",15));

        email.setText("Email : " + email1);
        email.setFont(Font.font("Verdana",15));

        aadharno.setText("Aadhar no : " + adno);
        aadharno.setFont(Font.font("Verdana",15));


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
