package journeys.main;

import java.util.List;

import org.hibernate.Session;
import journeys.utils.HibernateUtil;

import journeys.dao.ClientDAO;
import journeys.entity.Client;

public class Main {
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();


		ClientDAO clientdao = new ClientDAO();

		session.beginTransaction();

		List<Client> list = clientdao.findAll();

		session.getTransaction().commit();


		for(Client elem: list) {
			System.out.println(elem.get_address());
		}
	}
}
