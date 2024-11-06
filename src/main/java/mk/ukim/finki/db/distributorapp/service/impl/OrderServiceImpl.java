package mk.ukim.finki.db.distributorapp.service.impl;

import mk.ukim.finki.db.distributorapp.model.Article;
import mk.ukim.finki.db.distributorapp.model.Customer;
import mk.ukim.finki.db.distributorapp.model.Order;
import mk.ukim.finki.db.distributorapp.model.Proforma;
import mk.ukim.finki.db.distributorapp.model.enumerations.OrderStatus;
import mk.ukim.finki.db.distributorapp.repository.CustomerRepository;
import mk.ukim.finki.db.distributorapp.repository.OrdersRepository;
import mk.ukim.finki.db.distributorapp.repository.UserRepository;
import mk.ukim.finki.db.distributorapp.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrdersRepository ordersRepository;
    private final CustomerRepository customerRepository;

    public OrderServiceImpl(OrdersRepository ordersRepository, CustomerRepository customerRepository) {
        this.ordersRepository = ordersRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Order> findAll() {
        return ordersRepository.findAll();
    }

    @Override
    public Optional<Order> findById(Long id) {
        return this.ordersRepository.findById(id);
    }

    @Override
    public Optional<Order> save(Long customerId, List<Article> articles) {
        Order order = new Order(customerId,articles);
        double amount=0;
        for(Article article:articles){
            amount+=article.getPrice();
        }
        order.setAmount(amount);
        Customer customer = this.customerRepository.findById(customerId).get();
        order.setCustomer(customer);
        order.setOrderStatus(OrderStatus.CREATED);

        Proforma proforma = new Proforma();
        proforma.setTotalAmount(amount);

        order.setProforma(proforma);
        return Optional.of(this.ordersRepository.save(order));
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public boolean exists(Long id) {
        return false;
    }
}
