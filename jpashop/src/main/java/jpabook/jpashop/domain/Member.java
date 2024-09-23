package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded // 내장됐다! 임베더블, 임베디드 둘 중 하나만 적어도 되는데 걍 둘 다 적어주면 바로 알 수 있음
    private Address address;

    @OneToMany(mappedBy="member") // 멤버 입장에서 한명의 고객이 여러개의 주문을 가질 수 있다
    private List<Order> orders = new ArrayList<>();
}
