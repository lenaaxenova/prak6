package journeys.main;

//import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import journeys.utils.HibernateUtil;
//import journeys.utils.MyEntry;
import journeys.dao.*;
import journeys.entity.Client;
import journeys.entity.Journey;
import journeys.entity.Route;
public class Main {
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();


		ClientDAO clientdao = new ClientDAO();
		JourneyDAO journeydao = new JourneyDAO();
		session.beginTransaction();

		List<Client> list = clientdao.findAll();
		Journey journey = journeydao.findById(1l);
		List<Route> routes = journeydao.findAllRoutes(journey);
		//List<MyEntry<Journey, Date>> shedule = journeydao.getFullShedule();
		
		session.getTransaction().commit();


		for(Client elem: list) {
			System.out.println(elem.getEmail());
		}
		System.out.println(routes.get(0).getStation().getStation_name());
		System.out.println(routes.get(routes.size() - 1).getStation().getStation_name());
		for(Route route: routes) {
			System.out.println(route.getStation().getStation_name());
		}
		
		/*for(MyEntry<Journey, Date> elem: shedule) {
			System.out.println("!!!!!!!");
			System.out.println(elem.getKey().get_journey_id());
			System.out.println(elem.getValue());
		}*/
	}
}
