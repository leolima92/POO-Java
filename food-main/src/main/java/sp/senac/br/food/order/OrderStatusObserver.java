package sp.senac.br.food.order;

public interface OrderStatusObserver {
    void update(Order order);
}