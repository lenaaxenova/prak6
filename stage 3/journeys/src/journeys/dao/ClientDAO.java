package journeys.dao;

import java.util.List;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import journeys.entity.Client;
import journeys.entity.Order;

@Repository
public class ClientDAO extends AbstractDAO<Client> {
	public ClientDAO() {
		super(Client.class);
	}
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Order> findAllOrders(Client c) {
		return (List<Order>)getCurrentSession().createCriteria(Order.class).add(Restrictions.eq("client", c)).list();
	}
}
