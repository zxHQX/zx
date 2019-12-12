package cn.powertime.service;

import cn.powertime.entity.SysUserRole;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户角色表 服务类
 * </p>
 *
 * @author ZYW
 * @since 2019-04-16
 */
public interface SysUserRoleService extends IService<SysUserRole> {
    String selectRoleByUserId(String userId);
}
