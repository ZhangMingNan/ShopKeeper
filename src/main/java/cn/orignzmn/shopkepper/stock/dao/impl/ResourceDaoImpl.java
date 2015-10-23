package cn.orignzmn.shopkepper.stock.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.orignzmn.shopkepper.stock.dao.ResourceDao;
import cn.orignzmn.shopkepper.stock.model.Resource;
@Repository
public class ResourceDaoImpl implements ResourceDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<Resource> findMenus() {
		final String sql = "select id, name, type, url, parent_id as parentId, available,icon from sys_resource where type = 'url' order by num desc";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Resource>(Resource.class));
	}
	
	   @Override
	    public Resource findOne(Long resourceId) {
	        final String sql = "select id, name, type, url, parent_id as parentId, available ,icon from sys_resource where id=?";
	        List<Resource> resourceList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Resource>(Resource.class), resourceId);
	        if(resourceList.size() == 0) {
	            return null;
	        }
	        return resourceList.get(0);
	    }
}
