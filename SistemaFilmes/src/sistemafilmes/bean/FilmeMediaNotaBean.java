
package sistemafilmes.bean;

/**
 *
 * @author gabriel-da-rosa : gustavo-gonçalves
 */
public class FilmeMediaNotaBean {
    
    private String tituloFilme;
    private double mediaNota;

    public FilmeMediaNotaBean(String tituloFilme, double mediaNota) {
        this.tituloFilme = tituloFilme;
        this.mediaNota = mediaNota;
    }

    public String getTituloFilme() {
        return tituloFilme;
    }

    public void setTituloFilme(String tituloFilme) {
        this.tituloFilme = tituloFilme;
    }

    public double getMediaNota() {
        return mediaNota;
    }

    public void setMediaNota(double mediaNota) {
        this.mediaNota = mediaNota;
    }
    
    @Override
    public String toString() {
        return String.format("\nTítulo: %s | Nota Média: %.1f", tituloFilme, mediaNota);
    }
    
}
