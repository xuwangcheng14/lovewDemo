package dao;

import bean.User;
import mapper.UserMapper;

public class UserDao {
	
	/**
	 * 创建表
	 * @throws Exception 
	 */
	public void createTable() throws Exception {
		String sql = "create table if not exists User (name text,city text,age integer)";
		
		UserMapper mapper = new UserMapper();
		try {
			mapper.execSQL(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
	
	public User get(String name) throws Exception {
		String sql = "select * from user where name=?";
		UserMapper mapper = new UserMapper();
		
		mapper.execSQL(sql, name);
		
		return mapper.getObject();
	}
	
	public int save(User user) throws Exception {
		String sql = "insert into user values(?,?,?)";
		UserMapper mapper = new UserMapper();
		mapper.execSQL(sql, user.getName(), user.getCity(), user.getAge());
		
		return mapper.getEffectRowCount();
		
	}
}	
