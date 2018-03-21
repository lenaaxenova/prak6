package journeys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="stations")
public class Station
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="station_id")
    private Long station_id;

    @Column(name="station_name")
	@NotEmpty(message = "Название станции не должно быть пустым.")
	@Size(max = 255, message = "Название станции не может быть более 255 символов.")
    private String station_name;

    public Long getStation_id() {
		return station_id;
	}

	public void setStation_id(Long id) {
		this.station_id = id;
	}

	public String getStation_name() {
		return station_name;
	}

	public void setStation_name(String name) {
		this.station_name = name;
	}
}
