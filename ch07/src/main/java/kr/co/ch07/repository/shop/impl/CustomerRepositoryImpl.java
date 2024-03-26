package kr.co.ch07.repository.shop.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.ch07.entity.shop.Customer;
import kr.co.ch07.entity.shop.QCustomer;
import kr.co.ch07.repository.shop.custom.CustomerRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
    - CustomerRepositoryCustom 구현 클래스
    - RepositoryCustom에서 접미사 Custom 대신 impl 접미사 네이밍 규칙

*/

@Repository @RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    // Q도메인 클래스 선언(QueryDSL이 사용하는 엔티티) 선언
    QCustomer qCustomer = QCustomer.customer;

    @Override
    public List<Customer> selectCustomers() {
        // 'select * from customer' QueryDSL 문법
        List<Customer> customers = jpaQueryFactory.select(qCustomer).from(qCustomer).fetch();
        return customers;
    }

    @Override
    public Customer selectCustomer(String custId) {
        // 'select * from customer where custId=?' QueryDSL 문법s
        return jpaQueryFactory
                .selectFrom(qCustomer)
                .where(qCustomer.custId.eq(custId))
                .fetchOne();
    }

}
