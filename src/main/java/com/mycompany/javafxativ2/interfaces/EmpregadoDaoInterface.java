/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.javafxativ2.interfaces;

import com.mycompany.javafxativ2.models.EmpregadoTableView;
import com.thoughtworks.xstream.security.NoTypePermission;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author hugop
 */
public interface EmpregadoDaoInterface {
    ArrayList<EmpregadoTableView> getLista();
    void setLista(ArrayList<EmpregadoTableView> empregados);
    void adicionar(EmpregadoTableView empregado) throws IOException;
    void remover(EmpregadoTableView empregado);
    ArrayList<EmpregadoTableView> carregarDados(File arq) throws IOException;
    void gravarLista(ArrayList<EmpregadoTableView> empregados, String caminhoArquivo) throws IOException;
}

class CustomSecurityPermission extends NoTypePermission {

    public CustomSecurityPermission() {
        super();
    }
}
