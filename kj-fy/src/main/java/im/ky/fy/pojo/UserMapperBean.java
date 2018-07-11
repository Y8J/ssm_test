package im.ky.fy.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户表
 * @author yangjing
 *
 */
@Table(name="t_user") //mapper才使用  
public class UserMapperBean implements Serializable {
	@Id
	@Column(name="id") //mapper才使用  
	@GeneratedValue(strategy=GenerationType.IDENTITY) //主键
	private Long id; //用户ID
	
	@Column(name="name") //mapper才使用  
	private  String userName; //用户名
	
	@Column(name="password") //mapper才使用  
	private String userPwd; //密码
	
	@Column(name="status") //mapper才使用  
	private Integer status; // 状态:0-失效; 1-正常; 2-锁定;3-待审核
	
	@Column(name="email") //mapper才使用  
	private String email; //邮箱
	
	@Column(name="mobile") //mapper才使用  
	private String mobile; //手机

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "UserMapperBean [id=" + id + ", userName=" + userName
				+ ", userPwd=" + userPwd + ", status=" + status + ", email="
				+ email + ", mobile=" + mobile + "]";
	}

	
}
