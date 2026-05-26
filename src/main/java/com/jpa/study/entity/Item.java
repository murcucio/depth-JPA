package main.java.com.jpa.study.entity;

import com.jpa.study.base.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 상품 엔티티 (상속관계 매핑 - 부모)
 *
 * [상속 전략: 단일 테이블 전략 (SINGLE_TABLE)]
 * - 모든 자식 데이터(Album, Book, Movie)를 ITEM 테이블 하나에 통합
 * - DTYPE 컬럼으로 타입 구분
 * - 장점: 조인 없이 단순한 조회 쿼리, 빠른 성능
 * - 단점: 자식 엔티티의 컬럼은 모두 NULL 허용 필요
 *
 * [조인 전략으로 변경하려면]
 * strategy = InheritanceType.JOINED 로 변경하면 됨
 * 나머지 자식 엔티티 코드는 동일
 *
 * [구현 클래스마다 테이블 전략 (TABLE_PER_CLASS)은 사용 금지]
 * - DB 설계자, ORM 전문가 모두 비추천
 * - 여러 자식 조회 시 UNION 필요 → 성능 저하
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
public abstract class Item extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    // N:M (예시용 - 실무에서는 중간 엔티티로 풀어야 함)
    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public int getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }

    public List<Category> getCategories() { return categories; }
}
