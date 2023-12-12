/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javafxativ2;

import com.mycompany.javafxativ2.models.EmpregadoTableView;
import com.mycompany.javafxativ2.dao.EmpregadoDaoBD;
import com.mycompany.javafxativ2.interfaces.EmpregadoDaoInterface;
import com.mycompany.javafxativ2.dao.EmpregadoDaoJSON;
import com.mycompany.javafxativ2.dao.EmpregadoDaoXML;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class CadastroController {

    // Suponha que você já tenha esses campos na sua classe de controlador
    @FXML
    private TextField nomeField;
    @FXML
    private DatePicker dataAdmissaoField;
    @FXML
    private TextField salarioField;
    @FXML
    private TextField areaProgField;
    @FXML
    private TextField bonusField;
    @FXML
    private ComboBox turnoField;
    @FXML
    private ComboBox tipoColaboradorField;
    @FXML
    private TextField bonusCoopField;
    @FXML
    private Text nomeLabel;
    @FXML
    private Text dataLabel;
    @FXML
    private Text salarioLabel;
    @FXML
    private Text areaLabel;
    @FXML
    private Text bonusCoopLabel;
    @FXML
    private Text turnoLabel;
    @FXML
    private Text bonusLabel;
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
    @FXML
    private Button buttonLerDados;
    @FXML
    private Button buttonGravarDados;

    private int persistenciaSelecionada;

    private EmpregadoDaoInterface empregados;

    public void setPersistenciaSelecionada(int persistenciaSelecionada) throws SQLException {
        this.persistenciaSelecionada = persistenciaSelecionada;
        carregarDadosIniciais();
    }

    public void setDados(int persistenciaSelecionada, EmpregadoDaoInterface empregados) throws SQLException {
         this.empregados = empregados;
         this.persistenciaSelecionada = persistenciaSelecionada;
         carregarDadosIniciais();
    }

    private void carregarDadosIniciais() throws SQLException {
        tipoColaboradorField.getItems().addAll("Selecione um tipo de colaborador", "Gerente", "Executivo", "Secretário", "Programador"); // Adapte conforme necessário
        turnoField.getItems().addAll("Manhã", "Tarde", "Noite");
        clearFields();
        System.out.println("Tela de cadastro carregada!");

        tipoColaboradorField.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // Obtenha o índice da opção selecionada
            int indiceSelecionado = tipoColaboradorField.getSelectionModel().getSelectedIndex();
            visibleFields(indiceSelecionado);
            limparCampos();
        });

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colCargo.setCellValueFactory(new PropertyValueFactory<EmpregadoTableView, String>("tipoColaborador"));
        colDataAdmissao.setCellValueFactory(new PropertyValueFactory<EmpregadoTableView, String>("dataContratacao"));
        colSalario.setCellValueFactory(new PropertyValueFactory<EmpregadoTableView, String>("salario"));
        colBonus.setCellValueFactory(new PropertyValueFactory<EmpregadoTableView, String>("bonus"));
        colBonusCoop.setCellValueFactory(new PropertyValueFactory<EmpregadoTableView, String>("bonusCooparticipacao"));
        colTurno.setCellValueFactory(new PropertyValueFactory<EmpregadoTableView, String>("turno"));
        colAreaProg.setCellValueFactory(new PropertyValueFactory<EmpregadoTableView, String>("areaProg"));

        if (persistenciaSelecionada == 1) {
            empregados = new EmpregadoDaoJSON();
        } else if (persistenciaSelecionada == 2) {
            empregados = new EmpregadoDaoXML();
        } else if (persistenciaSelecionada == 3) {
            empregados = new EmpregadoDaoBD();
            buttonLerDados.setDisable(true);
            buttonGravarDados.setDisable(true);
        }
        atualizarTabela();
        System.out.println("opcao:" + persistenciaSelecionada);
    }

