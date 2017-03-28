package journeys.dao;

import java.util.List;
import org.hibernate.Session;
import journeys.utils.HibernateUtil;

public abstract class AbstractDAO<E> {
	private Class<E> entityClass;
	
    public AbstractDAO(Class<E> entityClass) {
		this.entityClass = entityClass;
	}

	public Session getCurrentSession() {
        return HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
    @SuppressWarnings("unchecked")
	public E findById(Long id) {
        return (E)getCurrentSession().get(entityClass, id);
    }
    
    @SuppressWarnings("unchecked")
    public List<E> findAll() {
    	return (List<E>)getCurrentSession().createCriteria(entityClass).list();
    }
 
    public void saveOrUpdate(E e) {
        getCurrentSession().saveOrUpdate(e);
    }
 
    public void delete(E e) {
        getCurrentSession().delete(e);
    }
}
