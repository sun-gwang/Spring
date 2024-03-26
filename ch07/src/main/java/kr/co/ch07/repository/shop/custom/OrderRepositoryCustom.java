package kr.co.ch07.repository.shop.custom;

import kr.co.ch07.entity.shop.Order;

import java.util.List;

public interface OrderRepositoryCustom {
    public List<Order> selectOrders();
    public Order selectOrder(int orderId);

}
