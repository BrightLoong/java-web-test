package service;

import entity.Users;

/**
 * Title: 用户业务逻辑接口<br>
 * Description: <br>
 * Copyright: Copyright (c) 2007<br>
 * Company: 北京华宇信息技术有限公司<br>
 * @author Administrator
 * @version 1.0 
 * @date 2016年9月22日
 */
public interface UsersDAO {
    /**
     * 用户登录
     * @param u
     * @return
     */
    public boolean usersLogin(Users u);
}
