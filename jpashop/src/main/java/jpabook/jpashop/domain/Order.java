package jpabook.jpashop.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="orders") // 이렇게 테이블명 지정안하면 관례상 order로 만들어짐
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id") // fk명이 member_id, 연관관계 주인!!, 값 수정할 수 있음
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery; //배송정보

    private LocalDateTime orderDate; //주문시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status; //주문상태 [ORDER, CANCEL]


    //==연관관계 메서드==//
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }
}
//    @OneToOne
//    @JoinColumn(name = "delivery_id")
//    private Delivery delivery;
//
//    private LocalDateTime orderDate; //주문시간
//
//    @Enumerated(EnumType.STRING)
//    private OrderStatus status; // 주문 상태 [ORDER, CANCEL]
//
//}

/*
1:1 관계에서는 fk를 양쪽 어느 테이블에 놔도 상관없다
access를 많이 하는 쪽에 fk를 넣는다
orders에 fk넣음
 */