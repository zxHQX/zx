package cn.powertime.controller;


import cn.powertime.utils.PasswordHelper;
import cn.powertime.utils.TwoTuple;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.powertime.common.Action;
import com.powertime.common.BaseAction;
import com.powertime.db.ExecuteSql;
import com.powertime.util.ResponseUtil;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@org.springframework.context.annotation.Scope("prototype")
public class AllController extends BaseAction {


    @RequestMapping("/get.jspx")
    public void get(HttpServletRequest request, HttpServletResponse response, HttpSession session){

        String reguest_id = getValueFromWeb(request);
        try {
            handle(reguest_id, Action.UNKOWN, "selectxml", request, response,
                    null, null);
        }catch (Exception e){
        }
    }
    @RequestMapping("/del.jspx")
    public void del(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        String reguest_id = getValueFromWeb(request);
        try {
            handle(reguest_id, Action.UNKOWN, "deletexml", request, response,
                    null, null);
        }catch (Exception e){
        }
    }
    @RequestMapping("/mod.jspx")
    public void mod(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        String reguest_id = getValueFromWeb(request);
        try {
            handle(reguest_id, Action.UNKOWN, "xml", request, response,
                    null, null);
        }catch (Exception e){
        }
    }
    @RequestMapping("/add.jspx")
    public void add(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        String reguest_id = getValueFromWeb(request);
        try {
            handle(reguest_id, Action.UNKOWN, "insertxml", request, response,
                    null, null);
        }catch (Exception e){
        }
    }
    @RequestMapping("/treeTable.jspx")
//    @Logging(code = "tree.jspx",vars = {"request","request"},type = EnumLogType.ADD)
    public void treeTable(HttpServletRequest request, HttpServletResponse response)throws Exception{
        String reguest_id = getValueFromWeb(request);
        try {
            handle(reguest_id, Action.UNKOWN, "treexml", request, response,
                    null, null);
        }catch (Exception e){
        }
    }
    @RequestMapping("/download.jspx")
    public void download(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        String reguest_id = getValueFromWeb(request);
        try {
            handle(reguest_id, Action.UNKOWN, "downloadxml", request, response,
                    null, null);
        }catch (Exception e){
        }
    }
    @RequestMapping("/upload.jspx")
    public void upload(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        String reguest_id = getValueFromWeb(request);
        try {
            handle(reguest_id, Action.UNKOWN, "uploadxml", request, response,
                   null, null);
        }catch (Exception e){

        }
    }

    /**
     * 添加用户
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/adduser.jspx")
    public void adduser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject json = new JSONObject();
        String reguest_id = getValueFromWeb(request);
        String password = getValueFromWeb(request,"password");
        String account = getValueFromWeb(request,"account");
        if(reguest_id!=null){
            if(password!=null&&!password.equals("")){
                Long id = IdWorker.getId();
                TwoTuple<String, String> tuple = PasswordHelper.builder().build().encrypt(password, account);
                password= tuple.getB();
                String  PwdSalt=tuple.getA();
                String sql="insert into sys_user(id,account,password,pwd_salt) value("+id+",'"+account+"','"+password+"','"+PwdSalt+"')";
                ExecuteSql execute = new ExecuteSql();
                int i=execute.insert(sql);
                if(i>0){
                    json.put("mark",1);
                    json.put("msg","用户创建成功");
                }else{
                    json.put("mark",-1);
                    json.put("msg","用户创建失败");
                }
            }else{
                json.put("mark",-1);
                json.put("msg","密码不能为空");
            }
        }else{
            json.put("mark",-1);
            json.put("msg","错误请求");
        }
        ResponseUtil.renderHtml(response, json.toString());

    }

    /**
     * 判断用户名密码是否正确
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/judgeuser.jspx")
    public void judgeuser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject json = new JSONObject();
        String reguest_id = getValueFromWeb(request);
        String password = getValueFromWeb(request,"password");
        String account = getValueFromWeb(request,"account");
        String pwd=null;
        String pwd_salt=null;
        if(reguest_id!=null) {
            String sql = "select password,pwd_salt  from sys_user where account='" + account + "'";
            ExecuteSql execute = new ExecuteSql();
            List list = execute.findSqlJson(sql);
            if ((list != null) && (list.size() == 1)) {
                Map<String, Object> map = (Map<String, Object>) list
                        .get(0);
                pwd = map.get("password").toString();
                pwd_salt = map.get("pwd_salt").toString();
            } else {
                json.put("mark", -1);
                json.put("msg", "用户名或密码错误");
                ResponseUtil.renderHtml(response, json.toString());
                return;
            }
            password=PasswordHelper.builder().build().encrypt(password, account, pwd_salt);
            if(pwd.equals(password)){
                json.put("mark", 1);
                json.put("msg", "用户名密码正确");
            }else{
                json.put("mark", -1);
                json.put("msg", "用户名或密码错误");
            }
        }else{
            json.put("mark", -1);
            json.put("msg", "错误请求");
        }
        ResponseUtil.renderHtml(response, json.toString());
    }

    /**
     * 修改密码
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/changepwd.jspx")
    public void changepwd(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject json = new JSONObject();
        String reguest_id = getValueFromWeb(request);
        String password = getValueFromWeb(request,"password");
        String id = getValueFromWeb(request,"id");
        String pwd=null;
        String pwd_salt=null;
        String account=null;
        if(reguest_id!=null) {
            String sql = "select password,pwd_salt,account  from sys_user where id='" + id + "'";
            ExecuteSql execute = new ExecuteSql();
            List list = execute.findSqlJson(sql);
            if ((list != null) && (list.size() == 1)) {
                Map<String, Object> map = (Map<String, Object>) list
                        .get(0);
                pwd = map.get("password").toString();
                pwd_salt = map.get("pwd_salt").toString();
                account=map.get("account").toString();
            } else {
                json.put("mark", -1);
                json.put("msg", "参数错误");
            }
            password=PasswordHelper.builder().build().encrypt(password, account, pwd_salt);
            if(pwd.equals(password)){
                json.put("mark", -1);
                json.put("msg", "新密码和旧密码相同");
            }else{
                TwoTuple<String, String> tuple = PasswordHelper.builder().build().encrypt(password, account);
                password= tuple.getB();
                String  PwdSalt=tuple.getA();
                sql="update sys_user set password='"+password+"',pwd_salt='"+pwd_salt+"' where id="+id;
                int i=execute.update(sql);
                if(i>0){
                    json.put("mark",1);
                    json.put("msg","密码修改成功");
                }else{
                    json.put("mark",-1);
                    json.put("msg","密码修改失败");
                }
            }
        }else{
            json.put("mark", -1);
            json.put("msg", "错误请求");
        }
        ResponseUtil.renderHtml(response, json.toString());

    }

}
