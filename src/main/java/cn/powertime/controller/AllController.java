package cn.powertime.controller;



import com.powertime.common.Action;
import com.powertime.common.BaseAction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
}
