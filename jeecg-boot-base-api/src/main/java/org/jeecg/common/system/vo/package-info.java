/**
 * JeecgBoot 系统视图对象包
 * 
 * <p>本包提供JeecgBoot系统模块的视图对象(VO)定义，用于数据展示和传输。
 * 
 * <h2>主要组件</h2>
 * <ul>
 *   <li>{@link org.jeecg.common.system.vo.LoginUser} - 登录用户信息</li>
 *   <li>{@link org.jeecg.common.system.vo.DictModel} - 字典模型</li>
 *   <li>{@link org.jeecg.common.system.vo.ComboModel} - 下拉选择模型</li>
 *   <li>{@link org.jeecg.common.system.vo.SelectTreeModel} - 树形选择模型</li>
 * </ul>
 * 
 * <h2>VO对象分类</h2>
 * <ul>
 *   <li><b>用户相关</b>: LoginUser, UserAccountInfo, SysUserCacheInfo</li>
 *   <li><b>字典相关</b>: DictModel, DictModelMany, DictQuery</li>
 *   <li><b>组织架构</b>: SysDepartModel, SysCategoryModel</li>
 *   <li><b>权限相关</b>: SysPermissionDataRuleModel</li>
 *   <li><b>通用模型</b>: ComboModel, SelectTreeModel, SysFilesModel</li>
 *   <li><b>数据源</b>: DynamicDataSourceModel</li>
 * </ul>
 * 
 * <h2>使用示例</h2>
 * <pre>{@code
 * // 登录用户
 * LoginUser loginUser = new LoginUser();
 * loginUser.setUsername("admin");
 * 
 * // 字典模型
 * DictModel dict = new DictModel();
 * dict.setValue("1");
 * dict.setText("启用");
 * 
 * // 下拉选择
 * ComboModel combo = new ComboModel();
 * combo.setTitle("选项1");
 * combo.setValue("1");
 * }</pre>
 * 
 * @since 4.0.0
 * @version 4.0.0
 * @author JeecgBoot Team
 */
package org.jeecg.common.system.vo;