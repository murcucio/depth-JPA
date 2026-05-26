package main.java.com.jpa.study.entity;

import com.jpa.study.base.BaseEntity;

import javax.persistence.*;

/**
 * 주문상품 엔티티
 *
 * [연관관계]
 * - 주문(Order)과 N:1 → 연관관계 주인 (ORDER_ID FK 보유)
 * - 상품(Item)과 N:1 → 연관관계 주인 (ITEM_ID FK 보유)
 *
 * 주문과 상품은 다대다(N:M) 관계이나,
 * 연결 테이블(ORDER_ITEM)을 엔티티로 승격하여 일대다 + 다대일로 풀어냄
 * → 주문 수량, 주문 가격 같은 추가 데이터를 넣을 수 있음
 */
@Entity
public class OrderItem extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "ORDER_ITEM_ID")
    private Long id;

    // N:1 - 연관관계 주인
    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    // N:1 - 연관관계 주인
    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    private int orderPrice; // 주문 가격
    private int count;      // 주문 수량

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }

    public Item getItem() { return item; }
    public void setItem(Item item) { this.item = item; }

    public int getOrderPrice() { return orderPrice; }
    public void setOrderPrice(int orderPrice) { this.orderPrice = orderPrice; }

    public int getCount() { return count; }
    public void setCount(int count) { this.count = count; }
}
