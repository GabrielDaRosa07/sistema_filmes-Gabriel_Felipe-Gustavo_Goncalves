
package sistemafilmes.bean;

/**
 *
 * @author gabriel-da-rosa
 */
public class ListaFilmeBean {
    
    private int idLista;
    private int idFilme;
    private int posicao;

    public ListaFilmeBean(int idLista, int idFilme, int posicao) {
        this.idLista = idLista;
        this.idFilme = idFilme;
        this.posicao = posicao;
    }

    public int getIdLista() {
        return idLista;
    }

    public void setIdLista(int idLista) {
        this.idLista = idLista;
    }

    public int getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(int idFilme) {
        this.idFilme = idFilme;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }
    
    
    
}
