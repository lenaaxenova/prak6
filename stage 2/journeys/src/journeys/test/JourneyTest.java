package journeys.test;

import java.util.List;
import java.util.Date;

import journeys.dao.*;
import journeys.entity.*;
import journeys.entity.Order;
import journeys.utils.*;

import org.hibernate.Session;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class JourneyTest {
  private JourneyDAO journeydao;

  @BeforeMethod
  public void beforeMethod() {
	  journeydao = new JourneyDAO();

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
	  List<Journey> journeydepartures = journeydao.findAll();
	  Assert.assertTrue(journeydepartures.size() == 3);
  }

  @Test
  public void saveOrUpdate() {
	  Journey journey = journeydao.findById(1L);

	  journey.set_number_of_places(100L);
	  journeydao.saveOrUpdate(journey);

	  journey = journeydao.findById(1L);
	  Assert.assertEquals(journey.get_number_of_places().longValue(), 100);

	  journey.set_number_of_places(10L);
	  journeydao.saveOrUpdate(journey);
  }

  @SuppressWarnings("deprecation")
  @Test
  public void delete() {
	  Journey journey = new Journey();
      journey.set_direction(new DirectionDAO().findById(1L));
	  journey.set_company(new CompanyDAO().findById(1L));
	  journey.set_number_of_places(10L);

      Date date = new Date(2017, 3, 1);
      Date time = new Date(2017, 3, 1);
      time.setHours(10);
      time.setMinutes(30);

	  journey.set_start_date(date);
      journey.set_start_time(time);
	  journeydao.saveOrUpdate(journey);

	  List<Journey> journeys = journeydao.findAll();
	  Assert.assertTrue(journeys.size() == 4);

	  journeydao.delete(journey);

	  journeys = journeydao.findAll();
	  Assert.assertTrue(journeys.size() == 3);
  }

  @Test
  public void getClients() {
	  Journey journey = journeydao.findById(1L);
	  List<Client> clients = journeydao.getClients(journey);

	  Assert.assertTrue(clients.size() == 1);
  }

  @Test
  public void getOrders() {
	  Journey journey = journeydao.findById(1L);
	  List<Order> orders = journeydao.getOrders(journey);

	  Assert.assertTrue(orders.size() == 1);
  }

  @Test
  public void getFullShedule() {
      List<MyEntry<Journey, Date>> shedule = journeydao.getFullShedule();
      Assert.assertTrue(shedule.size() == 9);
  }

  @Test
  public void getSheduleWithDirection() {
      Direction direction = new DirectionDAO().findById(1L);
      List<MyEntry<Journey, Date>> shedule = journeydao.getSheduleWithDirection(direction);
      Assert.assertTrue(shedule.size() == 1);
  }

  @Test
  public void getSheduleWithStation() {
      Station station = new StationDAO().findById(1L);
      List<MyEntry<Journey, Date>> shedule = journeydao.getSheduleWithStation(station);
      Assert.assertTrue(shedule.size() == 2);
  }
}
