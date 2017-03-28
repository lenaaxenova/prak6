package journeys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="companies")
public class Company
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="company_id")
    private Long company_id;

    @Column(name="company_name")
    private String company_name;

    public Long get_id() {
		return company_id;
	}

	public void set_id(Long id) {
		this.company_id = id;
	}

	public String get_name() {
		return company_name;
	}

	public void set_name(String name) {
		this.company_name = name;
	}
}
