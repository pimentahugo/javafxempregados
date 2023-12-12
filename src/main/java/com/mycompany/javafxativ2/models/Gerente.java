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
public class Gerente extends Empregado{
    private double bonus;

    public Gerente(String n, double s, Date dc) {
        super(n, s, dc);
        this.bonus = 0;
    }
    
    public Gerente(String n, double s, String dc) {
        super(n, s, dc);
        this.bonus = 0;
    }
    
    public void setBonus(double b) {
        this.bonus = b;
    }
    
    public double getBonus() {
        return this.bonus;
    }
    
    @Override
    public double getSalario() {
        return (this.salario + (this.salario * (this.getBonus()/100)));
    }
}
