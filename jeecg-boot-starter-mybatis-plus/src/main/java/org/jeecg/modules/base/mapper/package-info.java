/**
 * 基础 Mapper 包
 * <p>
 * 提供通用的 Mapper 接口和实现:
 * <ul>
 *   <li>{@link org.jeecg.modules.base.mapper.BaseCommonMapper} - 基础通用 Mapper</li>
 * </ul>
 * 
 * <h2>BaseCommonMapper</h2>
 * <p>提供常用的数据库查询方法，无需编写 SQL 语句</p>
 * <pre>{@code
 * @Mapper
 * public interface UserMapper extends BaseMapper<User> {
 *     // 继承 BaseCommonMapper 的通用方法
 * }
 * 
 * // 使用示例
 * @Autowired
 * private BaseCommonMapper baseMapper;
 * 
 * // 根据字典查询
 * List<DictModel> dictList = baseMapper.queryTableDictItemsByCode("sys_user", "sex", "sex_name");
 * 
 * // 删除数据
 * baseMapper.deleteByTableAndId("sys_user", "1234567890");
 * 
 * // 批量查询
 * List<DictModel> list = baseMapper.queryTableDictByKeys("sys_user", "id", "username", Arrays.asList("1", "2"));
 * }</pre>
 * 
 * <h2>支持的功能</h2>
 * <ul>
 *   <li>字典数据查询</li>
 *   <li>多表字典联合查询</li>
 *   <li>通用数据删除</li>
 *   <li>字段重复性校验</li>
 *   <li>表单填充规则查询</li>
 * </ul>
 * 
 * @author jeecg
 * @version 4.0.0
 */
package org.jeecg.modules.base.mapper;