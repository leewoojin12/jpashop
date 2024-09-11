package jpa.jpashop.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class  Order {


    @Id @GeneratedValue
    @Column(name = "orders_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name ="member_id")
    private Member member;


    @OneToMany(mappedBy = "order" ,cascade = CascadeType.ALL)
    private List<OrderItem> orderitems = new ArrayList<>();

    @OneToOne(fetch = LAZY ,cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;


    private LocalDateTime orderDate;


    @Enumerated(EnumType.STRING)
    private OrderStatus status; //주문 상태 ORDER , Cancel


    //== 연관관계 메서드 ==//

    public void setMember(Member member) {
        this.member =member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem){
        orderitems.add(orderItem);
        orderItem.setOrder(this);


    }

    public void setDelivery(Delivery delivery){

        this.delivery = delivery;
        delivery.setOrder(this);
    }





}
