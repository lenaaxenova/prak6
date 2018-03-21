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
@Table(name="clients")
public class Client {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="client_id")
	private Long client_id;

	@Column(name="first_name")
	@NotEmpty(message = "Поле имени не должно быть пустым.")
	@Size(max = 255, message = "Имя не может содержать более 255 символов.")
    private String first_name;

    @Column(name="middle_name")
    @NotEmpty(message = "Поле отчества не должно быть пустым.")
	@Size(max = 255, message = "Отчество не может содержать более 255 символов.")
    private String middle_name;

	@Column(name="last_name")
	@NotEmpty(message = "Поле фамилии не должно быть пустым.")
	@Size(max = 255, message = "Фамилия не может содержать более 255 символов.")
    private String last_name;

	
	@Column(name="address")
	@NotEmpty(message = "Поле адреса не должно быть пустым.")
	@Size(max = 255, message = "Адрес не может содержать более 255 символов.")
    private String address;

	@Column(name="phone_number")
    private String phone_number;

	@Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="is_admin")
    private Boolean is_admin;

	public Long getClient_id() {
		return client_id;
	}
	public void setClient_id(Long id) {
		this.client_id = id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

    public String getMiddle_name() {
		return middle_name;
	}
	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}

	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(Boolean is_admin) {
        this.is_admin = is_admin;
    }
}
