package com.t239.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t239.dao.UserMapper;
import com.t239.entity.User;
import com.t239.service.UserService;
import com.t239.util.Page;
@Service("userService")
public class UserServiceImpl implements UserService {
	@Resource//按类型自动匹配
	private UserMapper userMapper;

	@Override
	public Page getUserByPage(HashMap<String, Object> hash) {
		//统计数量
		Integer totalCount=userMapper.getUserCount(hash);
		//创建Page对象
		Page page=new Page(hash.get("curpage"), hash.get("pagesize"), totalCount);
		hash.put("startrow", page.getStartrow());
		hash.put("pagesize", page.getPageSize());
		//执行查询
		List<User> list=userMapper.getUserByPage(hash);
		//保存数据page
		page.setList(list);
		return page;
	}

	public UserMapper getUserMapper() {
		return userMapper;
	}

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Integer insertUser(User user) {
		Integer result=userMapper.insertUser(user);
		return result;
	}

	@Override
	public User login(String code,String pwd) {
		User user= userMapper.getUserByCodeAndPwd(code, pwd);
		return user;
	}

	@Override
	public User getUserById(Integer id) {
		return userMapper.getUserById(id);
	}

	@Override
	public Integer updateUser(User user) {
		return userMapper.updateUser(user);
	}

	@Override
	public User getUserByCode(String code) {
		// TODO Auto-generated method stub
		return userMapper.getUserByCode(code);
	}

}
