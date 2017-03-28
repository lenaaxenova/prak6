package journeys.test;

import java.util.List;
import java.util.Random;

import journeys.dao.DirectionDAO;
import journeys.entity.Direction;

import journeys.utils.HibernateUtil;

import org.hibernate.Session;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class DirectionTest {
  private DirectionDAO directiondao;

  @BeforeMethod
  public void beforeMethod() {
	  directiondao = new DirectionDAO();

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
	  List<Direction> directions = directiondao.findAll();
	  Assert.assertTrue(directions.size() == 3);
  }

  @Test
  public void saveOrUpdate() {
	  Direction direction = directiondao.findById(1L);

	  Random r = new Random();
	  String new_direction_name = String.valueOf(r.nextInt());

	  direction.set_name(new_direction_name);
	  directiondao.saveOrUpdate(direction);

	  direction = directiondao.findById(1L);
	  Assert.assertEquals(direction.get_name(), new_direction_name);
  }

  @Test
  public void delete() {
	  Direction direction = new Direction();
      direction.set_name("new_direction");

	  directiondao.saveOrUpdate(direction);

	  List<Direction> directions = directiondao.findAll();
	  Assert.assertTrue(directions.size() == 4);

	  directiondao.delete(direction);

	  directions = directiondao.findAll();
	  Assert.assertTrue(directions.size() == 3);
  }

}
