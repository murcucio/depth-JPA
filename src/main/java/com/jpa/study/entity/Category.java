package main.java.com.jpa.study.entity;

import com.jpa.study.base.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 카테고리 엔티티
 *
 * [셀프 참조 매핑]
 * - 카테고리는 계층 구조를 가짐 (상위/하위 카테고리)
 * - 같은 엔티티끼리 연관관계를 맺는 셀프 참조 가능
 *
 * [상품과의 관계]
 * - N:M 관계 (예시용)
 * - 실무에서는 @ManyToMany 사용 금지
 *   → 중간 테이블(CategoryItem)을 엔티티로 승격하여 1:N + N:1로 풀어야 함
 * - 이 예제는 강의 예시 목적으로만 사용
 */
@Entity
public class Category extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "CATEGORY_ID")
    private Long id;

    private String name;

    // 셀프 참조 - 상위 카테고리 (N:1)
    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private Category parent;

    // 셀프 참조 - 하위 카테고리 목록 (1:N, 읽기 전용)
    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    /**
     * 상품과 다대다 매핑 (예시용 - 실무 사용 금지)
     *
     * @ManyToMany 한계:
     * 1. 연결 테이블에 추가 컬럼(등록일, 수량 등) 삽입 불가
     * 2. 예상치 못한 쿼리 발생
     *
     * 실무 대안: CategoryItem 엔티티를 별도로 만들어 1:N + N:1로 풀기
     */
    @ManyToMany
    @JoinTable(
            name = "CATEGORY_ITEM",
            joinColumns = @JoinColumn(name = "CATEGORY_ID"),
            inverseJoinColumns = @JoinColumn(name = "ITEM_ID")
    )
    private List<Item> items = new ArrayList<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Category getParent() { return parent; }
    public void setParent(Category parent) { this.parent = parent; }

    public List<Category> getChild() { return child; }

    public List<Item> getItems() { return items; }
}
