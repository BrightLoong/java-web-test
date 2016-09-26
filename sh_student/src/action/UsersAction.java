package action;

import org.apache.struts2.interceptor.validation.SkipValidation;

import service.UsersDAO;
import service.impl.UsersDAOImpl;

import com.opensymphony.xwork2.ModelDriven;

import entity.Users;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright (c) 2007<br>
 * Company: 北京华宇信息技术有限公司<br>
 * @author Administrator
 * @version 1.0 
 * @date 2016年9月23日
 */
public class UsersAction extends SuperAction implements ModelDriven<Users> {
    private static final long serialVersionUID = 1L;
    private Users user = new Users();
    
    public String login(){
        UsersDAO uDao = new UsersDAOImpl();
        if(uDao.usersLogin(user)){
            //在session中保存用戶登录成功信息
            session.setAttribute("loginUserName", user.getUsername());
            return "login_success";
        } else {
            return "login_failure";
        }
    }
    
    @SkipValidation
    public String logout(){
        if(session.getAttribute("loginUserName")!=null){
            session.removeAttribute("loginUserName");
        }
        return "logout_success";
    }
    
    
    
    @Override
    public void validate() {
        if("".equals(user.getUsername().trim())){
            this.addFieldError("usernameErro", "用户名不能为空!");
        }
        if(user.getPassword().length() < 6){
            this.addFieldError("passwordErro", "密码长度少于6位!");
        }
    }

    @Override
    public Users getModel() { 
        return this.user;
    }

}
