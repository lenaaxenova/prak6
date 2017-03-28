package journeys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="directions")
public class Direction
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="direction_id")
    private Long direrction_id;

    @Column(name="direction_name")
    private String direction_name;

    public Long get_id() {
		return direrction_id;
	}

	public void set_id(Long id) {
		this.direrction_id = id;
	}

	public String get_name() {
		return direction_name;
	}

	public void set_name(String name) {
		this.direction_name = name;
	}
}
