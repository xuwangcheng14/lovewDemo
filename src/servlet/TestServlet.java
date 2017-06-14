package servlet;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import bean.User;
import dao.UserDao;
import xuwangcheng.love.w.servlet.AbstractHttpServlet;
import xuwangcheng.love.w.servlet.annotation.ExecuteRequest;
import xuwangcheng.love.w.servlet.annotation.InjectDao;
import xuwangcheng.love.w.servlet.annotation.RequestBody;
import xuwangcheng.love.w.util.Constants;

public class TestServlet extends AbstractHttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TestServlet() {
		// TODO Auto-generated constructor stub
		super.setServlet(this);
	}
	@InjectDao
	private UserDao userDao;
	
	@ExecuteRequest
	public void getName(Map<String, Object> ajaxMap, HttpServletRequest request, @RequestBody("name") String name) {
		
		if (StringUtils.isBlank(name)) {
			ajaxMap.put("msg", "参数不正确");
			ajaxMap.put("returnCode", 1);
			return;
		}
		
		ajaxMap.put("msg", "Test:Hello " + name);
		ajaxMap.put("returnCode", 0);
	}
	
	@ExecuteRequest("save")
	public void insertUser(Map<String, Object> ajaxMap, HttpServletRequest request, @RequestBody("name") String name
			,@RequestBody("city") String city, @RequestBody("age") int age) throws Exception {
		User user = new User(name, city, age);
		int ret = userDao.save(user);
		ajaxMap.put(Constants.RETURN_CODE_ATTRIBUTE_NAME, Constants.CORRECT_RETURN_CODE);
		ajaxMap.put(Constants.RETURN_MSG_ATTRIBUTE_NAME, ret);
	}
	
	@ExecuteRequest
	public void get(Map<String, Object> ajaxMap, HttpServletRequest request, @RequestBody("name") String name) throws Exception {
		ajaxMap.put(Constants.RETURN_CODE_ATTRIBUTE_NAME, Constants.CORRECT_RETURN_CODE);
		ajaxMap.put("user", userDao.get(name));
	}
	
	/*****************************************************************************/
	public UserDao getUserDao() {
		return userDao;
	}
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
}
