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
    	List<Client> client = new ArrayList();
    	for (Journey j: journeys) {
    		
    	}
    	
        return (List<Client>)getCurrentSession().createCriteria(Order.class).add(Restrictions.eq("company", c)).setProjection(Projections.property("client")).list();
    }
}
