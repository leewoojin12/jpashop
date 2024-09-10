package jpa.jpashop.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {


    @Id @GeneratedValue
    @Column(name = "orders_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name ="member_id")

    private Member member;

    private List<orderItems> orderitem = new ArrayList<>();

 


}
