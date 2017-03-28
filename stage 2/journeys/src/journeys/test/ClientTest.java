package journeys.test;

import java.util.List;
import java.util.Random;

import journeys.dao.ClientDAO;
import journeys.entity.Client;
import journeys.entity.Order;
import journeys.utils.HibernateUtil;

import org.hibernate.Session;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class ClientTest {
  private ClientDAO clientdao;

  @BeforeMethod
  public void beforeMethod() {
	  clientdao = new ClientDAO();

	  Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	  session.beginTransaction();
  }

  @AfterMethod
  public void afterMethod() {
	  Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	  session.getTransaction().commit();
  }

  @Test
  public void findAll() {
	  List<Client> clients = clientdao.findAll();
	  Assert.assertTrue(clients.size() == 5);
  }

  @Test
  public void saveOrUpdate() {
	  Client client = clientdao.findById(1L);

	  Random r = new Random();
	  String client_new_fname = String.valueOf(r.nextInt());

	  client.set_first_name(client_new_fname);
	  clientdao.saveOrUpdate(client);

	  client = clientdao.findById(1L);
	  Assert.assertEquals(client.get_first_name(), client_new_fname);
  }

  @Test
  public void delete() {
	  Client client = new Client();
      client.set_first_name("first_name");
	  client.set_middle_name("middle_name");
      client.set_last_name("last_name");
	  client.set_address("address");
	  client.set_email("email");
	  client.set_phone_number("phone");
      client.set_password("password");
      client.set_is_admin(false);

	  clientdao.saveOrUpdate(client);

	  List<Client> clients = clientdao.findAll();
	  Assert.assertTrue(clients.size() == 6);

	  clientdao.delete(client);

	  clients = clientdao.findAll();
	  Assert.assertTrue(clients.size() == 5);
  }

  @Test
  public void findAllOrders() {
	  Client client = clientdao.findById(1L);

	  List<Order> orders = clientdao.findAllOrders(client);
	  Assert.assertTrue(orders.size() == 1);

	  Order order = orders.get(0);
	  Assert.assertTrue(order.get_order_id() == 1L);
  }
}
