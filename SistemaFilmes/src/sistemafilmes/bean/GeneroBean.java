package sistemafilmes.bean;

/**
 *
 * @author gabriel-da-rosa : gustavo-gonçalves
 */
public class GeneroBean {

    private int idGenero;
    private String nome;

    public GeneroBean(int idGenero, String nome) {
        this.idGenero = idGenero;
        this.nome = nome;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    @Override
    public String toString() {
        return "ID: " + idGenero + " | Gênero: " + nome;
    }
    
}
