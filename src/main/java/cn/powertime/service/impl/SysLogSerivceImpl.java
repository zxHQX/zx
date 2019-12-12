package cn.powertime.service.impl;

import cn.powertime.dao.SysLogMapper;
import cn.powertime.dao.SysUserRoleMapper;
import cn.powertime.entity.SysLog;
import cn.powertime.service.SysLogService;
import cn.powertime.utils.GetCurrentTimeUtil;
import cn.powertime.utils.excel.EasyExcelUtils;
import cn.powertime.vo.LogExcelExportVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author yyd
 * @version V1.0
 * @Package com.powertime.audiltor.service.impl
 * @date 2019/10/28 14:13
 */
@Service
public class SysLogSerivceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {


    @Autowired
    SysLogMapper sysLogMapper;

    @Autowired
    SysUserRoleMapper sysUserRoleMapper;

    public List<LogExcelExportVo> list1(Integer page, Integer size, String start, String end, Integer type, String userId) {
        com.powertime.audiltor.entity.SysUser sysUser = null;

        if (start == null || "".equals(start)) {
            start = "1998.02.28";
        }
        if (end == null || "".equals(end)) {
            Date date = GetCurrentTimeUtil.getCurrentTime();
            //在原有天数上加一天
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.DATE, 1);
            date = c.getTime();
            //以下是将时间转换成String类型并返回Integer  page,Integer  size
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
            end = dateFormat.format(date);//得到网络的时间
        }


        //如果sysUser是日志管理员 ，可以查看所有人的日志
        PageHelper.startPage(page, size);
        return sysLogMapper.list1(start, end, userId,type);
        //如果sysUser不是日志管理员，只可以查看自己操作产生的日志
    }

    @Override
    public SysLog details(String id) {
        Long lId = Long.parseLong(id);
        return sysLogMapper.details(lId);
    }


    public Boolean exportExcel(HttpServletResponse response, String start, String end, String userId) {


        if (start == null || "".equals(start)) {
            start = "1998.02.28";
        }
        if (end == null || "".equals(end)) {
            Date date = GetCurrentTimeUtil.getCurrentTime();
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.DATE, 1);
            date = c.getTime();
            //以下是将时间转换成String类型并返回Integer  page,Integer  size
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
            end = dateFormat.format(date);//得到网络的时间
        }

        List<LogExcelExportVo> sysLogs = sysLogMapper.list1(start, end, userId,null);


        try {
            EasyExcelUtils.writeExcel(response,sysLogs,"logExport","logExport",LogExcelExportVo.class);

            //导出excel之后设置 日志为 已备份
            sysLogMapper.setBackup(start,end);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean deleteByDate(String date,String userId) {

        Long user_id = null;

        Date d = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd");
        try {
            d = df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(Calendar.DATE, 1);
        d = c.getTime();

        date = df.format(d);


        int size = sysLogMapper.deleteByDate(date, null);
        if (size > 0) {
            return true;
        } else {
            return false;
        }
    }


}
