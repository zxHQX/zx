package cn.powertime.dao;


import cn.powertime.entity.SysLog;
import cn.powertime.vo.LogExcelExportVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysLogMapper extends BaseMapper<SysLog> {

    /**
     * 查询所有日志
     * @return
     */
    List<LogExcelExportVo> list1(@Param("start") String start, @Param("end") String end, @Param("user_id") String user_id, @Param("type") Integer type);

    /**
     * 查询单条日志详情
     * @param id
     * @return
     */
    SysLog details(@Param("user_id") Long user_id);

    /**
     * 删除截止到xxx的日志
     * @param date
     * @param userId
     */
    int deleteByDate(@Param("date") String date, @Param("userId") Long userId);

    /**
     * 保存日志
     * @param sysLog
     */
    void save(SysLog sysLog);


    //设置日志为已备份
    void setBackup(@Param("start") String start, @Param("end") String end);
}