package jpa.jpashop.domain.item;


import jakarta.persistence.*;
import jpa.jpashop.domain.Category;
import jpa.jpashop.exception.NotEnoughstockException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name ="item_id")
    private Long id;


    private String name;

    private int price;

    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categoryList = new ArrayList<Category>();

    /**
     *stock 증가
     */
    //==비즈니스 로직 == //
    public void addstock(int quantity){

        this.stockQuantity+=quantity;
    }




    /**
     *stock 감소
     */
    public void removesotck(int quantity){
        int restStock = this.stockQuantity - quantity;
        if( restStock <0 ){
            throw new NotEnoughstockException("need more stock");
        }
        this.stockQuantity = restStock;
    }
}
