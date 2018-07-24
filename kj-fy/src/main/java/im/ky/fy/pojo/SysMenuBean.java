package im.ky.fy.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="t_permission") //mapper才使用  
public class SysMenuBean implements Serializable {

	//`id` int(11) NOT NULL,
	@Id
	@Column(name="id") //mapper才使用  
	@GeneratedValue(strategy=GenerationType.IDENTITY) //主键
	private Long id; //菜单ID
	
	//`parent_id` int(11) DEFAULT NULL,
	@Column(name="parent_id") //mapper才使用  
	private Long parentId; //菜单名称
	
	//`menu_name` varchar(50) DEFAULT NULL,
	@Column(name="menu_name") //mapper才使用  
	private String menuName; //菜单名称
	
	//`menu_url` varchar(50) DEFAULT '菜单链接',
	@Column(name="menu_url") //mapper才使用  
	private String menuUrl; //菜单链接
	
	// `menu_icon` varchar(50) DEFAULT '菜单图片',
	@Column(name="menu_icon") //mapper才使用  
	private String menuIcon; //菜单图片
	
	//`sort_num` int(11) DEFAULT '1',
	@Column(name="sort_num") //mapper才使用  
	private String sortNum; //排序
	
	//`user_id` int(11) DEFAULT '1' COMMENT '创建这个菜单的用户id',
	@Column(name="user_id") //mapper才使用  
	private String userId; //创建这个菜单的用户id
	
	//`is_del` int(11) DEFAULT '0' COMMENT '1-- 删除状态，0 -- 正常',
	@Column(name="is_del") //mapper才使用  
	private String isDel; //1-- 删除状态，0 -- 正常
	
	//`update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	@Column(name="update_time") //mapper才使用  
	private String updateTime; //修改时间
	
    //`create_time` datetime DEFAULT NULL,
	@Column(name="create_time") //mapper才使用 
	private String createTime; //创建时间

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public String getSortNum() {
		return sortNum;
	}

	public void setSortNum(String sortNum) {
		this.sortNum = sortNum;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "SysMenuBean [id=" + id + ", parentId=" + parentId
				+ ", menuName=" + menuName + ", menuUrl=" + menuUrl
				+ ", menuIcon=" + menuIcon + ", sortNum=" + sortNum
				+ ", userId=" + userId + ", isDel=" + isDel + ", updateTime="
				+ updateTime + ", createTime=" + createTime + "]";
	}
	
	
}
