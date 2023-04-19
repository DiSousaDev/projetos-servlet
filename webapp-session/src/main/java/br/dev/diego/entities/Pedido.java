package br.dev.diego.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Pedido {

    private List<ItemPedido> itemPedidos = new ArrayList<>();

    public Pedido() {
    }

    public List<ItemPedido> getItemPedidos() {
        return itemPedidos;
    }

    public void adicionarItem(ItemPedido item) {
        if (!itemPedidos.contains(item)) {
            itemPedidos.add(item);
            return;
        }
        adicionaQuantidade(item);
    }

    public void removerItem(ItemPedido item) {
        removeQuantidade(item);
    }

    public int getTotalPedido() {
        return itemPedidos.stream().mapToInt(ItemPedido::getValorTotal).sum();
    }

    private void adicionaQuantidade(ItemPedido item) {
        Optional<ItemPedido> itemPedidoOptional = itemPedidos
                .stream()
                .filter(itemPedido -> itemPedido.equals(item))
                .findAny();
        if (itemPedidoOptional.isPresent()) {
            ItemPedido i = itemPedidoOptional.get();
            i.setQuantidade(i.getQuantidade() + 1);
        }
    }

    private void removeQuantidade(ItemPedido item) {
        Optional<ItemPedido> itemPedidoOptional = itemPedidos
                .stream()
                .filter(itemPedido -> itemPedido.equals(item))
                .findAny();
        if (itemPedidoOptional.isPresent() && itemPedidoOptional.get().getQuantidade() > 1) {
            ItemPedido i = itemPedidoOptional.get();
            i.setQuantidade(i.getQuantidade() - 1);
        }

        if (itemPedidoOptional.isPresent() && itemPedidoOptional.get().getQuantidade() == 1) {
            ItemPedido i = itemPedidoOptional.get();
            itemPedidos.remove(i);
        }
    }
}

