package journeys.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import journeys.entity.Order;

@Repository
public class OrderDAO extends AbstractDAO<Order> {
	public OrderDAO() {
		super(Order.class);
	}
	
	@Transactional(readOnly = true)
	public double getCost(Order o) {
		return Math.abs(o.getRoute_end().getCost_offset() - o.getRoute_start().getCost_offset());
	}
}
