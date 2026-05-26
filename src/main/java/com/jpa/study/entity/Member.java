package main.java.com.jpa.study.entity;

import com.jpa.study.base.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 회원 엔티티
 *
 * [매핑 정보]
 * - @Entity           : JPA가 관리하는 엔티티 선언 (필수)
 * - @Table            : 매핑할 테이블 이름 지정 (생략 시 클래스 이름 사용)
 * - @Column(name=...) : DB 컬럼명이 다를 때 명시적으로 지정
 * - @GeneratedValue   : PK 자동 생성 전략 (기본: AUTO)
 *
 * [연관관계]
 * - 주문(Order)과 1:N 양방향 관계
 * - 연관관계 주인: Order.member (외래키 MEMBER_ID가 ORDERS 테이블에 있음)
 * - Member.orders는 mappedBy → 읽기 전용
 */
@Entity
public class Member extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    private String name;
    private String city;
    private String street;
    private String zipcode;

    // 1:N 양방향 (읽기 전용 - mappedBy)
    // 실무에서는 굳이 넣지 않아도 되는 경우가 많음
    // 주문 내역은 Order 테이블의 MEMBER_ID로 조회하면 충분
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public String getZipcode() { return zipcode; }
    public void setZipcode(String zipcode) { this.zipcode = zipcode; }

    public List<Order> getOrders() { return orders; }
}
