
package sistemafilmes.bean;

/**
 *
 * @author gabriel-da-rosa : gustavo-gonçalves
 */
public class FilmeNotaBean {
    
    private String tituloFilme;
    private double nota;

    public FilmeNotaBean(String tituloFilme, double nota) {
        this.tituloFilme = tituloFilme;
        this.nota = nota;
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
    
    @Override
    public String toString() {
        //  String.format vai formata a nota pra ser tipo 4.0 em vez de 4
        return String.format("Título: %s | Nota: %.1f", tituloFilme, nota);
    } 
    
}
