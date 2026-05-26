package main.java.com.jpa.study.entity;

/**
 * 주문 상태
 * - @Enumerated(EnumType.STRING) 과 함께 사용
 * - EnumType.ORDINAL 사용 금지: enum 순서 변경 시 데이터 꼬임 발생
 */
public enum OrderStatus {
    ORDER, CANCEL
}
