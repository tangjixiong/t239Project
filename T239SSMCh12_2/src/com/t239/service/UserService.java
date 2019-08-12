package com.t239.service;

import java.util.HashMap;

import com.t239.entity.User;
import com.t239.util.Page;

public interface UserService {
	//分页查询用户信息
	public Page getUserByPage(HashMap<String, Object> hash);
	public Integer insertUser(User user);
	public User login(String code,String pwd);
	//根据用户id查询信息
	public User getUserById(Integer id);
	//修改用户信息
	public Integer updateUser(User user);
	//根据code查询用户信息
	public User getUserByCode(String code);


}
