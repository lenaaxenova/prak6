package journeys.test;

import java.util.List;
import java.util.Random;

import journeys.dao.StationDAO;
import journeys.entity.Station;
import journeys.utils.HibernateUtil;

import org.hibernate.Session;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class StationTest {
  private StationDAO stationdao;

  @BeforeMethod
  public void beforeMethod() {
	  stationdao = new StationDAO();

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
	  List<Station> stations = stationdao.findAll();
	  Assert.assertTrue(stations.size() == 8);
  }

  @Test
  public void saveOrUpdate() {
	  Station station = stationdao.findById(1L);

	  Random r = new Random();
	  String new_station_name = String.valueOf(r.nextInt());

	  station.setStation_name(new_station_name);
	  stationdao.saveOrUpdate(station);

	  station = stationdao.findById(1L);
	  Assert.assertEquals(station.getStation_name(), new_station_name);
  }

  @Test
  public void delete() {
	  Station station = new Station();
      station.setStation_name("new_station");

	  stationdao.saveOrUpdate(station);

	  List<Station> stations = stationdao.findAll();
	  Assert.assertTrue(stations.size() == 9);

	  stationdao.delete(station);

	  stations = stationdao.findAll();
	  Assert.assertTrue(stations.size() == 8);
  }

}
