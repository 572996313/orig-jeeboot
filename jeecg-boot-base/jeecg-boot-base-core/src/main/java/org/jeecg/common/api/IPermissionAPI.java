package org.jeecg.common.api;

import com.alibaba.fastjson.JSONObject;
import org.jeecg.common.api.dto.OnlineAuthDTO;
import org.jeecg.common.system.vo.*;

import java.util.List;
import java.util.Set;

/**
 * @description 权限服务API
 *
 * 提供与用户权限、角色和数据规则相关的原子服务。
 * 包括查询用户角色、权限集合、数据权限规则等功能。
 *
 * @author jeecg-boot
 * @date 2025-11-21
 */
public interface IPermissionAPI {

    /**
     * 根据用户账号查询角色集合
     * @param username 用户账号
     * @return 角色集合
     */
    Set<String> queryUserRoles(String username);

    /**
     * 根据用户ID查询角色集合
     * @param userId 用户ID
     * @return 角色集合
     */
    Set<String> queryUserRolesById(String userId);

    /**
     * 根据用户ID查询权限集合
     * @param userId 用户ID
     * @return 权限集合
     */
    Set<String> queryUserAuths(String userId);

    /**
     * 查询用户数据权限
     * @param component 组件
     * @param requestPath 请求路径
     * @param username 用户账号
     * @return 数据权限规则
     */
    List<SysPermissionDataRuleModel> queryPermissionDataRule(String component, String requestPath, String username);

    /**
     * 根据用户账号查询角色
     * @param username 用户账号
     * @return 角色列表
     */
    List<String> getRolesByUsername(String username);
    
    /**
     * 根据用户ID查询角色
     * @param userId 用户ID
     * @return 角色列表
     */
    List<String> getRolesByUserId(String userId);

    /**
     * 查询所有角色
     * @return 角色下拉框
     */
    List<ComboModel> queryAllRole();

    /**
     * 根据角色ID查询所有角色
     * @param roleIds 角色ID数组
     * @return 角色下拉框
     */
    List<ComboModel> queryAllRole(String[] roleIds);

    /**
     * 根据用户账号查询角色ID
     * @param username 用户账号
     * @return 角色ID列表
     */
    List<String> getRoleIdsByUsername(String username);

    /**
     * 通过高级查询获取角色列表
     * @param superQuery 高级查询参数
     * @param matchType 匹配类型
     * @return 角色列表
     */
    List<JSONObject> queryRoleBySuperQuery(String superQuery, String matchType);

    /**
     * 根据用户账号查询角色集合
     * @param username 用户账号
     * @return 角色集合
     */
    Set<String> getUserRoleSet(String username);

    /**
     * 根据用户ID查询角色集合
     * @param useId 用户ID
     * @return 角色集合
     */
    Set<String> getUserRoleSetById(String useId);

    /**
     * 根据用户ID查询权限集合
     * @param userId 用户ID
     * @return 权限集合
     */
    Set<String> getUserPermissionSet(String userId);

    /**
     * 判断用户是否有Online某个表单的权限
     * @param onlineAuthDTO Online权限DTO
     * @return 是否有权限
     */
    boolean hasOnlineAuth(OnlineAuthDTO onlineAuthDTO);

    /**
     * 根据角色ID查询角色编码
     * @param id 角色ID
     * @return 角色编码
     */
    String getRoleCodeById(String id);
    
    /**
     * 根据角色编码查询角色字典
     * @param roleCodes 角色编码（多个逗号分割）
     * @return 角色字典
     */
    List<DictModel> queryRoleDictByCode(String roleCodes);

    /**
     * 根据角色编码查询用户ID
     * @param roleCodes 角色编码列表
     * @return 用户ID列表
     */
    List<String> queryUserIdsByRoleds(List<String> roleCodes);
}