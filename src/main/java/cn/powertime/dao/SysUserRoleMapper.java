package cn.powertime.dao;

import cn.powertime.entity.SysUserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
    
    int deleteByPrimaryKey(Long id);

   
    int insert(SysUserRole record);

    //查询系统用户角色
    String selectRoleByUserId(String userId);

    int insertSelective(SysUserRole record);

   
    SysUserRole selectByPrimaryKey(Long id);

  
    int updateByPrimaryKeySelective(SysUserRole record);


    int updateByPrimaryKey(SysUserRole record);
}