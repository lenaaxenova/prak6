package journeys.dao;

import java.util.List;
import org.hibernate.criterion.*;
import journeys.entity.Company;
import journeys.entity.Order;
import journeys.entity.Client;
import journeys.entity.Journey;
import java.util.ArrayList;

public class CompanyDAO extends AbstractDAO<Company> {
	public CompanyDAO() {
		super(Company.class);
	}

    @SuppressWarnings("unchecked")
    public List<Client> findAllClients(Company c) {
    	List<Journey> journeys = (List<Journey>)getCurrentSession().createCriteria(Journey.class).add(Restrictions.eq("company", c)).list();
    	List<Order> orders = (List<Order>)getCurrentSession().createCriteria(Order.class).list();
    	List<Client> clients = new ArrayList<Client>();
    	for (Journey j: journeys) {
    		for(Order o: orders) {
    			if (o.get_journey().get_journey_id() == j.get_journey_id()) {
    				clients.add(o.get_client());
    			}
    		}
    	}
    	
        return clients;
    }
}
