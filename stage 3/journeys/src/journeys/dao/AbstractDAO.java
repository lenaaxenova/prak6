package journeys.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import journeys.utils.HibernateUtil;

@Repository
public abstract class AbstractDAO<E> {
	private Class<E> entityClass;
	
	@Autowired
	private SessionFactory sessionFactory;
	
    public AbstractDAO(Class<E> entityClass) {
		this.entityClass = entityClass;
	}

	public Session getCurrentSession() {
		if (sessionFactory == null) {
			return HibernateUtil.getSessionFactory().getCurrentSession();
		}
		return sessionFactory.getCurrentSession();
    }
    
	@Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
	public E findById(Long id) {
        return (E)getCurrentSession().get(entityClass, id);
    }
    
    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<E> findAll() {
    	return (List<E>)getCurrentSession().createCriteria(entityClass).list();
    }
    
    @Transactional(readOnly = false)
    public void saveOrUpdate(E e) {
        getCurrentSession().saveOrUpdate(e);
    }
    
    @Transactional(readOnly = false)
    public void delete(E e) {
        getCurrentSession().delete(e);
    }
}
