package main.aplicativo;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Cliente cliente = null;  // Cliente a ser utilizado nas operações
        PedidoHandler pedido = null;    // Pedido associado ao cliente

        int opcao;
        do {
            System.out.println("===== Menu =====");
            System.out.println("1. Cliente");
            System.out.println("2. Adicionar Item ao Pedido");
            System.out.println("3. Remover Item do Pedido");
            System.out.println("4. Visualizar Pedido");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    menuCliente(cliente, pedido);
                    break;
                case 2:
                    if (cliente != null) {
                        adicionarItemAoPedido(pedido);
                    } else {
                        System.out.println("Por favor, cadastre um cliente primeiro.");
                    }
                    break;
                case 3:
                    if (cliente != null) {
                        removerItemDoPedido(pedido);
                    } else {
                        System.out.println("Por favor, cadastre um cliente primeiro.");
                    }
                    break;
                case 4:
                    if (cliente != null) {
                        visualizarPedido(pedido);
                    } else {
                        System.out.println("Por favor, cadastre um cliente primeiro.");
                    }
                    break;
                case 5:
                    System.out.println("Saindo do programa. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 5);
    }

    private static void menuCliente(Cliente cliente, PedidoHandler pedido) {
        int opcao;
        do {
            System.out.println("===== Menu Cliente =====");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Editar Cliente");
            System.out.println("3. Excluir Cliente");
            System.err.println("4. Editar Endereço");
            System.out.println("5. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    cliente = cadastrarCliente();
                    break;
                case 2:
                    if (cliente != null) {
                        editarCliente(cliente);
                    } else {
                        System.out.println("Por favor, cadastre um cliente primeiro.");
                    }
                    break;
                case 3:
                    if (cliente != null) {
                        cliente = null;
                        System.out.println("Cliente excluído com sucesso.");
                    } else {
                        System.out.println("Nenhum cliente cadastrado para excluir.");
                    }
                    break;
                case 4:
                    if (cliente != null) {
                        editarEndereco(cliente.getEndereco());
                    } else {
                        System.out.println("Por favor, cadastre um cliente primeiro.");
                    }
                    break;
                case 5:
                    System.out.println("Voltando ao Menu Principal.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 5);
    }

    private static Cliente cadastrarCliente() {
        System.out.print("Digite o nome do cliente: ");
        String nome = scanner.next();

        System.out.print("Digite o CPF do cliente: ");
        int cpf = scanner.nextInt();

        System.out.print("Digite o telefone do cliente: ");
        int telefone = scanner.nextInt();

        System.out.print("Digite o CEP do endereço: ");
        int cep = scanner.nextInt();

        System.out.print("Digite o número da casa: ");
        int numCasa = scanner.nextInt();

        Endereco endereco = new Endereco(cep, numCasa);

        Cliente novoCliente = new Cliente(nome, cpf, telefone, endereco);
        System.out.println("Cliente cadastrado com sucesso!");
        return novoCliente;
    }

    private static void editarCliente(Cliente cliente) {
        System.out.println("Editando informações do cliente:");

        System.out.print("Novo nome (atual: " + cliente.getNome() + "): ");
        String novoNome = scanner.next();

        System.out.print("Novo CPF (atual: " + cliente.getCpf() + "): ");
        int novoCpf = scanner.nextInt();

        System.out.print("Novo telefone (atual: " + cliente.getTelefone() + "): ");
        int novoTelefone = scanner.nextInt();

        cliente.setNome(novoNome);
        cliente.setCpf(novoCpf);
        cliente.setTelefone(novoTelefone);

        System.out.println("Informações do cliente atualizadas com sucesso!");
    }

    private static void editarEndereco(Endereco endereco) {
        System.out.println("Editando informações do endereço:");

        System.out.print("Novo CEP (atual: " + endereco.getCep() + "): ");
        int novoCep = scanner.nextInt();

        System.out.print("Novo número da casa (atual: " + endereco.getNumCasa() + "): ");
        int novoNumCasa = scanner.nextInt();

        endereco.setCep(novoCep);
        endereco.setNumCasa(novoNumCasa);

        System.out.println("Informações do endereço atualizadas com sucesso!");
    }

    private static void adicionarItemAoPedido(PedidoHandler pedido) {
        System.out.print("Digite a descrição do item: ");
        String descricao = scanner.next();

        System.out.print("Digite a quantidade: ");
        int quantidade = scanner.nextInt();

        System.out.print("Digite o valor do item: ");
        float valorItem = scanner.nextFloat();

        ItemPedidoHandler novoItem = new ItemPedidoHandler(descricao, quantidade, valorItem);
        pedido.adicionarItem(novoItem);
        System.out.println("Item adicionado ao pedido com sucesso!");
    }

    private static void removerItemDoPedido(PedidoHandler pedido) {
        if (pedido.getItens().isEmpty()) {
            System.out.println("O pedido não possui itens para remover.");
            return;
        }

        System.out.println("Itens no pedido:");
        for (ItemPedidoHandler item : pedido.getItens()) {
            System.out.println(item.getDescricao());
        }

        System.out.print("Digite a descrição do item a ser removido: ");
        String descricao = scanner.next();

        ItemPedidoHandler itemRemover = null;
        for (ItemPedidoHandler item : pedido.getItens()) {
            if (item.getDescricao().equals(descricao)) {
                itemRemover = item;
                break;
            }
        }

        if (itemRemover != null) {
            pedido.removerItem(itemRemover);
            System.out.println("Item removido do pedido com sucesso!");
        } else {
            System.out.println("Item não encontrado no pedido.");
        }
    }

    private static void visualizarPedido(PedidoHandler pedido) {
        System.out.println("===== Pedido =====");
        System.out.println("Número do Pedido: " + pedido.getNumPedido());
        System.out.println("Cliente: " + pedido.getCliente().getNome());
        System.out.println("Endereço de Entrega: CEP " + pedido.getEnderecoEntrega().getCep() +
                ", Número " + pedido.getEnderecoEntrega().getNumCasa());

        if (pedido.getItens().isEmpty()) {
            System.out.println("O pedido não possui itens.");
        } else {
            System.out.println("Itens no pedido:");
            for (ItemPedidoHandler item : pedido.getItens()) {
                System.out.println(" - Descrição: " + item.getDescricao() +
                        ", Quantidade: " + item.getQuantidade() +
                        ", Valor Unitário: " + item.getValorItem());
            }
        }
    }
}

