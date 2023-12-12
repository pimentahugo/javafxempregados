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
public class Programador extends Empregado{
    private String areaProg;

    public Programador(String n, double s, Date dc, String areaP) {
        super(n, s, dc);
        this.areaProg = areaP;
    }
    
    public Programador(String n, double s, String dc, String areaP) {
        super(n, s, dc);
        this.areaProg = areaP;
    }
    
    public void setArea(String a) {
        this.areaProg = a;
    }
    
    public String getAreaProg() {
        return this.areaProg;
    }
}
