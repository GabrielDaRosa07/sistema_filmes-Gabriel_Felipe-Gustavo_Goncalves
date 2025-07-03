/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemafilmes.bean;

/**
 *
 * @author gabrielrosa
 */

import java.sql.*;

public class AvaliacaoBean {
    
    private int idUser;
    private int idFilme;
    private String critica;
    private double nota;
    private Date data;

    public AvaliacaoBean(int idUser, int idFilme, String critica, double nota, Date data) {
        this.idUser = idUser;
        this.idFilme = idFilme;
        this.critica = critica;
        this.nota = nota;
        this.data = data;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(int idFilme) {
        this.idFilme = idFilme;
    }

    public String getCritica() {
        return critica;
    }

    public void setCritica(String critica) {
        this.critica = critica;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    
    @Override
    public String toString() {
        return "Avaliação [ID Usuário=" + idUser + ", ID Filme=" + idFilme + ", Nota=" + nota + "]";
    }
    
}
