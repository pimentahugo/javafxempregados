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
public class EmpregadoDaoXML implements EmpregadoDaoInterface {
    private ArrayList<EmpregadoTableView> empregados;

    public EmpregadoDaoXML() {
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
        XStream xs = new XStream(new DomDriver());
        xs.addPermission(NoTypePermission.NONE);
        xs.addPermission(new PrimitiveTypePermission());
        xs.allowTypesByWildcard(new String[]{"com.mycompany.javafxativ2.**", "java.util.**"});

        String xml = xs.toXML(empregados);

        // Gravar XML no arquivo
        try (Writer writer = new FileWriter(caminhoArquivo, StandardCharsets.UTF_8)) {
            writer.write(xml);
        }
    }
    
    @Override
    public ArrayList<EmpregadoTableView> carregarDados(File arquivo) throws IOException {
        XStream xs = new XStream();
        xs.addPermission(NoTypePermission.NONE);
        xs.addPermission(new PrimitiveTypePermission());
        xs.allowTypesByWildcard(new String[]{"com.mycompany.javafxativ2.**", "java.util.**"});

        try (FileInputStream fis = new FileInputStream(arquivo); InputStreamReader reader = new InputStreamReader(fis, StandardCharsets.UTF_8)) {
            this.empregados = (ArrayList<EmpregadoTableView>) xs.fromXML(reader);
            System.out.println("Número de empregados recuperados: " + empregados.size());
            return empregados;
        }
    }

}