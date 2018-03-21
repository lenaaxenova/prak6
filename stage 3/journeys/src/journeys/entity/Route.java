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

    public Long getRoute_id() {
        return route_id;
    }

    public void setRoute_id(Long route_id) {
        this.route_id = route_id;
    }

    public Journey getJourney() {
        return journey;
    }

    public void setJourney(Journey journey) {
        this.journey = journey;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public int getTime_of_stop() {
        return time_of_stop;
    }

    public void setTime_of_stop(int time_of_stop) {
        this.time_of_stop = time_of_stop;
    }

    public double getCost_offset() {
        return cost_offset;
    }

    public void setCost_offset(double cost_offset) {
        this.cost_offset = cost_offset;
    }

    public int getTime_offset() {
        return time_offset;
    }

    public void setTime_offset(int time_offset) {
        this.time_offset = time_offset;
    }
}
