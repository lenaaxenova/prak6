package journeys.dao;

import java.util.List;
import org.hibernate.criterion.Restrictions;
import journeys.entity.Client;
import journeys.entity.Order;

public class ClientDAO extends AbstractDAO<Client> {
	public ClientDAO() {
		super(Client.class);
	}

	@SuppressWarnings("unchecked")
	public List<Order> findAllOrders(Client c) {
		return (List<Order>)getCurrentSession().createCriteria(Order.class).add(Restrictions.eq("client", c)).list();
	}
}
