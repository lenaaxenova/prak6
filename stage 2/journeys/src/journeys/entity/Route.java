package journeys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="routes")
public class Route
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="route_id")
    private Long route_id;

    @OneToOne
	@JoinColumn(name="journey_id")
    private Journey journey;

    @OneToOne
	@JoinColumn(name="station_id")
    private Station station;

    @Column(name="time_of_stop")
    private int time_of_stop;

    @Column(name="cost_offset")
    private double cost_offset;

    @Column(name="time_offset")
    private int time_offset;

    public Long get_route_id() {
        return route_id;
    }

    public void set_route_id(Long route_id) {
        this.route_id = route_id;
    }

    public Journey get_journey() {
        return journey;
    }

    public void set_journey(Journey journey) {
        this.journey = journey;
    }

    public Station get_station() {
        return station;
    }

    public void set_station(Station station) {
        this.station = station;
    }

    public int get_time_of_stop() {
        return time_of_stop;
    }

    public void set_time_of_stop(int time_of_stop) {
        this.time_of_stop = time_of_stop;
    }

    public double get_cost_offset() {
        return cost_offset;
    }

    public void set_cost_offset(double cost_offset) {
        this.cost_offset = cost_offset;
    }

    public int get_time_offset() {
        return time_offset;
    }

    public void set_time_offset(int time_offset) {
        this.time_offset = time_offset;
    }
}
