package journeys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="clients")
public class Client {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="client_id")
	private Long client_id;

	@Column(name="first_name")
    private String first_name;

    @Column(name="middle_name")
    private String middle_name;

	@Column(name="last_name")
    private String last_name;

	@Column(name="address")
    private String address;

	@Column(name="phone_number")
    private String phone_number;

	@Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="is_admin")
    private Boolean is_admin;

	public Long get_id() {
		return client_id;
	}
	public void set_id(Long id) {
		this.client_id = id;
	}
	public String get_first_name() {
		return first_name;
	}
	public void set_first_name(String first_name) {
		this.first_name = first_name;
	}

    public String get_middle_name() {
		return middle_name;
	}
	public void set_middle_name(String middle_name) {
		this.middle_name = middle_name;
	}

	public String get_last_name() {
		return last_name;
	}
	public void set_last_name(String last_name) {
		this.last_name = last_name;
	}

	public String get_address() {
		return address;
	}
	public void set_address(String address) {
		this.address = address;
	}
	public String get_phone_number() {
		return phone_number;
	}
	public void set_phone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String get_email() {
		return email;
	}
	public void set_email(String email) {
		this.email = email;
	}

    public String get_password() {
        return password;
    }

    public void set_password(String password) {
        this.password = password;
    }

    public Boolean get_is_admin() {
        return is_admin;
    }

    public void set_is_admin(Boolean is_admin) {
        this.is_admin = is_admin;
    }
}
