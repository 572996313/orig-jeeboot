/**
 * 基础 Service 包
 * <p>
 * 提供通用的 Service 接口和实现:
 * <ul>
 *   <li>{@link org.jeecg.modules.base.service.BaseCommonService} - 基础通用 Service 接口</li>
 *   <li>{@link org.jeecg.modules.base.service.impl.BaseCommonServiceImpl} - 基础通用 Service 实现</li>
 * </ul>
 * 
 * <h2>BaseCommonService</h2>
 * <p>封装常用的业务逻辑方法，简化开发</p>
 * <pre>{@code
 * @Service
 * public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
 *     @Autowired
 *     private BaseCommonService baseCommonService;
 *     
 *     public void deleteUser(String userId) {
 *         // 使用基础服务删除
 *         baseCommonService.deleteById("sys_user", userId);
 *     }
 *     
 *     public List<DictModel> getUserDict() {
 *         // 查询用户字典
 *         return baseCommonService.queryTableDictByKeys("sys_user", "id", "username", null);
 *     }
 * }
 * }</pre>
 * 
 * <h2>支持的功能</h2>
 * <ul>
 *   <li>通用 CRUD 操作</li>
 *   <li>字典数据查询</li>
 *   <li>数据校验</li>
 *   <li>批量操作</li>
 *   <li>事务管理</li>
 * </ul>
 * 
 * <h2>与 Mapper 的关系</h2>
 * <p>Service 层调用 Mapper 层，提供业务逻辑封装：</p>
 * <ul>
 *   <li>Service: 业务逻辑、事务控制、数据组装</li>
 *   <li>Mapper: 数据访问、SQL 执行</li>
 * </ul>
 * 
 * @author jeecg
 * @version 4.0.0
 */
package org.jeecg.modules.base.service;