package im.ky.fy.pojo;

import java.util.List;

public class PageBean<T> {

	 private long total;// 记录总数  
	 private List<T> rows; // 记录集合  
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	 
	 
}
