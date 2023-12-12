/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javafxativ2.models;

/**
 *
 * @author hugop
 */
import javafx.scene.control.TextFormatter;
import java.util.function.UnaryOperator;

public class BaseTextFormatter {
    private String regexPattern;

    public BaseTextFormatter(String regexPattern) {
        this.regexPattern = regexPattern;
    }

    public TextFormatter<String> getTextFormatter() {
        return new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (isValid(newText)) {
                return change;
            } else {
                return null; // Rejeitar a alteração
            }
        });
    }

    private boolean isValid(String text) {
        return text.matches(regexPattern);
    }
}

