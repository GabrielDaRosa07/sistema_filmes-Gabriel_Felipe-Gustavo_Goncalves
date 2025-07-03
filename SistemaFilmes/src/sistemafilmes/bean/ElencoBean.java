package sistemafilmes.bean;

/**
 *
 * @author gabriel-da-rosa : gustavo-gonçalves
 */
public class ElencoBean {

    private int idFilme;
    private int idPessoa;

    public ElencoBean(int idFilme, int idPessoa) {
        this.idFilme = idFilme;
        this.idPessoa = idPessoa;
    }

    public int getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(int idFilme) {
        this.idFilme = idFilme;
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }
    
    
    
}
