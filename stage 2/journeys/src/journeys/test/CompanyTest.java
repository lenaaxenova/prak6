package journeys.test;

import java.util.List;
import java.util.Random;

import journeys.dao.CompanyDAO;
import journeys.entity.Client;
import journeys.entity.Company;
import journeys.utils.HibernateUtil;

import org.hibernate.Session;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class CompanyTest {
  private CompanyDAO companydao;

  @BeforeMethod
  public void beforeMethod() {
	  companydao = new CompanyDAO();

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
	  List<Company> companies = companydao.findAll();
	  Assert.assertTrue(companies.size() == 3);
  }

  @Test
  public void saveOrUpdate() {
	  Company company = companydao.findById(1L);

	  Random r = new Random();
	  String company_new_name = String.valueOf(r.nextInt());

	  company.set_name(company_new_name);
	  companydao.saveOrUpdate(company);

	  company = companydao.findById(1L);
	  Assert.assertEquals(company.get_name(), company_new_name);
  }

  @Test
  public void delete() {
	  Company company = new Company();
	  company.set_name("new_name");
	  companydao.saveOrUpdate(company);

	  List<Company> stations = companydao.findAll();
	  Assert.assertTrue(stations.size() == 4);

	  companydao.delete(company);

	  stations = companydao.findAll();
	  Assert.assertTrue(stations.size() == 3);
  }

  @Test
  public void findAllClients() {
	  Company company = companydao.findById(1L);
	  List<Client> clients = companydao.findAllClients(company);

	  Assert.assertTrue(clients.size() == 1);
  }
}
