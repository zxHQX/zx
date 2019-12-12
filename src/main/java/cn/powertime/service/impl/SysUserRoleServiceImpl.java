package cn.powertime.service.impl;

import cn.powertime.dao.SysUserRoleMapper;
import cn.powertime.entity.SysUserRole;
import cn.powertime.service.SysUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author zx
 * @since 2019-11-14
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    @Autowired
    SysUserRoleMapper sysUserRoleMapper;
    public String selectRoleByUserId(String userId) {
        String roleName = null;
        try {
            roleName = sysUserRoleMapper.selectRoleByUserId(userId);
            return roleName;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
