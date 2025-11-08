/**
 * 查询构建器包
 * 
 * <p>提供MyBatis-Plus查询条件自动生成功能，支持根据前端传递的参数自动构建查询条件。</p>
 * 
 * <h2>核心类</h2>
 * <ul>
 *   <li>{@link org.jeecg.common.system.query.QueryGenerator} - 查询生成器，自动生成QueryWrapper</li>
 *   <li>{@link org.jeecg.common.system.query.QueryCondition} - 查询条件封装</li>
 *   <li>{@link org.jeecg.common.system.query.MatchTypeEnum} - 匹配类型枚举</li>
 *   <li>{@link org.jeecg.common.system.query.QueryRuleEnum} - 查询规则枚举</li>
 * </ul>
 * 
 * <h2>使用示例</h2>
 * <pre>{@code
 * // 方式1：根据HttpServletRequest自动生成
 * QueryWrapper<User> queryWrapper = QueryGenerator.initQueryWrapper(new User(), request.getParameterMap());
 * List<User> users = userMapper.selectList(queryWrapper);
 * 
 * // 方式2：手动构建查询条件
 * QueryWrapper<User> queryWrapper = new QueryWrapper<>();
 * QueryGenerator.installMplus(queryWrapper, new User(), request.getParameterMap());
 * }</pre>
 * 
 * <h2>支持的查询规则</h2>
 * <ul>
 *   <li><b>eq</b>：等于（=）</li>
 *   <li><b>ne</b>：不等于（!=）</li>
 *   <li><b>gt</b>：大于（>）</li>
 *   <li><b>ge</b>：大于等于（>=）</li>
 *   <li><b>lt</b>：小于（<）</li>
 *   <li><b>le</b>：小于等于（<=）</li>
 *   <li><b>like</b>：模糊查询（LIKE '%value%'）</li>
 *   <li><b>left_like</b>：左模糊（LIKE '%value'）</li>
 *   <li><b>right_like</b>：右模糊（LIKE 'value%'）</li>
 *   <li><b>in</b>：包含（IN）</li>
 *   <li><b>not_in</b>：不包含（NOT IN）</li>
 *   <li><b>between</b>：范围查询（BETWEEN）</li>
 * </ul>
 * 
 * <h2>前端传参格式</h2>
 * <pre>{@code
 * // URL参数示例
 * ?username=admin              // 默认等于查询
 * ?age_gt=18                   // 年龄大于18
 * ?createTime_ge=2025-01-01   // 创建时间大于等于
 * ?name_like=张                // 姓名模糊查询
 * ?status_in=1,2,3            // 状态在[1,2,3]中
 * }</pre>
 * 
 * @author llllxf
 * @version 4.0.0-SNAPSHOT
 * @since 2025-11-08
 */
package org.jeecg.common.system.query;