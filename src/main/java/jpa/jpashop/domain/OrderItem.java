package jpa.jpashop.domain;

import jakarta.persistence.*;
import jpa.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.FetchType.*;


@Entity
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name ="order_item_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name ="item_id")
    private Item item;

    @ManyToOne(fetch = LAZY)
    @JoinColumn( name ="order_id")
    private Order order;


    private int orderPrice;

    private int count;


    //==생성 로직==//

    public static OrderItem createOrderItem (Item item , int orderPrice , int count){
        OrderItem orderItem = new OrderItem();


        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removesotck(count);

        return orderItem;

    }

    //== 비즈니스 로직 ==//
    public void cancel() {

        getItem().addstock(count);
    }

    public int getTotalPrice(){
        return getOrderPrice() * getCount() ;
    }




}
