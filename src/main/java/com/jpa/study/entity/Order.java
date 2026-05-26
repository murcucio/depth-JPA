package main.java.com.jpa.study.entity;

import com.jpa.study.base.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 주문 엔티티
 *
 * [테이블명: ORDERS]
 * ORDER는 일부 DB에서 예약어이므로 ORDERS로 지정
 *
 * [연관관계]
 * - 회원(Member)과 N:1 단방향 → 연관관계 주인 (MEMBER_ID FK 보유)
 * - 주문상품(OrderItem)과 1:N 양방향 → 연관관계 주인: OrderItem.order
 * - 배송(Delivery)과 1:1 단방향 → 연관관계 주인 (DELIVERY_ID FK 보유)
 */
@Entity
@Table(name = "ORDERS")
public class Order extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    // N:1 - 연관관계 주인 (외래키 MEMBER_ID 관리)
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    // 1:N 양방향 (읽기 전용 - mappedBy)
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    // 1:1 - 연관관계 주인 (외래키 DELIVERY_ID 관리)
    @OneToOne
    @JoinColumn(name = "DELIVERY_ID")
    private Delivery delivery;

    private LocalDateTime orderDate;

    // EnumType.ORDINAL 사용 금지 → STRING 사용
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    // ==== 연관관계 편의 메서드 ====
    // 양방향 관계에서 양쪽 모두 값을 설정해야 하는 번거로움을 방지
    // 메서드 이름을 setXxx 대신 다른 이름으로 짓는 것을 권장 (단순 setter와 구분)

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    // ==== Getter / Setter ====

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Member getMember() { return member; }
    public void setMember(Member member) { this.member = member; }

    public List<OrderItem> getOrderItems() { return orderItems; }

    public Delivery getDelivery() { return delivery; }

    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }

    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }
}
