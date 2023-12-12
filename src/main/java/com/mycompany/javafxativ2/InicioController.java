/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javafxativ2;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author hugop
 */
public class InicioController {
    
    private int persistenciaSelecionada;
    @FXML
    private Button buttonJson;

    @FXML
    private Button buttonXml;

    @FXML
    private Button buttonBd;
    
    
    @FXML
    private void navegarMenu(ActionEvent event) throws IOException {
        if (event.getSource() == buttonJson) {
            persistenciaSelecionada = 1;
        } else if (event.getSource() == buttonXml) {
            persistenciaSelecionada = 2;
        } else if (event.getSource() == buttonBd) {
            persistenciaSelecionada = 3;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent root = loader.load();
        MenuController menuController = loader.getController();
        menuController.setPersistenciaSelecionada(persistenciaSelecionada);

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}

