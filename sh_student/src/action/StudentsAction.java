package action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import service.StudentsDAO;
import service.impl.StudentsDAOImpl;
import entity.Students;

/**
 * Title: 学生action类<br>
 * Description: <br>
 * Copyright: Copyright (c) 2007<br>
 * Company: 北京华宇信息技术有限公司<br>
 * @author Administrator
 * @version 1.0 
 * @date 2016年9月25日
 */
public class StudentsAction extends SuperAction{

    private static final long serialVersionUID = 1L;
    
    /**
     *查询所有学生信息 
     * @return
     */
    public String query(){
        StudentsDAO sDao = new StudentsDAOImpl();
        List<Students> list = sDao.queryAllStudents();
        if(list != null && list.size() > 0){
            session.setAttribute("students_list", list);
        }
        return "Students_query_success";
    }
    
    /**
     * 删除指定学生信息
     * @return
     */
    public String delete(){
        StudentsDAO sDao = new StudentsDAOImpl();
        String sid = request.getParameter("sid");
        sDao.deleteStudents(sid);
        return "delete_success";
    }
    
    /**
     * 新增学生信息
     * @return
     */
    public String add(){
        StudentsDAO sDao = new StudentsDAOImpl();
        Students students = new Students();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            students.setSid(getStudentSid());
            students.setGender(request.getParameter("gender"));
            students.setSname(request.getParameter("sname"));
            students.setBirthday(sdf.parse(request.getParameter("birthday")));
            students.setAddress(request.getParameter("address"));
            sDao.addStudents(students);
        } catch (ParseException e) {
            e.printStackTrace();
            return "add_failure";
        }
        return "add_success";
    }
    
    public String modify(){
        StudentsDAO sDao = new StudentsDAOImpl();
        String sid = request.getParameter("sid");
        Students students = new Students();
        if(!StringUtils.isBlank(sid)){
            students = sDao.queryStudentsBySid(sid); 
        }
        session.setAttribute("modify_students", students);
        return "modify_success";
    }
    
    public String save(){
        Students students = new Students();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        StudentsDAO sDao = new StudentsDAOImpl();
        try {
            students.setSid(request.getParameter("sid"));
            students.setGender(request.getParameter("gender"));
            students.setSname(request.getParameter("sname"));
            students.setBirthday(sdf.parse(request.getParameter("birthday")));
            students.setAddress(request.getParameter("address"));
            sDao.updateStudents(students);
        } catch (Exception e) {
            e.printStackTrace();
            return "save_failure"; 
        }
        return "save_success"; 
    }
    /**
     *获取学生id 
     * @return
     */
    private String getStudentSid(){
        StudentsDAO sDao = new StudentsDAOImpl();
        String sid = sDao.queryMaxSid();
        if(StringUtils.isBlank(sid)){
            sid = "S0000000";
        } else {
            sid = "1" + sid.substring(1);
            sid = String.valueOf(Integer.parseInt(sid) + 1);
            sid = "S" + sid.substring(1);
        }
        return sid;
    }
    
}
