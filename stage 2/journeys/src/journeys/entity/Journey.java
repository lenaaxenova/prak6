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
@Table(name="journeys")
public class Journey
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="journey_id")
    private Long journey_id;

    @OneToOne
	@JoinColumn(name="direction_id")
    private Direction direction;

    @OneToOne
	@JoinColumn(name="company_id")
    private Company company;

    @Column(name="number_of_places")
    private Long number_of_places;

    @Column(name="start_date", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date start_date;

    @Column(name="start_time", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date start_time;

    public Long get_journey_id() {
        return journey_id;
    }

    public void set_journey_id(Long journey_id) {
        this.journey_id = journey_id;
    }

    public Direction get_direction() {
        return direction;
    }

    public void set_direction(Direction direction) {
        this.direction = direction;
    }

    public Company get_company() {
        return company;
    }

    public void set_company(Company company) {
        this.company = company;
    }

    public Long get_number_of_places() {
        return number_of_places;
    }

    public void set_number_of_places(Long number_of_places) {
        this.number_of_places = number_of_places;
    }

    public Date get_start_date() {
        return start_date;
    }

    public void set_start_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date get_start_time() {
        return start_time;
    }

    public void set_start_time(Date start_time) {
        this.start_time = start_time;
    }
}
