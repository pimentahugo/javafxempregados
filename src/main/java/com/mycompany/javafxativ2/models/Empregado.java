/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javafxativ2.models;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author hugop
 */

public class Empregado {
//    @XStreamAlias("nome")

    private String nome;
    double salario;
    private Date dataContratacao;

    public Empregado(String n, double s, Date dc) {
        this.nome = n;
        this.salario = s;
        this.dataContratacao = dc;
    }

    public Empregado(String n, double s, String dc) {
        this.nome = n;
        this.salario = s;
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.dataContratacao = formato.parse(dc);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getNome() {
        return nome;
    }

    public double getSalario() {
        return salario;
    }

    public Date getDataContratacao() {
        return dataContratacao;
    }

    public void aumentoSalarial(double percentual) {
        this.salario += (this.salario * (percentual / 100));
    }
}
