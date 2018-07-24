package im.ky.fy.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 角色表
 * @author yangjing
 *
 */
@Table(name = "t_role")
public class Role implements Serializable{
   
	@Id
	@Column(name="id") //mapper才使用  
	@GeneratedValue(strategy=GenerationType.IDENTITY) //主键
	private Long id;

    private String name; //角色名称
    
    @Column(name="role_type") 
    private String roleType; //角色类型
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    
	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", roleType=" + roleType
				+ "]";
	}

    
}