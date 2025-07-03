package sistemafilmes.bean;
/**
 *
 * @author gabriel-da-rosa : gustavo-gonçalves
 */

import java.util.*;


public class FilmeBean {
    
    private int idFilme;
    private String titulo;
    private int ano;
    private int duracao;
    private String sinopse;
    private String poster;
    private List<String> generos;

    public FilmeBean(int idFilme, String titulo, int ano, int duracao, String sinopse, String poster) {
        this.idFilme = idFilme;
        this.titulo = titulo;
        this.ano = ano;
        this.duracao = duracao;
        this.sinopse = sinopse;
        this.poster = poster;
        this.generos = new ArrayList<>();
    }

    public int getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(int idFilme) {
        this.idFilme = idFilme;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
    
    public List<String> getGeneros() {
        return generos;
    }

    public void setGeneros(List<String> generos) {
        this.generos = generos;
    }
    
    @Override
    public String toString() {
        String generosStr = generos.isEmpty() ? "Nenhum" : String.join(", ", generos);
        return "ID: " + idFilme + " | Título: " + titulo + " (" + ano + ") | Gêneros: " + generosStr;
    }
}

