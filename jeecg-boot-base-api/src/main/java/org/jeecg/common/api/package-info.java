/**
 * JeecgBoot API接口定义包
 * 
 * <p>本包提供JeecgBoot框架的API接口定义、数据传输对象(DTO)和视图对象(VO)。
 * 
 * <h2>主要组件</h2>
 * <ul>
 *   <li>{@link org.jeecg.common.api.CommonAPI} - 通用API接口</li>
 *   <li>{@link org.jeecg.common.api.vo.Result} - 统一响应结果对象</li>
 *   <li>{@link org.jeecg.common.api.dto} - 数据传输对象子包</li>
 * </ul>
 * 
 * <h2>特性</h2>
 * <ul>
 *   <li>统一的API接口定义</li>
 *   <li>标准化的数据传输格式</li>
 *   <li>支持JSON序列化</li>
 *   <li>支持Swagger文档生成</li>
 * </ul>
 * 
 * <h2>使用示例</h2>
 * <pre>{@code
 * // 成功响应
 * Result<User> result = Result.ok(user);
 * 
 * // 错误响应
 * Result<Void> error = Result.error("操作失败");
 * 
 * // 创建DTO
 * LogDTO logDTO = new LogDTO();
 * logDTO.setLogContent("操作日志");
 * }</pre>
 * 
 * @since 4.0.0
 * @version 4.0.0
 * @author JeecgBoot Team
 */
package org.jeecg.common.api;