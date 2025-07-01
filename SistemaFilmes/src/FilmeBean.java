/**
 *
 * @author gabrielrosa
 */
public class FilmeBean {
    
    private int idFilme;
    private String titulo;
    private int ano;
    private int duracao;
    private String sinopse;
    private String poster;

    public FilmeBean(int idFilme, String titulo, int ano, int duracao, String sinopse, String poster) {
        this.idFilme = idFilme;
        this.titulo = titulo;
        this.ano = ano;
        this.duracao = duracao;
        this.sinopse = sinopse;
        this.poster = poster;
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

    @Override
    public String toString() {
        return "ID: " + idFilme + " | Título: " + titulo + " (" + ano + ") | Duração: " + duracao + " min";
    }
}

