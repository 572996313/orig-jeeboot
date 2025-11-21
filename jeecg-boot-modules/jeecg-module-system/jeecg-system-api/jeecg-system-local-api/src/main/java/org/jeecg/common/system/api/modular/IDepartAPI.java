package org.jeecg.common.system.api.modular;

import com.alibaba.fastjson.JSONObject;
import org.jeecg.common.system.vo.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @description 部门服务API
 *
 * 提供与部门和组织结构相关的原子服务。
 * 包括查询用户的所属部门、查询部门层级、获取部门负责人等功能。
 *
 * @author jeecg-boot
 * @date 2025-11-21
 */
public interface IDepartAPI {

    /**
     * 根据用户账号查询部门ID
     * @param username 用户账号
     * @return 部门ID
     */
    List<String> getDepartIdsByUsername(String username);

    /**
     * 根据用户ID查询部门ID
     * @param userId 用户ID
     * @return 部门ID
     */
    List<String> getDepartIdsByUserId(String userId);

    /**
     * 根据用户账号查询父级部门ID
     * @param username 用户账号
     * @return 父级部门ID
     */
    Set<String> getDepartParentIdsByUsername(String username);

    /**
     * 根据部门ID查询父级部门ID
     * @param depIds 部门ID
     * @return 父级部门ID
     */
    Set<String> getDepartParentIdsByDepIds(Set<String> depIds);

    /**
     * 根据用户账号查询部门名称
     * @param username 用户账号
     * @return 部门名称
     */
    List<String> getDepartNamesByUsername(String username);

    /**
     * 查询所有部门的下拉框
     * @return 部门下拉框
     */
    List<DictModel> queryAllDepartBackDictModel();

    /**
     * 根据机构编码查询部门ID
     * @param orgCode 机构编码
     * @return 部门ID
     */
    String getDepartIdsByOrgCode(String orgCode);

    /**
     * 查询所有部门
     * @return 所有部门
     */
    List<SysDepartModel> getAllSysDepart();

    /**
     * 根据部门ID查询父级部门
     * @param departId 部门ID
     * @return 父级部门
     */
    DictModel getParentDepartId(String departId);

    /**
     * 根据部门ID查询部门负责人
     * @param deptId 部门ID
     * @return 部门负责人
     */
    List<String> getDeptHeadByDepId(String deptId);

    /**
     * 通过高级查询获取部门列表
     * @param superQuery 高级查询参数
     * @param matchType 匹配类型
     * @return 部门列表
     */
    List<JSONObject> queryDeptBySuperQuery(String superQuery, String matchType);

    /**
     * 根据ID查询所有部门
     * @param id 部门ID
     * @return 部门
     */
    SysDepartModel selectAllById(String id);

    /**
     * 根据机构编码查询部门
     * @param orgCodes 机构编码（多个逗号分割）
     * @return 部门
     */
    List<JSONObject> queryDepartsByOrgcodes(String orgCodes);

    /**
     * 根据部门ID查询部门
     * @param ids 部门ID（多个逗号分割）
     * @return 部门
     */
    List<JSONObject> queryDepartsByIds(String ids);

    /**
     * 根据机构编码查询部门用户
     * @param orgCode 机构编码
     * @return 部门用户
     */
    List<Map> getDeptUserByOrgCode(String orgCode);

    /**
     * 根据部门ID查询用户ID
     * @param deptIds 部门ID
     * @return 用户ID
     */
    List<String> queryUserIdsByDeptIds(List<String> deptIds);

    /**
     * 根据部门ID级联查询用户ID
     * @param deptIds 部门ID
     * @return 用户ID
     */
    List<String> queryUserIdsByCascadeDeptIds(List<String> deptIds);

    /**
     * 根据部门ID查询用户账号
     * @param deptIds 部门ID
     * @return 用户账号
     */
    List<String> queryUserAccountsByDeptIds(List<String> deptIds);

    /**
     * 根据职位ID查询用户ID
     * @param positionIds 职位ID
     * @return 用户ID
     */
    List<String> queryUserIdsByPositionIds(List<String> positionIds);

    /**
     * 根据部门编码查询用户账号
     * @param orgCode 部门编码
     * @return 用户账号
     */
    List<String> getUserAccountsByDepCode(String orgCode);

    /**
     * 根据机构编码查询公司
     * @param orgCode 机构编码
     * @return 公司
     */
    SysDepartModel queryCompByOrgCode(String orgCode);

    /**
     * 根据机构编码和级别查询公司
     * @param orgCode 机构编码
     * @param level 级别
     * @return 公司
     */
    SysDepartModel queryCompByOrgCodeAndLevel(String orgCode, Integer level);

    /**
     * 根据机构编码和部门ID获取部门路径名称
     * @param orgCode 机构编码
     * @param depId 部门ID
     * @return 部门路径名称
     */
    String getDepartPathNameByOrgCode(String orgCode, String depId);
}