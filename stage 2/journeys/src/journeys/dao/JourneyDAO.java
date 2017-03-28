package journeys.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.*;
import journeys.entity.Journey;
import journeys.entity.Client;
import journeys.entity.Direction;
import journeys.entity.Station;
import journeys.entity.Route;
import journeys.entity.Order;
import journeys.utils.MyEntry;

public class JourneyDAO extends AbstractDAO<Journey> {
	public JourneyDAO() {
		super(Journey.class);
	}

    @SuppressWarnings("unchecked")
    public List<Client> getClients(Journey j) {
        return (List<Client>)getCurrentSession().createCriteria(Order.class).add(Restrictions.eq("journey", j)).setProjection(Projections.property("client")).list();
    }

    @SuppressWarnings("unchecked")
    public List<Order> getOrders(Journey j) {
        return (List<Order>)getCurrentSession().createCriteria(Order.class).add(Restrictions.eq("journey", j)).list();
    }

    @SuppressWarnings("unchecked")
    public List<MyEntry<Journey, Date>> getFullShedule() {
        List<Journey> journeys = (List<Journey>)getCurrentSession().createCriteria(Journey.class).addOrder(org.hibernate.criterion.Order.asc("start_date")).list();

        List<MyEntry<Journey, Date>> shedule = new ArrayList<MyEntry<Journey, Date>>();
        for(Journey j: journeys) {
            MyEntry<Journey, Date> cur_obj = new MyEntry<Journey, Date>(j, j.get_start_date());
            shedule.add(cur_obj);
        }
        return shedule;
    }

    @SuppressWarnings("unchecked")
    public List<MyEntry<Journey, Date>> getSheduleWithDirection(Direction d) {
        List<Journey> journeys = (List<Journey>)getCurrentSession().createCriteria(Journey.class).add(Restrictions.eq("direction", d)).addOrder(org.hibernate.criterion.Order.asc("start_date")).list();

        List<MyEntry<Journey, Date>> shedule = new ArrayList<MyEntry<Journey, Date>>();
        for(Journey j: journeys) {
            MyEntry<Journey, Date> cur_obj = new MyEntry<Journey, Date>(j, j.get_start_date());
            shedule.add(cur_obj);
        }
        return shedule;
    }

    @SuppressWarnings("unchecked")
    public List<MyEntry<Journey, Date>> getSheduleWithStation(Station s) {
        List<Journey> journeys = (List<Journey>)getCurrentSession().createCriteria(Route.class).add(Restrictions.eq("station", s)).setProjection(Projections.property("journey")).list();

        List<MyEntry<Journey, Date>> shedule = new ArrayList<MyEntry<Journey, Date>>();
        for(Journey j: journeys) {
            MyEntry<Journey, Date> cur_obj = new MyEntry<Journey, Date>(j, j.get_start_date());
            shedule.add(cur_obj);
        }
        return shedule;
    }
}
