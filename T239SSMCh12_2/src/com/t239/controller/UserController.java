package com.t239.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.t239.entity.User;
import com.t239.service.UserService;
import com.t239.util.Page;

@Controller
@RequestMapping("/sys/user")
public class UserController extends BaseController{
	@Resource(name="userService")
	private UserService userService;

	//分页查询用户信息
	@RequestMapping("/list.html")
	public String getUserByPage(Integer pageIndex,String queryname,String roleId,Model model){
		HashMap<String, Object> hash=new HashMap<>();
		hash.put("curpage", pageIndex);
		hash.put("pagesize", 3);
		hash.put("name", queryname);
		hash.put("roleId", roleId);
		Page page=userService.getUserByPage(hash);
		model.addAttribute("page", page);
		return "userlist";
	}

	//局部异常处理
	/*@ExceptionHandler(value={RuntimeException.class})
	public String handleException(HttpServletRequest req,RuntimeException e){
		req.setAttribute("e", e);
		return "error";
	}*/


	@RequestMapping("/frame.html")
	public String toindex(){
		return "frame";
	}
	//跳转到新增界面
	@RequestMapping(value="/add.html",method=RequestMethod.GET)
	public String toadduser(@ModelAttribute("user")User user){
		return "useradd";
	}
	/*//新增用户信息:单文件上传
	@RequestMapping(value="/add.html",method=RequestMethod.POST)
	//@Valid:让实体类中的验证规则生效
	public String addUser(@Valid User user,BindingResult br,
			@RequestParam(value ="attachs", required = false)MultipartFile attach,
			HttpServletRequest request){
		if(br.hasErrors()){
			return "useradd";
		}
		String idPicPath = null;
		//判断文件是否为空
		if(!attach.isEmpty()){
			String path = request.getSession().getServletContext().getRealPath("static"+File.separator+"uploadfiles"); 
			String oldFileName = attach.getOriginalFilename();//原文件名
			String prefix=FilenameUtils.getExtension(oldFileName);//原文件后缀     
			int filesize = 600000;
	        if(attach.getSize() >  filesize){//上传大小不得超过 5000k
            	request.setAttribute("uploadFileError", " * 上传大小不得超过 500k");
	        	return "useradd";
            }else if(prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png") 
            		|| prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("pneg")){//上传图片格式不正确
            	//生成新的文件名
            	String fileName = System.currentTimeMillis()+RandomUtils.nextInt(1000000)+"_Personal."+prefix;  
                File targetFile = new File(path, fileName);  
                if(!targetFile.exists()){  
                    targetFile.mkdirs();  
                }  

                try {  
                	//保存  
                	attach.transferTo(targetFile);  
                } catch (Exception e) {  
                    e.printStackTrace();  
                    request.setAttribute("uploadFileError", " * 上传失败！");
                    return "useradd";
                }  
                idPicPath = path+File.separator+fileName;
            }else{
            	request.setAttribute("uploadFileError", " * 上传图片格式不正确");
            	return "useradd";
            }
		}
		System.out.println(user.getBirthday());
		user.setIdPicPath(idPicPath);
		Integer result= userService.insertUser(user);
		if(result>0){
			return "redirect:/user/list.html";
		}else{
			return "redirect:/user/add.html";
		}
	}*/

	@RequestMapping(value="/add.html",method=RequestMethod.POST)
	public String addUser(User user){	
		Integer result= userService.insertUser(user);
		if(result>0){
			return "redirect:/sys/user/list.html";
		}else{
			return "redirect:/sys/user/add.html";
		}
	}

	//进入修改页面
	@RequestMapping(value="/modify",method=RequestMethod.GET)
	public String toupdate(Integer id,Model model){
		User user=userService.getUserById(id);
		model.addAttribute("user", user);
		return "usermodify";
	}
	//修改用户信息
	@RequestMapping(value="/modify",method=RequestMethod.POST)
	public String updateUser(User user,BindingResult br){
		System.out.println(user.getBirthday());
		Integer result= userService.updateUser(user);
		if(result>0){
			return "redirect:/sys/user/list.html";
		}else{
			return "redirect:/sys/user/modify";
		}
	}

	//进入查看页面
	/*	@RequestMapping(value="/view/{id}",method=RequestMethod.GET)
	public String toview(@PathVariable Integer id,Model model){
		System.out.println("用户编码："+id);
		User user=userService.getUserById(id);
		model.addAttribute("user", user);
		return "userview";
	}*/
	//根据id查询用户信息，返回json格式数据
	@RequestMapping(value="/view.json")/*,produces={"application/json;charset=UTF-8"}*/
	@ResponseBody
	public Object view(Integer id){
		try {
			User user=userService.getUserById(id);
			if(user==null){
				return "nodata";
			}else{
				/*return JSON.toJSONString(user);*/
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "failed";
		}
	}
	//检查userCode是否存在
	@RequestMapping("/checkUserCode")
	@ResponseBody//作用类似于out，把返回的结果，打印给客户端
	public String checkUserCode(String userCode){
		HashMap<String, Object> hash=new HashMap<>();

		User user=userService.getUserByCode(userCode);
		if(user!=null){
			hash.put("userCode", "exist");
		}else{
			hash.put("userCode", "noexist");
		}
		return JSONArray.toJSONString(hash);
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
