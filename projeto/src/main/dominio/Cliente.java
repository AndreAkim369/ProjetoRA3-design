package dominio;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;
import javax.persistence.GenerationType;
import java.util.ArrayList;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private int cpf;

    @Column(nullable = false)
    private int telefone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    // Construtores
    public Cliente() {
    }

    public Cliente(String nome, int cpf, int telefone, Endereco endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    // Getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public static ArrayList<Cliente> listarClientes(){
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("ex");
        EntityManager em = emf.createEntityManager();

        ArrayList<Cliente> clientes = (ArrayList<Cliente>) em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();

        em.close();
        emf.close();

        return clientes;
    }

    public static Cliente find(Integer cpf){
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("ex");
        EntityManager em = emf.createEntityManager();

        Cliente cliente = em.find(Cliente.class, cpf);

        em.close();
        emf.close();

        return cliente;
    }

    public boolean update(String nome, int telefone){
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("ex");
        EntityManager em = emf.createEntityManager();
        String oldNome = this.nome;
        String oldtelefone = this.telefone;
        boolean commit = false;
        try{

            this.nome = nome;
            this.telefone = telefone;
            em.getTransaction().begin();
            em.merge(this);
            em.getTransaction().commit();
            commit = true;

        }catch(Exception e){

            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }

            this.nome = oldNome;
            this.telefone = oldtelefone;

        }finally{

            em.close();
            emf.close();

        }

        return commit;
    }

    public boolean remove(){
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("ex");
        EntityManager em = emf.createEntityManager();
        boolean commit = false;

        ArrayList<Pedido> pedidos_cliente = Pedido.visualizarPedido(this.cpf);

        try{

            for(Pedido pedido : pedidos_cliente){
                pedido.getNotaFiscal().remove();
                pedido.remove();
            }

            Cliente cliente = em.find(Cliente.class, this.id);
            em.getTransaction().begin();
            em.remove(cliente);
            em.remove(cliente.getEndereco());
            em.getTransaction().commit();
            commit = true;
        }catch(Exception e){

            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
        }finally{

            em.close();
            emf.close();

        }

        return commit;
    }

}
