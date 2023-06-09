package edu.wtbu.service;

import java.util.HashMap;
import java.util.List;

import edu.wtbu.dao.UsersDao;
import edu.wtbu.pojo.Page;
import edu.wtbu.pojo.Result;

public class UsersService {
		public static Boolean findByEmail(String email) {
			List<HashMap<String,Object>> list = UsersDao.findByEmail(email);
			if(list!=null&&list.size()>0) {
				return true;
			}else {
				return false;
			}
		}
		public static Result findByUserId(int userId) {
			Result result = new Result("fail",null,null);
			HashMap<String,Object> user = UsersDao.findByUserId(userId);
			if(user!=null) {
				result.setFlag("success");
				result.setData(user);
			}else {
				result.setFlag("用户信息不存在");
			}
			return result;
		}
		public static Boolean findByEmailAndPassword(String email,int userId) {
			List<HashMap<String,Object>> list = UsersDao.findByEmailAndUserId(email, userId);
			if(list!=null&&list.size()>0) {
				return true;
			}else {
				return false;
			}
		}
		public static Result updateUser(HashMap<String,Object> map) {
			Result result = new Result("fail",null,null);
			int userId = Integer.parseInt(map.get("userId").toString());
			HashMap<String,Object> userInfo = UsersDao.findByUserId(userId);
			if(userInfo==null) {
				result.setData("用户信息不存在");
				return result;
			}
			Boolean isEmail = UsersService.findByEmailAndPassword(map.get("email").toString(), userId);
			if(isEmail) {
				result.setData("邮箱重复");
				return result;
			}
			int updateResult = UsersDao.updateUser(map);
			if(updateResult>0) {
				result.setFlag("success");
			}
			return result;
		}
		public static Result login(String email,String password) {
			Result result = new Result("fail",null,null);
			HashMap<String, Object> user = UsersDao.findByEmailAndPassWord(email, password);
			if(user!=null) {
				result.setFlag("success");
				HashMap<String, Object> loginInfo = new HashMap<String, Object>();
				loginInfo.put("Email", user.get("Email"));
				loginInfo.put("RoleId", user.get("RoleId"));
				result.setData(loginInfo);
			}else {
				Boolean isEmail = UsersService.findByEmail(email);
				if(isEmail) {
					result.setData("密码错误");
				}else {
					result.setData("邮箱不存在");
				}
			}
			return result;
		}
		public static Result addUser(HashMap<String, Object> map) {
			Result result = new Result("fail", null, null);
			Boolean isEmail = UsersService.findByEmail(map.get("email").toString());
			if(isEmail) {
				result.setData("邮箱重复");
				return result;
			}
			int addResult = UsersDao.addUser(map);
			if(addResult>0) {
				result.setFlag("success");
			}
			return result;
		}
		public static Result userList(String name,int roleId,int pageSize,int startPage) {
			List<HashMap<String,Object>> list = null;
			int total=0;
			if(roleId==0) {
				list = UsersDao.findUserListByPage(name, startPage, pageSize);
				total = UsersDao.findUserCount(name);
			}else {
				list = UsersDao.findUserListByPageAndRoleId(name, startPage, pageSize, roleId);
				
				total = UsersDao.findUserCountAndRoleId(name, roleId);
			}
			Page page = new Page(total, pageSize, startPage); 
			Result result = new Result("success", list, page);
			return result;
		}
}
