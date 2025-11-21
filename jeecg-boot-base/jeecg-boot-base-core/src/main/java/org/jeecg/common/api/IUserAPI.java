package org.jeecg.common.api;

import com.alibaba.fastjson.JSONObject;
import org.jeecg.common.system.vo.*;
import org.jeecg.common.system.vo.UserAccountInfo;

import java.util.List;

/**
 * @description 用户服务API
 *
 * 提供所有与用户实体相关的原子服务。
 * 包括根据用户名/ID查询用户、查询用户缓存、分页查询用户列表等功能。
 *
 * @author jeecg-boot
 * @date 2025-11-21
 */
public interface IUserAPI {

    /**
     * 根据用户名获取登录用户信息
     * @param username 用户名
     * @return 登录用户信息
     */
    LoginUser getUserByName(String username);

    /**
     * 根据用户ID获取登录用户信息
     * @param id 用户ID
     * @return 登录用户信息
     */
    LoginUser getUserById(String id);

    /**
     * 根据用户名获取用户ID
     * @param username 用户名
     * @return 用户ID
     */
    String getUserIdByName(String username);

    /**
     * 根据用户名获取用户缓存信息
     * @param username 用户名
     * @return 用户缓存信息
     */
    SysUserCacheInfo getCacheUser(String username);

    /**
     * 查询所有用户返回下拉框
     * @return 用户下拉框
     */
    List<ComboModel> queryAllUserBackCombo();

    /**
     * 分页查询所有用户
     * @param userIds 用户ID（多个逗号分割）
     * @param pageNo 页码
     * @param pageSize 每页条数
     * @return 用户JSON对象
     */
    JSONObject queryAllUser(String userIds, Integer pageNo, Integer pageSize);

    /**
     * 根据用户ID查询用户信息
     * @param userIds 用户ID数组
     * @return 用户账户信息
     */
    List<UserAccountInfo> queryAllUserByIds(String[] userIds);

    /**
     * 根据用户账号查询用户信息
     * @param userNames 用户账号数组
     * @return 用户账户信息
     */
    List<UserAccountInfo> queryUserByNames(String[] userNames);

    /**
     * 通过高级查询获取用户列表
     * @param superQuery 高级查询参数
     * @param matchType 匹配类型
     * @return 用户列表
     */
    List<JSONObject> queryUserBySuperQuery(String superQuery, String matchType);

    /**
     * 根据用户ID查询用户信息
     * @param id 用户ID
     * @return 用户JSON对象
     */
    JSONObject queryUserById(String id);

    /**
     * 根据租户ID查询用户ID
     * @param tenantId 租户ID
     * @return 用户ID列表
     */
    List<String> selectUserIdByTenantId(String tenantId);

    /**
     * 根据用户ID查询所属部门
     * @param userId 用户ID
     * @return 部门ID列表
     */
    List<String> queryDeptUsersByUserId(String userId);

    /**
     * 根据用户账号查询用户信息
     * @param usernames 用户账号（多个逗号分割）
     * @return 用户列表
     */
    List<JSONObject> queryUsersByUsernames(String usernames);

    /**
     * 根据用户ID查询用户信息
     * @param ids 用户ID（多个逗号分割）
     * @return 用户列表
     */
    List<JSONObject> queryUsersByIds(String ids);

    /**
     * 更新用户头像
     * @param loginUser 登录用户
     */
    void updateAvatar(LoginUser loginUser);
}