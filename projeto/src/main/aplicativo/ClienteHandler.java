package main.aplicativo;

class Cliente {
    private String nome;
    private int cpf;
    private int telefone;

    public Cliente(String nome2, int cpf2, int telefone2, Endereco endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return null;
    }

    public void SetEndereco(int endereco) {
    }
}
