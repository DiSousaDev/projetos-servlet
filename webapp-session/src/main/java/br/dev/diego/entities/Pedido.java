package br.dev.diego.entities;

import br.dev.diego.config.PedidoCompra;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@PedidoCompra
public class Pedido implements Serializable {

    @Inject
    private transient Logger log;
    private List<ItemPedido> itemPedidos;

    @PostConstruct
    public void iniciar() {
        itemPedidos = new ArrayList<>();
        log.info("Iniciando pedidos...");
    }

    @PreDestroy
    public void encerrar() {
        log.info("Encerrando pedido...");
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

