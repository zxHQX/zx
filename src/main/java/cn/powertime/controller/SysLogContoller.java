package cn.powertime.controller;

import cn.powertime.entity.SysUser;
import cn.powertime.service.SysLogService;
import cn.powertime.service.SysUserRoleService;
import cn.powertime.utils.PropertiesUtils_;
import cn.powertime.utils.excel.BaseController;
import cn.powertime.vo.LogExcelExportVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * @author zx
 * @version V1.0
 * @Package com.powertime.audiltor.controller
 * @date 2019/10/26 11:11
 */

@RestController
@RequestMapping("/sysLog")
public class SysLogContoller extends BaseController {


    @Autowired
    SysLogService sysLogService;

    @Autowired
    SysUserRoleService sysUserRoleService;

    //查询日志并分页
    @PostMapping("/list")
    public Object list(HttpServletRequest request, Integer  page, Integer  size, String start, String end, Integer type){

        HttpSession session = request.getSession();
        SysUser sysUser = (SysUser) session.getAttribute("user");
        String userId = sysUser.getId();

        if(!"".equals(userId)&&userId!=null) {
            String roleName = sysUserRoleService.selectRoleByUserId(userId);

            //判断是否是日志管理员
            if (roleName.equals("日志管理员")) {
                //查询全部日志
                userId = null;
            }else{
                //查询自己的日志
            }
        }else{
            //没有传入userId
            return error("请重新登录");
        }

        List<LogExcelExportVo> lists = sysLogService.list1(page,size,start,end,type,userId);

        PageInfo pageInfo = new PageInfo(lists);
        if(lists == null){
            return error("查询失败");
        }else{
            return success1(lists,pageInfo.getPages(),pageInfo.getPageSize(),pageInfo.getTotal());
        }
    }

    //日志导出excel
    @GetMapping(value = "/exportExcel")
    public Object exports(HttpServletRequest request, HttpServletResponse response, String start, String end) throws IOException {

        HttpSession session = request.getSession();
        SysUser sysUser = (SysUser) session.getAttribute("user");
        String userId = sysUser.getId();

        if(!"".equals(userId)&&userId!=null) {
            String roleName = sysUserRoleService.selectRoleByUserId(userId);

            //判断是否是超级管理员（日志管理员）
            if (roleName != null && roleName.equals("日志管理员")) {
                //查询全部日志
                userId = null;
            }else{
                //查询自己的日志
            }
        }else{
            //没有传入userId
            return error("请重新登录");
        }


        Boolean bl = sysLogService.exportExcel(response,start,end,userId);
        if(bl){
            return success("导出成功");
        }else{
            return error("导出失败");
        }
    }

    //按日期批量删除
    @PostMapping("/delete")
    public Object deleteBatch(HttpServletRequest request, String date) {

        HttpSession session = request.getSession();
        SysUser sysUser = (SysUser) session.getAttribute("user");
        String userId = sysUser.getId();

        if(!"".equals(userId)&&userId!=null) {
            String roleName = sysUserRoleService.selectRoleByUserId(userId);

            //判断是否是超级管理员（日志管理员）
            if (roleName.equals("日志管理员")) {
                Boolean bl = sysLogService.deleteByDate(date,userId);
                if(bl){
                    return success("删除成功");
                }else{
                    return error("在"+date+"之前没啥可删的了");
                }
            }else{
                return error("您的权限不足");
            }
        }else{
            //没有拿到用户ID
            return error("请重新登录");
        }
    }

    //获取日志保护时长 单位月
    @PostMapping("/getLogLiveToTime")
    public Object getLogLiveToTime(){

        PropertiesUtils_ propertiesUtil = new PropertiesUtils_();
        Integer liveToTime = null;
       try {
           Properties properties = propertiesUtil.getProperties("config/logTimeToLiveConfig.properties");

           String len = properties.getProperty("logTimeToLive");
           liveToTime = Integer.parseInt(len);
       }catch (Exception e){
           e.printStackTrace();
           return error("获取失败");
       }
       if(liveToTime != null && liveToTime > 0){
           return success(liveToTime,"");
       }else{
           //配置不合法 默认6个月
            return success(6,"");
       }
    }


}
