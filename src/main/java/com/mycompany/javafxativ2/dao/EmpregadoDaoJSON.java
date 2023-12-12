/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javafxativ2.dao;

import com.mycompany.javafxativ2.interfaces.EmpregadoDaoInterface;
import com.mycompany.javafxativ2.models.EmpregadoTableView;
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
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author hugop
 */
public class EmpregadoDaoJSON implements EmpregadoDaoInterface{

    private ArrayList<EmpregadoTableView> empregados;

    public EmpregadoDaoJSON() {
    }
    
    @Override
    public ArrayList<EmpregadoTableView> getLista() {
        if (this.empregados == null) {
            this.empregados = new ArrayList<>();
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
    }
    
    @Override
    public void remover(EmpregadoTableView empregado) {
        if (empregados != null) {
            this.empregados.remove(empregado);
        }
    }

    @Override
    public void gravarLista(ArrayList<EmpregadoTableView> empregados, String caminhoArquivo) throws IOException {
        JSONArray jsonArray = new JSONArray();
        for (EmpregadoTableView empregado : empregados) {
            // Conversão para JSON
            JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", empregado.getId());
                jsonObject.put("nome", empregado.getNome());
                jsonObject.put("tipoColaborador", empregado.getTipoColaborador());
                jsonObject.put("salario", empregado.getSalario());
                jsonObject.put("dataContratacao", empregado.getDataContratacao());
                jsonObject.put("areaProg", empregado.getAreaProg());
                jsonObject.put("bonusCooparticipacao", empregado.getBonusCooparticipacao());
                jsonObject.put("bonus", empregado.getBonus());
                jsonObject.put("turno", empregado.getTurno());
            jsonArray.put(jsonObject);
        }

        // Gravar JSON no arquivo
        try (Writer writer = new FileWriter(caminhoArquivo, StandardCharsets.UTF_8)) {
            writer.write(jsonArray.toString());
        }
    }
    
    @Override
    public ArrayList<EmpregadoTableView> carregarDados(File arquivo) throws IOException {
        String conteudoArquivo = new String(Files.readAllBytes(Paths.get(arquivo.getAbsolutePath())));
        JSONArray jsonArray = new JSONArray(conteudoArquivo);

        ArrayList<EmpregadoTableView> empregados = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            EmpregadoTableView empregado = instanciarJson(jsonObject);
            if (empregado != null) {
                empregados.add(empregado);
            }
        }
        System.out.println("Número de empregados recuperados: " + empregados.size());
        return empregados;
    }

    private EmpregadoTableView instanciarJson(JSONObject jsonObject) {
        EmpregadoTableView empregado = new EmpregadoTableView(
                jsonObject.getInt("id"),
                jsonObject.getString("nome"),
                jsonObject.getString("tipoColaborador"),
                jsonObject.getDouble("salario"),
                parseDate(jsonObject.getString("dataContratacao")),
                jsonObject.getString("areaProg"),
                jsonObject.getDouble("bonus"),
                jsonObject.getDouble("bonusCooparticipacao"),
                jsonObject.getString("turno")
        );

        return empregado;
    }

    private Date parseDate(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.forLanguageTag("pt-BR"));
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
