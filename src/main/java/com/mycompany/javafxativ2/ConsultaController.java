/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javafxativ2;

import com.mycompany.javafxativ2.dao.EmpregadoDaoBD;
import com.mycompany.javafxativ2.dao.EmpregadoDaoJSON;
import com.mycompany.javafxativ2.dao.EmpregadoDaoXML;
import com.mycompany.javafxativ2.interfaces.EmpregadoDaoInterface;
import com.mycompany.javafxativ2.models.EmpregadoTableView;
import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author hugop
 */
public class ConsultaController {

    private int persistenciaSelecionada;

    private EmpregadoDaoInterface empregados;

    @FXML
    private TableView<EmpregadoTableView> tabela = new TableView<EmpregadoTableView>();
    @FXML
    private TableColumn<EmpregadoTableView, String> colId = new TableColumn<>();
    @FXML
    private TableColumn<EmpregadoTableView, String> colNome = new TableColumn<>();
    @FXML
    private TableColumn<EmpregadoTableView, String> colCargo = new TableColumn<>();
    @FXML
    private TableColumn<EmpregadoTableView, String> colDataAdmissao = new TableColumn<>();
    @FXML
    private TableColumn<EmpregadoTableView, String> colSalario = new TableColumn<>();
    @FXML
    private TableColumn<EmpregadoTableView, String> colBonus = new TableColumn<>();
    @FXML
    private TableColumn<EmpregadoTableView, String> colBonusCoop = new TableColumn<>();
    @FXML
    private TableColumn<EmpregadoTableView, String> colTurno = new TableColumn<>();
    @FXML
    private TableColumn<EmpregadoTableView, String> colAreaProg = new TableColumn<>();

//    public void setEmpregados(EmpregadoDaoInterface empregados) throws SQLException {
//        this.empregados = empregados;
//        carregarDadosIniciais();
//    }
    
    public void setDados(int persistenciaSelecionada, EmpregadoDaoInterface empregados) throws SQLException {
         this.empregados = empregados;
         this.persistenciaSelecionada = persistenciaSelecionada;
         carregarDadosIniciais();
    }

    public void setPersistenciaSelecionada(int persistenciaSelecionada) throws SQLException {
        this.persistenciaSelecionada = persistenciaSelecionada;
        carregarDadosIniciais();
    }

    private void carregarDadosIniciais() throws SQLException {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colCargo.setCellValueFactory(new PropertyValueFactory<EmpregadoTableView, String>("tipoColaborador"));
        colDataAdmissao.setCellValueFactory(new PropertyValueFactory<EmpregadoTableView, String>("dataContratacao"));
        colSalario.setCellValueFactory(new PropertyValueFactory<EmpregadoTableView, String>("salario"));
        colBonus.setCellValueFactory(new PropertyValueFactory<EmpregadoTableView, String>("bonus"));
        colBonusCoop.setCellValueFactory(new PropertyValueFactory<EmpregadoTableView, String>("bonusCooparticipacao"));
        colTurno.setCellValueFactory(new PropertyValueFactory<EmpregadoTableView, String>("turno"));
        colAreaProg.setCellValueFactory(new PropertyValueFactory<EmpregadoTableView, String>("areaProg"));
        atualizarTabela();
    }

    private void atualizarTabela() throws SQLException {
        // Atualiza os dados da tabela com os dados mais recentes do EmpregadoDaoInterface
        tabela.getItems().clear(); // Limpa os itens existentes na tabela
        tabela.getItems().addAll(empregados.getLista()); // Adiciona os novos itens

        // Você pode adicionar outras lógicas de atualização da tabela, se necessário
    }

    @FXML
    private void voltarParaTelaAnterior(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent root = loader.load();

        MenuController menu = loader.getController();
        menu.setDados(persistenciaSelecionada, empregados);
//        menu.setEmpregados(empregados);
//        menu.setPersistenciaSelecionada(persistenciaSelecionada);

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
