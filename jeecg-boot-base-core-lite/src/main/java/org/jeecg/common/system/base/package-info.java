/**
 * JeecgBoot 基础CRUD包
 * 
 * <p>提供JeecgBoot框架的基础CRUD能力，包括：</p>
 * <ul>
 *   <li>{@link org.jeecg.common.system.base.controller.JeecgController} - 控制器基类</li>
 *   <li>{@link org.jeecg.common.system.base.entity.JeecgEntity} - 实体基类</li>
 *   <li>{@link org.jeecg.common.system.base.service.JeecgService} - 服务接口</li>
 *   <li>{@link org.jeecg.common.system.base.service.impl.JeecgServiceImpl} - 服务实现基类</li>
 * </ul>
 * 
 * <h2>使用示例</h2>
 * <pre>{@code
 * // 实体类继承JeecgEntity
 * @Data
 * @EqualsAndHashCode(callSuper = true)
 * @TableName("sys_user")
 * public class SysUser extends JeecgEntity {
 *     private String username;
 *     private String realname;
 * }
 * 
 * // 服务接口继承JeecgService
 * public interface ISysUserService extends JeecgService<SysUser> {
 * }
 * 
 * // 服务实现继承JeecgServiceImpl
 * @Service
 * public class SysUserServiceImpl extends JeecgServiceImpl<SysUserMapper, SysUser> 
 *         implements ISysUserService {
 * }
 * 
 * // 控制器继承JeecgController
 * @RestController
 * @RequestMapping("/sys/user")
 * public class SysUserController extends JeecgController<SysUser, ISysUserService> {
 * }
 * }</pre>
 * 
 * @since 4.0.0
 * @author JeecgBoot Team
 */
package org.jeecg.common.system.base;