/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javafxativ2.models;

import java.util.Date;

/**
 *
 * @author hugop
 */
public class Executivo extends Gerente {
    private double bonusCooparticipacao;
    
    public Executivo(String n, double s, Date dc) {
        super(n,s,dc);
        this.bonusCooparticipacao = 0;
    }
    
     public Executivo(String n, double s, Date dc, double bonus) {
        super(n,s,dc);
        this.bonusCooparticipacao = bonus;
    }
    
    
    public void setBonusCooparticipacao(double b) {
        this.bonusCooparticipacao = b;
    }
    
    public double getBonusCoop() {
        return this.bonusCooparticipacao;
    }
    
    @Override
    public double getSalario() {
        return (this.salario + (this.salario * (this.getBonus()/100)) + (this.salario * (this.getBonusCoop()/100)));
    }
}
