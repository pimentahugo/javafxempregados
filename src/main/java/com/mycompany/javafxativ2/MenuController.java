/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javafxativ2;

import com.mycompany.javafxativ2.InicioController;
import com.mycompany.javafxativ2.CadastroController;
import com.mycompany.javafxativ2.interfaces.EmpregadoDaoInterface;
import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author hugop
 */
public class MenuController {

    private int persistenciaSelecionada;

    private EmpregadoDaoInterface empregados;

    public void setPersistenciaSelecionada(int persistenciaSelecionada) {
        this.persistenciaSelecionada = persistenciaSelecionada;
    }
    
    public void setDados(int persistenciaSelecionada, EmpregadoDaoInterface empregados) {
         this.empregados = empregados;
         this.persistenciaSelecionada = persistenciaSelecionada;
    }
    
    @FXML
    private void navegarMenu(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaCadastroJavaFX.fxml"));
        Parent root = loader.load();
        CadastroController menuController = loader.getController();
//        menuController.setPersistenciaSelecionada(persistenciaSelecionada);
        if (empregados != null) {
            menuController.setDados(persistenciaSelecionada, empregados);
        } else {
            menuController.setPersistenciaSelecionada(persistenciaSelecionada);
        }
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.centerOnScreen();
        stage.setMinWidth(1200);
        stage.setMinHeight(500);
        stage.setMaxWidth(1200);
        stage.setMaxHeight(500);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void navegarConsulta(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaConsulta.fxml"));
        Parent root = loader.load();
        ConsultaController consulta = loader.getController();
        if (empregados != null) {
            consulta.setDados(persistenciaSelecionada, empregados);
        } else {
            consulta.setPersistenciaSelecionada(persistenciaSelecionada);
        }

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.centerOnScreen();
        stage.setMinWidth(800);
        stage.setMinHeight(400);
        stage.setMaxWidth(800);
        stage.setMaxHeight(400);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void voltarInicio(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Inicio.fxml"));
        Parent root = loader.load();

        InicioController menu = loader.getController();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.centerOnScreen();
        stage.setMinWidth(600);
        stage.setMinHeight(400);
        stage.setMaxWidth(600);
        stage.setMaxHeight(400);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
