
/**
 *
 * @author gabriel-da-rosa
 * 
 * Essa classe basicamente vai ser basicamente as colunas da tabela
 */
public class UsuarioBean {
    
    private int idUser;
    private String nome;
    private String email;
    private String senha;
    private boolean credencial;

    public UsuarioBean(int idUser, String nome, String email, String senha, boolean credencial) {
        this.idUser = idUser;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.credencial = credencial;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isCredencial() {
        return credencial;
    }

    public void setCredencial(boolean credencial) {
        this.credencial = credencial;
    }
    
    @Override
    public String toString() {
        return "ID: " + this.idUser + " | Nome: " + this.nome + " | Email: " + this.email + " | É Crítico: " + this.credencial;
    } 
  
}
