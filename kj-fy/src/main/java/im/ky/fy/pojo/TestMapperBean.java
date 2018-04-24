package im.ky.fy.pojo;

import java.io.Serializable;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="test") //mapper才使用  
public class TestMapperBean implements Serializable {
	@Id
	@Column(name="test_id") //mapper才使用  
	@GeneratedValue(strategy=GenerationType.IDENTITY) //主键
	private Integer id;
	@Column(name="test_name") //mapper才使用  
	private String name;
	@Column(name="test_password") //mapper才使用  
	private String password;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	
}
