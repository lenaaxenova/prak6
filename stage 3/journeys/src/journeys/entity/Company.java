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
@Table(name="companies")
public class Company
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="company_id")
    private Long company_id;

    @Column(name="company_name")
    @NotEmpty(message = "Название компании не должно быть пустым.")
	@Size(max = 255, message = "Название компании не может быть более 255 символов.")
    private String company_name;

    public Long getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Long id) {
		this.company_id = id;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String name) {
		this.company_name = name;
	}
}
