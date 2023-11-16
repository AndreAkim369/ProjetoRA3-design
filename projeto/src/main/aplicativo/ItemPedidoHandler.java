package main.aplicativo;

import java.util.ArrayList;
import java.util.List;


public class ItemPedidoHandler {
     public ItemPedidoHandler(String descricao, int quantidade, float valorItem) {
    }

    class Pedido {
    private int numPedido;
    private Cliente cliente;
    private Endereco enderecoEntrega;
    private List<ItemPedidoHandler> itens;

    public Pedido(int numPedido, Cliente cliente, Endereco enderecoEntrega) {
        this.numPedido = numPedido;
        this.cliente = cliente;
        this.enderecoEntrega = enderecoEntrega;
        this.itens = new ArrayList<>();
    }

    public int getNumPedido() {
        return numPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Endereco getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public List<ItemPedidoHandler> getItens() {
        return itens;
    }

    public void adicionarItem(ItemPedidoHandler item) {
        itens.add(item);
    }

    public void removerItem(ItemPedidoHandler item) {
        itens.remove(item);
    }
}

    public char[] getDescricao() {
        return null;
    }

    public String getQuantidade() {
        return null;
    }

    public String getValorItem() {
        return null;
    }
}
