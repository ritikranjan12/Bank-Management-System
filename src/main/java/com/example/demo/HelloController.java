package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Objects;




public class HelloController {
    public  String user;
    public static StringBuilder finaluser;
    public  String name;
    @FXML
    public Button login;
    @FXML
    public Button register;
    @FXML
    public Label loginfailed;

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Stage stage;
    @FXML
    private Parent root;
    @FXML
    private Scene scene;


    @FXML
    public void register(ActionEvent actionEvent) throws IOException {

        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("register.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }


    @FXML
    public void login(ActionEvent actionEvent) throws IOException, SQLException {
        user = username.getText();
        long pass = Integer.parseInt(password.getText());

        Connection conn = getconnection();
        String sql = "select userid, password from bank where userid = ?";
        Statement myst = conn.prepareStatement(sql);
        ((PreparedStatement) myst).setString(1, String.valueOf(user));
        ResultSet resultset = ((PreparedStatement) myst).executeQuery();
        long verifypassword = 0;
        while(resultset.next()){
            name = resultset.getString("userid");

            verifypassword = Long.parseLong(resultset.getString("password"));

        }
        finaluser = new StringBuilder(user);
        if (!Objects.equals(user, name)){
            loginfailed.setText("Failed! Either username or Password is wrong");
        } else if(pass!=verifypassword){
            loginfailed.setText("Failed! Either username or Password is wrong");
        } else if (pass==verifypassword && Objects.equals(user, name)) {


            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("base-view.fxml")));
            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }  else {
            loginfailed.setText("Failed! Either username or Password is wrong");
        }


    }




    // connecting Function to the Database
    public Connection getconnection() {
        try{
            String driver = "com.mysql.cj.jdbc.Driver";
            String databaseurl = "jdbc:mysql://sql6.freesqldatabase.com/sql6480283";
            String databaseuser = "sql6480283";
            String databasepassword = "5uhSAMHesx";
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
}