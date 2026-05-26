package main.java.com.jpa.study.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 팀 엔티티
 *
 * [연관관계 기초 예제]
 * - 팀(1) : 회원(N) 관계
 * - Team.members는 양방향 탐색을 위한 컬렉션 (읽기 전용)
 * - 연관관계 주인: Member.team (TEAM_ID FK를 MEMBER 테이블에서 관리)
 *
 * [양방향 매핑 주의사항]
 * - mappedBy로 지정된 Team.members는 읽기 전용
 * - Team.members에만 값을 넣으면 DB에 반영되지 않음
 * - 반드시 연관관계 주인(Member.team)에 값을 설정해야 함
 * - 순수 객체 상태를 고려해 양쪽 모두 값을 설정하는 것을 권장
 *   → 연관관계 편의 메서드 활용
 */
@Entity
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;

    // 1:N 양방향 (읽기 전용 - mappedBy)
    // "team" = Member 엔티티의 필드명 'team'과 연결
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Member> getMembers() { return members; }
}
