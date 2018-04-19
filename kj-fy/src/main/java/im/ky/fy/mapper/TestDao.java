package im.ky.fy.mapper;

import im.ky.fy.pojo.Test;

import java.util.List;
import java.util.Map;

public interface TestDao { 
 /**
  * 返回HashMap数据
  * @return
  */
 List<Map<Object,Object>> queryTestMap();
 /**
  * 返回实体数据
  * @return
  */
 List<Test> queryTestPojo();
}
