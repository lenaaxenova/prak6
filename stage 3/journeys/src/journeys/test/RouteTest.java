package journeys.test;

import java.util.Date;
import java.util.List;

import journeys.dao.JourneyDAO;
import journeys.dao.RouteDAO;
import journeys.dao.StationDAO;
import journeys.entity.Route;
import journeys.entity.Station;
import journeys.entity.Journey;
import journeys.utils.HibernateUtil;
import journeys.utils.MyEntry;

import org.hibernate.Session;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class RouteTest {
  private RouteDAO routedao;

  @BeforeMethod
  public void beforeMethod() {
	  routedao = new RouteDAO();

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
	  List<Route> routes = routedao.findAll();
	  Assert.assertTrue(routes.size() == 9);
  }

  @Test
  public void saveOrUpdate() {
	  Route route = routedao.findById(1L);

	  route.setTime_of_stop(5);
	  routedao.saveOrUpdate(route);

	  route = routedao.findById(1L);
	  Assert.assertEquals(route.getTime_of_stop(), 5);

	  route.setTime_of_stop(0);
	  routedao.saveOrUpdate(route);
  }

  @Test
  public void delete() {
	  Route route = new Route();
	  route.setJourney(new JourneyDAO().findById(1L));
	  route.setStation(new StationDAO().findById(1L));
	  route.setTime_offset(0);
	  route.setTime_of_stop(0);
	  route.setCost_offset(0);
	  routedao.saveOrUpdate(route);

	  List<Route> routes = routedao.findAll();
	  Assert.assertTrue(routes.size() == 10);

	  routedao.delete(route);

	  routes = routedao.findAll();
	  Assert.assertTrue(routes.size() == 9);
  }

  @Test
  public void getCost() {
	  Route start = routedao.findById(1L);
      Route end = routedao.findById(2L);

	  Assert.assertEquals(routedao.getCost(start, end), 145.0);
  }

  @Test
  public void getSheduleOfStops() {
      Journey journey = new JourneyDAO().findById(1L);
      List<MyEntry<Station, Integer>> shedule = routedao.getSheduleOfStops(journey);
      Assert.assertTrue(shedule.size() == 3);
  }

  @Test
  public void getShedule() {
      Journey journey = new JourneyDAO().findById(1L);
      List<MyEntry<Station, Date>> shedule = routedao.getShedule(journey);
      Assert.assertTrue(shedule.size() == 3);
  }
}
