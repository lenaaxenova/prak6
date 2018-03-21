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

	  client.setFirst_name(client_new_fname);
	  clientdao.saveOrUpdate(client);

	  client = clientdao.findById(1L);
	  Assert.assertEquals(client.getFirst_name(), client_new_fname);
  }

  @Test
  public void delete() {
	  Client client = new Client();
      client.setFirst_name("first_name");
	  client.setMiddle_name("middle_name");
      client.setLast_name("last_name");
	  client.setAddress("address");
	  client.setEmail("email");
	  client.setPhone_number("phone");
      client.setPassword("password");
      client.setIs_admin(false);

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
	  Assert.assertTrue(order.getOrder_id() == 1L);
  }
}
