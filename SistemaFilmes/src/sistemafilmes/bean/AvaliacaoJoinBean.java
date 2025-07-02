
package sistemafilmes.bean;

/**
 *
 * @author gabrielrosa
 */
public class AvaliacaoJoinBean {

    private String nomeUsuario;
    private String tituloFilme;
    private double nota;
    private String critica;

    public AvaliacaoJoinBean(String nomeUsuario, String tituloFilme, double nota, String critica) {
        this.nomeUsuario = nomeUsuario;
        this.tituloFilme = tituloFilme;
        this.nota = nota;
        this.critica = critica;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getTituloFilme() {
        return tituloFilme;
    }

    public void setTituloFilme(String tituloFilme) {
        this.tituloFilme = tituloFilme;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public String getCritica() {
        return critica;
    }

    public void setCritica(String critica) {
        this.critica = critica;
    }
    
    @Override
    public String toString() {
        return "\n----- ----- -----\nUsuário: " + nomeUsuario + " | Filme: " + tituloFilme + " | Nota: " + nota + "\n\nCrítica: " + critica + "\n----- ----- -----\n";
    }
    
}
