package journeys.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import journeys.entity.Direction;
import journeys.entity.Journey;

@Repository
public class DirectionDAO extends AbstractDAO<Direction> {
	public DirectionDAO() {
		super(Direction.class);
	}
	
	@Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<Journey> getJourneys(Direction d) {
        List<Journey> journeys = (List<Journey>)getCurrentSession().createCriteria(Journey.class).add(Restrictions.eq("direction", d)).addOrder(org.hibernate.criterion.Order.asc("start_date")).list();

        return journeys;
    }
}
