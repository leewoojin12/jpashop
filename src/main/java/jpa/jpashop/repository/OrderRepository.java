package jpa.jpashop.repository;


import jakarta.persistence.EntityManager;
import jpa.jpashop.domain.Order;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Getter
@RequiredArgsConstructor

public class OrderRepository {

    private final EntityManager em;



    public void save(Order order){
        em.persist(order);
    }


    public Order findOne(Long id){
        return em.find(Order.class, id);
    }

    public List<Order> findAll() {
        return null;
    }
}
