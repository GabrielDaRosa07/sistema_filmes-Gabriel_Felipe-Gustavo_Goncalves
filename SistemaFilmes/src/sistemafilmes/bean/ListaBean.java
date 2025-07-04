
package sistemafilmes.bean;

/**
 *
 * @author gabriel-da-rosa : gustavo-gonçalves
 */
public class ListaBean {

    private int idLista;
    private int idUser;
    private String nome;
    private boolean privada;

    public ListaBean(int idLista, int idUser, String nome, boolean privada) {
        this.idLista = idLista;
        this.idUser = idUser;
        this.nome = nome;
        this.privada = privada;
    }

    public int getIdLista() {
        return idLista;
    }

    public void setIdLista(int idLista) {
        this.idLista = idLista;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isPrivada() {
        return privada;
    }

    public void setPrivada(boolean privada) {
        this.privada = privada;
    }
    
    @Override
    public String toString() {
        String status = privada ? "Privada" : "Pública";
        return "ID: " + idLista + " | Nome: " + nome + " | Status: " + status;
    }
    
}
