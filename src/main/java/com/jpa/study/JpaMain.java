package main.java.com.jpa.study;

import com.jpa.study.entity.*;
import com.jpa.study.entity.inheritance.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

/**
 * JPA 전체 개념 실습 메인 클래스
 *
 * [EntityManagerFactory]
 * - 애플리케이션 로딩 시점에 딱 하나만 생성
 * - persistence.xml의 persistence-unit name과 일치해야 함
 *
 * [EntityManager]
 * - 요청(트랜잭션)마다 하나씩 생성 후 사용 종료 시 닫아야 함
 * - 쓰레드 간 공유 금지
 *
 * [트랜잭션]
 * - JPA의 모든 데이터 변경은 트랜잭션 안에서 실행
 */
public class JpaMain {

    public static void main(String[] args) {

        // EntityManagerFactory는 애플리케이션 전체에서 1개만 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // EntityManager는 요청마다 생성
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            // ========================
            // 1. 저장 예제
            // ========================

            // 팀 저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            // 회원 저장 (단방향 연관관계)
            Member member = new Member();
            member.setName("member1");
            member.setCity("서울");
            member.setStreet("강남대로 1");
            member.setZipcode("12345");
            member.setCreatedDate(LocalDateTime.now());
            member.setLastModifiedDate(LocalDateTime.now());

            // 연관관계 주인(member.team)에 값을 설정
            // JPA가 team의 PK를 꺼내어 TEAM_ID FK에 자동 세팅
            // member.changeTeam(team); // 연관관계 편의 메서드 사용 권장
            em.persist(member);

            // 배송 저장
            Delivery delivery = new Delivery();
            delivery.setCity("부산");
            delivery.setStreet("해운대로 1");
            delivery.setZipcode("67890");
            delivery.setStatus(DeliveryStatus.READY);
            em.persist(delivery);

            // 상품 저장 (상속관계 매핑)
            Book book = new Book();
            book.setName("JPA 프로그래밍");
            book.setPrice(35000);
            book.setStockQuantity(100);
            book.setAuthor("김영한");
            book.setIsbn("978-89-XXX");
            book.setCreatedDate(LocalDateTime.now());
            book.setLastModifiedDate(LocalDateTime.now());
            em.persist(book);

            // 주문 저장
            Order order = new Order();
            order.setMember(member);
            order.setDelivery(delivery);   // 연관관계 편의 메서드: order 양쪽 세팅
            order.setOrderDate(LocalDateTime.now());
            order.setStatus(OrderStatus.ORDER);
            order.setCreatedDate(LocalDateTime.now());
            order.setLastModifiedDate(LocalDateTime.now());
            em.persist(order);

            // 주문상품 저장
            OrderItem orderItem = new OrderItem();
            orderItem.setItem(book);
            orderItem.setOrderPrice(35000);
            orderItem.setCount(2);
            order.addOrderItem(orderItem); // 연관관계 편의 메서드: 양쪽 세팅
            em.persist(orderItem);

            // ========================
            // 2. 1차 캐시 비우기 (DB에서 직접 조회하는 쿼리 확인용)
            // ========================
            em.flush(); // 영속성 컨텍스트 변경 내용을 DB에 반영
            em.clear(); // 영속성 컨텍스트 초기화 (1차 캐시 제거)

            // ========================
            // 3. 조회 예제
            // ========================

            // 단방향 탐색: member → team
            Member findMember = em.find(Member.class, member.getId());
            // Team findTeam = findMember.getTeam(); // Member에 Team 참조가 있는 경우

            // Order 조회 후 연관 객체 참조
            Order findOrder = em.find(Order.class, order.getId());
            System.out.println("주문 상태: " + findOrder.getStatus());
            System.out.println("주문 회원: " + findOrder.getMember().getName());
            System.out.println("배송 도시: " + findOrder.getDelivery().getCity());

            // 주문상품 목록 조회
            for (OrderItem oi : findOrder.getOrderItems()) {
                System.out.println("상품명: " + oi.getItem().getName() + ", 수량: " + oi.getCount());
            }

            // ========================
            // 4. 수정 예제
            // ========================
            // JPA에서는 em.update() 같은 메서드 없음
            // 영속 상태의 엔티티 값만 변경하면 트랜잭션 커밋 시 자동 UPDATE 쿼리 실행
            // (변경 감지, Dirty Checking)
            findOrder.setStatus(OrderStatus.CANCEL);

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }
}
