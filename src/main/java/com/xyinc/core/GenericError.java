package com.xyinc.core;

import lombok.Data;

/**
 * Classe de Erro Generico
 *
 */
@Data
public class GenericError {
    /**
     * Atributo errorMessage
     */
    private String errorMessage;

    /**
     * Construtor
     * @param errorMessage
     */
    public GenericError(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}