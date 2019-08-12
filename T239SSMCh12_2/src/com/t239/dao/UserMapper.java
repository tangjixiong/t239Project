package com.t239.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.t239.entity.Role;
import com.t239.entity.User;

public interface UserMapper {
	//统计用户数量
	public Integer countUser();
	//查询所有用户
	public List<User> getAllUser();
	//根据用户id查询信息
	public User getUserById(Integer id);
	//按姓名模糊查询
	public List<User> getUserByName(HashMap<String, Object> hash);
	//新增
	public Integer insertUser(User user);
	//修改
	public Integer updateUser(User user);
	//删除
	public Integer deleteUser(Integer id);
	//根据角色id查询角色信息
	public Role getRoleById(Integer rid);
	//查询指定范围roleid的用户信息
	public List<User> getUserByRoleIds(Integer [] rids);
	//查询指定范围roleid的用户信息
	public List<User> getUserByRoleIds2(List<Integer> ridlist);
	//查询指定范围roleid的用户信息
	public List<User> getUserByRoleIds3(HashMap<String, Object> hash);
	//使用choose查询
	public List<User> getUserByChoose(HashMap<String, Object> hash);
	
	//统计数据总条数
	public Integer getUserCount(HashMap<String, Object> hash);
	//查询指定页码的数据
	public List<User> getUserByPage(HashMap<String, Object> hash);
	
	//根据用户名和密码查询信息
	public User getUserByCodeAndPwd(@Param("code")String code,@Param("pwd")String pwd);
	public User getUserByCode(String code);
}
