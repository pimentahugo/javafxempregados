/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javafxativ2.models;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author hugop
 */
@XStreamAlias("EmpregadoTableView")
public class EmpregadoTableView {

    private int id;
    private String nome;
    private String tipoColaborador;
    private double salario;
    private Date dataContratacao;
    private String areaProg;
    private double bonusCooparticipacao;
    private double bonus;
    private String turno;

    public EmpregadoTableView() {

    }
    
    public EmpregadoTableView(int id, String nome, String tipoColaborador, double salario, Date dataContratacao, String areaProg, double bonus, double bonusCoop, String turno) {
        this.id = id;
        this.nome = nome;
        this.tipoColaborador = tipoColaborador;
        this.salario = salario;
        this.dataContratacao = dataContratacao;
        this.areaProg = areaProg;
        this.bonus = bonus;
        this.bonus = bonusCoop;
        this.turno = turno;
    }
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTipoColaborador() {
        return tipoColaborador;
    }

    public double getSalario() {
        return salario;
    }

    public String getDataContratacao() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        return dateFormat.format(dataContratacao);
    }

    public String getAreaProg() {
        return areaProg;
    }

    public double getBonusCooparticipacao() {
        return bonusCooparticipacao;
    }

    public double getBonus() {
        return bonus;
    }

    public String getTurno() {
        return turno;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTipo(String tipo) {
        this.tipoColaborador = tipo;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public void setData(String data) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
        try {
            // Parse a String para um objeto Date
            this.dataContratacao = sdf.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
            // Ou lance uma exceção, dependendo do seu caso
        }
    }

    public void setArea(String area) {
        this.areaProg = area;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public void setBonusCoop(double bonusCoop) {
        this.bonusCooparticipacao = bonusCoop;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }
}
