package mk.ukim.finki.db.distributorapp.service;

import mk.ukim.finki.db.distributorapp.model.Article;
import mk.ukim.finki.db.distributorapp.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> findAll();
    Optional<Order> findById(Long id);

    Optional<Order> save(Long customerId, List<Article> articles);

    void deleteById(Long id);
    boolean exists(Long id);
}
