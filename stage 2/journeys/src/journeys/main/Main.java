package journeys.main;

//import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import journeys.utils.HibernateUtil;
//import journeys.utils.MyEntry;
import journeys.dao.*;
import journeys.entity.Client;
//import journeys.entity.*;

public class Main {
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();


		ClientDAO clientdao = new ClientDAO();
		//JourneyDAO journeydao = new JourneyDAO();
		session.beginTransaction();

		List<Client> list = clientdao.findAll();
		//List<MyEntry<Journey, Date>> shedule = journeydao.getFullShedule();
		
		session.getTransaction().commit();


		for(Client elem: list) {
			System.out.println(elem.get_email());
		}
		
		/*for(MyEntry<Journey, Date> elem: shedule) {
			System.out.println("!!!!!!!");
			System.out.println(elem.getKey().get_journey_id());
			System.out.println(elem.getValue());
		}*/
	}
}
