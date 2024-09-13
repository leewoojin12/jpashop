package jpa.jpashop.service;


import jpa.jpashop.domain.Delivery;
import jpa.jpashop.domain.Member;
import jpa.jpashop.domain.Order;
import jpa.jpashop.domain.OrderItem;
import jpa.jpashop.domain.item.Item;
import jpa.jpashop.repository.ItemRepository;
import jpa.jpashop.repository.MemberRepository;
import jpa.jpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor

public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;




    /**
     * 주문
     */
    //주문

    public Long order(Long memberId , Long itemId , int count){
        //엔디티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        //배송 정보 조회

        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());


        //주문 상품 생성

        OrderItem orderItem =OrderItem.createOrderItem(item, item.getPrice(), count);


        //주문 생성
        Order order = Order.createOrder(member ,delivery , orderItem);

        //주문 저장
        orderRepository.save(order);
        return order.getId();


    }



    //취소

    public void cancelOrder(Long orderid){
        //주문 엔트리 조회
        Order order =    orderRepository.findOne(orderid);
        //주문 캔슬
        order.cancel();
    }



//    public List<Order> findOrders(OrderSerach orderSerach) {
//        return orderRepository.findAll();
//    }



    //검색





}
