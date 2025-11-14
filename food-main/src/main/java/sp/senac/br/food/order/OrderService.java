package sp.senac.br.food.order;

import org.springframework.stereotype.Service;
import sp.senac.br.food.observer.DeliveryListener;
import sp.senac.br.food.observer.KitchenListener;
import sp.senac.br.food.views.HomeView;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class OrderService {

    private HomeView view;
    private final OrderRepository orderRepository;

    private final List<OrderStatusObserver> observers = new CopyOnWriteArrayList<>();

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(String product) {
        var order = Order.builder()
                .id(null)
                .product(product)
                .status(OrderStatus.PENDING)
                .build();
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public void advanceOrderStatus(Order order) {

        var status  = switch (order.getStatus()) {
            case PENDING -> OrderStatus.PREPARING;    
            case PREPARING -> OrderStatus.IN_TRANSIT; 
            case IN_TRANSIT -> OrderStatus.DELIVERED; 
    
            default -> order.getStatus();
        };

        order.setStatus(status);
        orderRepository.save(order);

        notifyObservers(order);
    }

    public void addObserver(OrderStatusObserver observer) {
        if (observer == null) return;
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void removeObserver(OrderStatusObserver observer) {
        observers.remove(observer);
    }

    public void clearObservers() {
        observers.clear();
    }

    public void notifyObservers(Order order) {
        for (OrderStatusObserver observer : observers) {
            try {
                observer.update(order);
            } catch (Exception e) {
            }
        }
    }

    public void setView(HomeView view) {
        this.view = view;

        addObserver(new KitchenListener(view));
        addObserver(new DeliveryListener(view));
    }
}
