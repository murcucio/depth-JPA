package main.java.com.jpa.study.entity;

import com.jpa.study.base.BaseEntity;

import javax.persistence.*;

/**
 * 배송 엔티티
 *
 * [연관관계]
 * - 주문(Order)과 1:1 양방향 관계
 * - 외래키(DELIVERY_ID)는 ORDERS 테이블에 있음 → Order가 연관관계 주인
 * - Delivery.order는 mappedBy → 읽기 전용
 *
 * [1:1 외래키 위치 선택 이유]
 * - 주 테이블(ORDERS)에 외래키를 두는 방식 선택
 * - 장점: 주 테이블 조회만으로 배송 데이터 유무 확인 가능, JPA 매핑 편리
 * - 단점: 배송 없는 주문의 경우 외래키에 NULL 허용 필요
 */
@Entity
public class Delivery extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "DELIVERY_ID")
    private Long id;

    // 1:1 양방향 (읽기 전용 - mappedBy)
    // Order.delivery 가 연관관계 주인
    @OneToOne(mappedBy = "delivery")
    private Order order;

    private String city;
    private String street;
    private String zipcode;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public String getZipcode() { return zipcode; }
    public void setZipcode(String zipcode) { this.zipcode = zipcode; }

    public DeliveryStatus getStatus() { return status; }
    public void setStatus(DeliveryStatus status) { this.status = status; }
}
