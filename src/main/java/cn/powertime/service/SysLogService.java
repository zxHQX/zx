package cn.powertime.service;

import cn.powertime.entity.SysLog;
import cn.powertime.vo.LogExcelExportVo;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author zx
 * @version V1.0
 * @Package com.powertime.audiltor.service
 * @date 2019/10/26 11:06
 */
public interface SysLogService extends IService<SysLog>{

    //查取日志信息并分页
    List<LogExcelExportVo> list1(Integer page, Integer size, String start, String end, Integer type, String userId);

    //日志详情
    SysLog details(String id);

    //导出excel
    Boolean exportExcel(HttpServletResponse response, String start, String end, String userId);

    //删除date之前的日志
    Boolean deleteByDate(String date, String userId);

}
