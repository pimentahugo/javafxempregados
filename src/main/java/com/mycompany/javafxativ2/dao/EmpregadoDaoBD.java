/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javafxativ2.dao;

import com.mycompany.javafxativ2.interfaces.EmpregadoDaoInterface;
import com.mycompany.javafxativ2.models.EmpregadoTableView;
import com.mycompany.javafxativ2.data.Dao;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author hugop
 */
public class EmpregadoDaoBD extends Dao implements EmpregadoDaoInterface {

    private ArrayList<EmpregadoTableView> empregados;

    public EmpregadoDaoBD() {
    }

    @Override
    public ArrayList<EmpregadoTableView> getLista() {
        try {
            this.empregados = getListaBanco();
        } catch (SQLException ex) {
            Logger.getLogger(EmpregadoDaoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.empregados;
    }

    @Override
    public void setLista(ArrayList<EmpregadoTableView> empregados) {
        this.empregados = empregados;
    }

    @Override
    public void adicionar(EmpregadoTableView empregado) throws IOException {
        if (empregados == null) {
            this.empregados = new ArrayList<EmpregadoTableView>();
        }
        this.empregados.add(empregado);
        try {
            gravarBanco(empregado);
        } catch (SQLException ex) {
            Logger.getLogger(EmpregadoDaoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void remover(EmpregadoTableView empregado) {
        if (empregados != null) {
            this.empregados.remove(empregado);
            try {
                removerBanco(empregado);
            } catch (SQLException ex) {
                Logger.getLogger(EmpregadoDaoBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void gravarBanco(EmpregadoTableView empregado) throws SQLException {
        String sql = "INSERT INTO empregados (nome, tipocolaborador, salario, datacontratacao, areaprogramacao, bonus, bonuscooparticipacao, turno) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        Connection conn = this.getConexao();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, empregado.getNome());
        ps.setString(2, empregado.getTipoColaborador());
        ps.setDouble(3, empregado.getSalario());
        ps.setString(4, empregado.getDataContratacao());
        ps.setString(5, empregado.getAreaProg());
        ps.setDouble(6, empregado.getBonus());
        ps.setDouble(7, empregado.getBonusCooparticipacao());
        ps.setString(8, empregado.getTurno());

        ps.executeUpdate();
    }

    public void removerBanco(EmpregadoTableView empregado) throws SQLException {
        String sql = "DELETE FROM empregados WHERE id = ?";

        Connection conn = this.getConexao();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, empregado.getId());

        ps.executeUpdate();
    }

    public ArrayList<EmpregadoTableView> getListaBanco() throws SQLException {
        this.empregados = new ArrayList<EmpregadoTableView>();

        String sql = "Select * from empregados";
        Connection conn = this.getConexao();

        PreparedStatement ps = conn.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            EmpregadoTableView empregado = new EmpregadoTableView();
            empregado.setId(rs.getInt("Id"));
            empregado.setNome(rs.getString("nome"));
            empregado.setTipo(rs.getString("tipocolaborador"));
            empregado.setSalario(rs.getDouble("salario"));
            empregado.setData(rs.getString("datacontratacao"));
            empregado.setArea(rs.getString("areaprogramacao"));
            empregado.setBonus(rs.getDouble("bonus"));
            empregado.setBonusCoop(rs.getDouble("bonuscooparticipacao"));
            empregado.setTurno(rs.getString("turno"));
            this.empregados.add(empregado);
        }

        return this.empregados;
    }

    @Override
    public ArrayList<EmpregadoTableView> carregarDados(File arq) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void gravarLista(ArrayList<EmpregadoTableView> empregados, String caminhoArquivo) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
