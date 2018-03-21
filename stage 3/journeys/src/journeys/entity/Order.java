package journeys.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="orders")
public class Order {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="order_id")
	private Long order_id;

	@OneToOne
	@JoinColumn(name="client_id")
    private Client client;

	@OneToOne
	@JoinColumn(name="journey_id")
    private Journey journey;

	@OneToOne
	@JoinColumn(name="route_start_id")
    private Route route_start;

	@OneToOne
	@JoinColumn(name="route_end_id")
    private Route route_end;

    @Column(name="date_of_order", insertable = false, updatable = false, columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
	private Date date_of_order;

	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Journey getJourney() {
		return journey;
	}

	public void setJourney(Journey journey) {
		this.journey = journey;
	}

	public Route getRoute_start() {
		return route_start;
	}

	public void setRoute_start(Route route_start) {
		this.route_start = route_start;
	}

	public Route getRoute_end() {
		return route_end;
	}

	public void setRoute_end(Route route_end) {
		this.route_end = route_end;
	}

	public Date getDate_of_order() {
		return date_of_order;
	}

	public void setDate_of_order(Date date_of_order) {
		this.date_of_order = date_of_order;
	}
}
