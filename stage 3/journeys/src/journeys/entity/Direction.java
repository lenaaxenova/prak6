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
@Table(name="directions")
public class Direction
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="direction_id")
    private Long direrction_id;

    @Column(name="direction_name")
    @NotEmpty(message = "Название направления не должно быть пустым.")
	@Size(max = 255, message = "Название направления не может быть более 255 символов.")
    private String direction_name;

    public Long getDirection_id() {
		return direrction_id;
	}

	public void setDirection_id(Long id) {
		this.direrction_id = id;
	}

	public String getDirection_name() {
		return direction_name;
	}

	public void setDirection_name(String name) {
		this.direction_name = name;
	}
}
