public class Titular implements Repositorio {

    private String nome;
    private String email;
    private String telefone;

    public Titular(String nome, String email) {
        this.nome = nome;
        this.email = email;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public void cadastrar() {
        System.out.println("Titular cadastrado com sucesso!");
    }

    @Override
    public void atualizar() {
        System.out.println("Titular atualizado com sucesso!");
    }

    @Override
    public void deletar() {
        System.out.println("Titular deletado com sucesso!");
    }

    @Override
    public void listar() {
        System.out.println("Titulares listados com sucesso!");
    }

}
