package service;

import java.util.List;

import entity.Students;

/**
 * Title: 学生类接口<br>
 * Description: <br>
 * Copyright: Copyright (c) 2007<br>
 * Company: 北京华宇信息技术有限公司<br>
 * @author Administrator
 * @version 1.0 
 * @date 2016年9月24日
 */
public interface StudentsDAO {
    /**
     *查询所有学生 
     * @return
     */
    public List<Students> queryAllStudents();
    
    /**
     * 根据sid查询学生
     * @param sid
     * @return
     */
    public Students queryStudentsBySid(String sid);
    
    /**
     * 添加学生信息
     * @param s
     * @return
     */
    public boolean addStudents(Students s);
    
    /**
     * 更新学生信息
     * @param s
     * @return
     */
    public boolean updateStudents(Students s);
    
    /**
     * 删除学生信息
     * @param sid
     * @return
     */
    public boolean deleteStudents(String sid);
    
    /**
     * 获取数据库中Students最大的sid值
     * @return
     */
    public String queryMaxSid();
}
