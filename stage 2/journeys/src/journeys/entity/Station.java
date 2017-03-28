package journeys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="stations")
public class Station
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="station_id")
    private Long station_id;

    @Column(name="station_name")
    private String station_name;

    public Long get_id() {
		return station_id;
	}

	public void set_id(Long id) {
		this.station_id = id;
	}

	public String get_name() {
		return station_name;
	}

	public void set_name(String name) {
		this.station_name = name;
	}
}
