package journeys.dao;

import java.util.Date;
import java.util.List;
import journeys.utils.MyEntry;
import org.hibernate.criterion.*;
import journeys.entity.*;
import java.util.*;
import org.hibernate.criterion.Order;

public class RouteDAO extends AbstractDAO<Route> {
	public RouteDAO() {
		super(Route.class);
	}

    @SuppressWarnings("unchecked")
    public List<MyEntry<Station, Date>> getShedule(Journey j) {
        List<Route> routes = (List<Route>)getCurrentSession().createCriteria(Route.class).add(Restrictions.eq("journey", j)).addOrder(Order.asc("time_offset")).list();

        List<MyEntry<Station, Date>> shedule = new ArrayList<MyEntry<Station, Date>>();
        for(Route r: routes) {
            int cur_time_in_sec =(int)( j.get_start_date().getTime() + j.get_start_time().getTime() + r.get_time_offset() * 60 * 1000);
            Date date = new Date(cur_time_in_sec);
            //String date = Long.toString(days) + ' ' + Long.toString(hours) + ':' + Long.toString(minutes);
            MyEntry<Station, Date> cur_obj = new MyEntry<Station, Date>(r.get_station(), date);
            shedule.add(cur_obj);
        }
        return shedule;
    }

    @SuppressWarnings("unchecked")
    public List<MyEntry<Station, Integer>> getSheduleOfStops(Journey j) {
        List<Route> routes = (List<Route>)getCurrentSession().createCriteria(Route.class).add(Restrictions.eq("journey", j)).addOrder(Order.asc("time_offset")).list();

        List<MyEntry<Station, Integer>> shedule = new ArrayList<MyEntry<Station, Integer>>();
        for(Route r: routes) {
            MyEntry<Station, Integer> cur_obj = new MyEntry<Station, Integer>(r.get_station(), r.get_time_of_stop());
            shedule.add(cur_obj);
        }
        return shedule;
    }

    public double getCost(Route start, Route end) {
        return end.get_cost_offset() - start.get_cost_offset();
    }
}
