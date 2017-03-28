package journeys.test;

import java.util.Date;
import java.util.List;

import journeys.dao.*;
import journeys.entity.*;
import journeys.utils.HibernateUtil;

import org.hibernate.Session;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class OrderTest {
  private OrderDAO orderdao;

  @BeforeMethod
  public void beforeMethod() {
	  orderdao = new OrderDAO();

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
	  List<Order> orders = orderdao.findAll();
	  Assert.assertTrue(orders.size() == 5);
  }

  @SuppressWarnings("deprecation")
  @Test
  public void saveOrUpdate() {
	  Order order = orderdao.findById(1L);

	  Date date = order.get_date_of_order();
	  date.setHours(0);

	  order.set_date_of_order(date);
	  orderdao.saveOrUpdate(order);

	  order = orderdao.findById(1L);
	  Assert.assertEquals(order.get_date_of_order(), date);

	  date.setHours(11);

	  order.set_date_of_order(date);
	  orderdao.saveOrUpdate(order);
  }

  @SuppressWarnings("deprecation")
  @Test
  public void delete() {
	  Order order = new Order();
	  order.set_client(new ClientDAO().findById(1L));
	  order.set_journey(new JourneyDAO().findById(1L));
	  order.set_route_start(new RouteDAO().findById(1L));
	  order.set_route_end(new RouteDAO().findById(2L));
	  Date date = new Date(2017, 3, 1);
      order.set_date_of_order(date);
	  orderdao.saveOrUpdate(order);

	  List<Order> orders = orderdao.findAll();
	  Assert.assertTrue(orders.size() == 6);

	  orderdao.delete(order);

	  orders = orderdao.findAll();
	  Assert.assertTrue(orders.size() == 5);
  }

  @Test
  public void getCost() {
	  Order order = orderdao.findById(1L);

	  Assert.assertEquals(orderdao.getCost(order), 188.0);
  }
}