//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//    }
    @FXML
    private void escolherArquivo(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        File arquivoEscolhido = fileChooser.showOpenDialog(new Stage());

        if (this.persistenciaSelecionada == 1) {
            fileChooser.setTitle("Escolha um arquivo JSON");
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Arquivos JSON (*.json)", "*.json");
            fileChooser.getExtensionFilters().add(extFilter);
        } else if (this.persistenciaSelecionada == 2) {
            fileChooser.setTitle("Escolha um arquivo XML");
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Arquivos XML (*.xml)", "*.xml");
            fileChooser.getExtensionFilters().add(extFilter);
        }

        if (arquivoEscolhido != null) {
            if (this.empregados.getLista() != null) {
                boolean confirmacao = mostrarMensagemConfirmacao("Você possui dados que serão perdidos. Deseja continuar?");
                if (confirmacao) {
                    this.empregados.setLista(this.empregados.carregarDados(arquivoEscolhido));
                    preencherTabela(this.empregados.getLista());
                }
            } else {
                this.empregados.setLista(this.empregados.carregarDados(arquivoEscolhido));
                preencherTabela(this.empregados.getLista());
            }
        }
    }

    @FXML
    private void btnSalvarAction(ActionEvent event) {
        if (empregados.getLista().isEmpty()) {
            mostrarAlerta("Lista está vazia, por favor, cadastre antes de salvar um arquivo.", AlertType.ERROR);
        } else {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Salvar Dados");

            if (persistenciaSelecionada == 1) {
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Arquivos JSON (*.json)", "*.json"));
            } else if (persistenciaSelecionada == 2) {
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Arquivos XML (*.xml)", "*.xml"));
            }

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // Obtém a janela atual
            File arquivoEscolhido = fileChooser.showSaveDialog(stage);

            if (arquivoEscolhido != null) {
                try {
                    empregados.gravarLista(this.empregados.getLista(), arquivoEscolhido.getAbsolutePath());
                    limparCampos();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    private void btnAdicionarAction(ActionEvent event) throws IOException, SQLException {
        if (dataAdmissaoField.getValue() == null || nomeField.getText().isEmpty() || salarioField.getText().isEmpty()) {
            mostrarAlerta("Por favor, preencha os campos corretamente.", AlertType.ERROR);
        } else {
            LocalDate localDate = dataAdmissaoField.getValue();
            Date data = Date.from(localDate.atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant());
            String nome = nomeField.getText();
            Double salario = Double.parseDouble(salarioField.getText());
            Double bonus = bonusField.getText().isEmpty() ? 0 : Double.parseDouble(bonusField.getText());
            Double bonusCoop = bonusCoopField.getText().isEmpty() ? 0 : Double.parseDouble(bonusCoopField.getText());
            String areaProg = areaProgField.getText();
            int turno = turnoField.getSelectionModel().getSelectedIndex();
            String turnoString = turnoField.getSelectionModel().getSelectedItem().toString();
            int indiceSelecionado = tipoColaboradorField.getSelectionModel().getSelectedIndex();
            int id = empregados.getLista() == null ? 1 : empregados.getLista().size() + 1;
            String tipoColaborador = tipoColaboradorField.getSelectionModel().getSelectedItem().toString();
            if (empregados.getLista() == null) {
                empregados.setLista(new ArrayList<EmpregadoTableView>());
            }

            EmpregadoTableView empregado = new EmpregadoTableView(id, nome, tipoColaborador, salario, data, areaProg, bonus, bonusCoop, turnoString);
            this.empregados.adicionar(empregado);

            tabela.getItems().setAll(empregados.getLista());
            limparCampos();
        }
    }

    @FXML
    public void remover(ActionEvent event) throws SQLException {
        EmpregadoTableView empregadoSelecionado = tabela.getSelectionModel().getSelectedItem();

        if (empregadoSelecionado != null) {
            boolean confirmacao = mostrarMensagemConfirmacao("Deseja realmente excluir o colaborador selecionado?");
            if (confirmacao) {
                empregados.remover(empregadoSelecionado);
                tabela.getItems().remove(empregadoSelecionado);
            }
        } else {
            mostrarAlerta("Nenhum empregado selecionado para remover.", AlertType.ERROR);
        }
    }

    private void preencherTabela(ArrayList<EmpregadoTableView> empregado) {
        ObservableList<EmpregadoTableView> empregados = FXCollections.observableArrayList(empregado);
        tabela.getItems().setAll(empregados);
    }

    private void atualizarTabela() throws SQLException {
        tabela.getItems().clear(); // Limpa os itens existentes na tabela
        tabela.getItems().addAll(empregados.getLista()); // Adiciona os novos itens
    }

    private void limparCampos() {
        nomeField.setText("");
        salarioField.setText("");
        dataAdmissaoField.setValue(null);
        bonusField.setText("");
        bonusCoopField.setText("");
        turnoField.setValue(0);
        areaProgField.setText("");
    }

    @FXML
    private void voltarParaTelaAnterior(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent root = loader.load();

        MenuController menu = loader.getController();
//        menu.setPersistenciaSelecionada(persistenciaSelecionada);
//        menu.setEmpregados(empregados);
        menu.setDados(persistenciaSelecionada, empregados);

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

    private void visibleFields(int t) {
        clearFields();

        switch (t) {
            case 1:
                showCommonFields();
                bonusField.setVisible(true);
                bonusLabel.setVisible(true);
                break;
            case 2:
                bonusField.setVisible(true);
                bonusLabel.setVisible(true);
                bonusCoopField.setVisible(true);
                bonusCoopLabel.setVisible(true);
                showCommonFields();
                break;
            case 3: //secretario
                showCommonFields();
                turnoField.setVisible(true);
                turnoLabel.setVisible(true);
                break;
            case 4:
                showCommonFields();
                areaProgField.setVisible(true);
                areaLabel.setVisible(true);
                break;
            default:
                break;
        }
    }

    private void clearFields() {
        nomeField.setVisible(false);
        dataAdmissaoField.setVisible(false);
        salarioField.setVisible(false);
        areaProgField.setVisible(false);
        bonusField.setVisible(false);
        turnoField.setVisible(false);
        bonusCoopField.setVisible(false);

        nomeLabel.setVisible(false);
        dataLabel.setVisible(false);
        salarioLabel.setVisible(false);
        areaLabel.setVisible(false);
        bonusCoopLabel.setVisible(false);
        turnoLabel.setVisible(false);
        bonusLabel.setVisible(false);
    }

    private void showCommonFields() {
        nomeField.setVisible(true);
        dataAdmissaoField.setVisible(true);
        salarioField.setVisible(true);
        nomeLabel.setVisible(true);
        dataLabel.setVisible(true);
        salarioLabel.setVisible(true);
    }

    private boolean mostrarMensagemConfirmacao(String mensagem) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmação");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);

        // Adiciona botões de confirmação e cancelamento
        ButtonType botaoConfirmar = new ButtonType("Confirmar");
        ButtonType botaoCancelar = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(botaoConfirmar, botaoCancelar);

        // Mostra o diálogo e espera pela resposta do usuário
        return alert.showAndWait().filter(botao -> botao == botaoConfirmar).isPresent();
    }

    private void mostrarAlerta(String mensagem, AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle("Aviso");
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }

}
