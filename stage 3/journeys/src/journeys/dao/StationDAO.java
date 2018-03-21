package journeys.dao;

import java.util.List;

import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import journeys.entity.Journey;
import journeys.entity.Route;
import journeys.entity.Station;

@Repository
public class StationDAO extends AbstractDAO<Station> {
	public StationDAO() {
		super(Station.class);
	}
	
	@Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
	public List<Journey> getJourneys(Station s) {
	       List<Journey> journeys = (List<Journey>)getCurrentSession().createCriteria(Route.class).add(Restrictions.eq("station", s)).setProjection(Projections.property("journey")).list();
	       return journeys;
	 }
}
