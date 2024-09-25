package jpa.jpashop.repository;




import jakarta.persistence.EntityManager;
import jpa.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Getter
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;


    public void save (Item item) {
        if(item.getId() == null){
            em.persist(item);
        } else{
            em.merge(item);
        }
    }
    public Item findOne(Long id){
        return em.find(Item.class, id);
    }

    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }

}
