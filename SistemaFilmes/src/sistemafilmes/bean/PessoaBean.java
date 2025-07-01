package sistemafilmes.bean;

/**
 *
 * @author gabrielrosa
 */
public class PessoaBean {

    private int idPessoa;
    private String nome;
    private String papel;

    public PessoaBean(int idPessoa, String nome, String papel) {
        this.idPessoa = idPessoa;
        this.nome = nome;
        this.papel = papel;
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPapel() {
        return papel;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }
    
    @Override
    public String toString() {
        return "ID: " + idPessoa + " | Nome: " + nome + " | Papel: " + papel;
    }
    
}
