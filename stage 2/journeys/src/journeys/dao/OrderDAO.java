package journeys.dao;

import journeys.entity.Order;

public class OrderDAO extends AbstractDAO<Order> {
	public OrderDAO() {
		super(Order.class);
	}

	public double getCost(Order o) {
		return Math.abs(o.get_route_end().get_cost_offset() - o.get_route_start().get_cost_offset());
	}
}
