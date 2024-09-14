package jpa.jpashop.service;

import jakarta.persistence.EntityManager;
import jpa.jpashop.domain.Address;
import jpa.jpashop.domain.Member;
import jpa.jpashop.domain.Order;
import jpa.jpashop.domain.OrderStatus;
import jpa.jpashop.domain.item.Book;
import jpa.jpashop.domain.item.Item;
import jpa.jpashop.exception.NotEnoughstockException;
import jpa.jpashop.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {





    @Autowired
    EntityManager em;


    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 주문_생성 () throws Exception{
        //given
        Member member = createMember();


        Book book = createbook("시골 JPA", 10000, 10);

        int orderCount = 2;

        //when
        Long orderId = orderService.order(member.getId(), book.getId() , orderCount );


        //then

        Order getOrder = orderRepository.findOne(orderId);

        assertEquals("사웊 주문시 상태는 Order " , OrderStatus.ORDER ,getOrder.getStatus());
        assertEquals("주문된 아이템이 갯수가 맞아야한다 " , 1 , getOrder.getOrderitems().size());

        assertEquals("주문 가격이 맞아야한다 " , 10000* orderCount ,getOrder.getTotalPrice());
        assertEquals("재고 수량이 맞아야한다. " , 8 , book.getStockQuantity() );
    }


    @Test
    public void 주문취소 () throws Exception{
        //given

        //when


        //then
    }

    @Test(expected = NotEnoughstockException.class)
    public void 재고수량초과 () throws Exception{
        //given

        Member member = createMember();
        Item item = createbook("시골 JPA", 10000, 10);

        int orderCount = 11;

        //when
        orderService.order(createMember().getId(), item.getId() , orderCount);

        //then
        fail("재고 수량 부족 예외가 떠야함 ");
    }

    private Book createbook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울" , "강가" , "1231"));
        em.persist(member);
        return member;
    }
}