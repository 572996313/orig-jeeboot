/**
 * 处理器接口包
 * 
 * <p>提供JeecgBoot框架的扩展点接口：</p>
 * <ul>
 *   <li>{@link org.jeecg.common.handler.IFillRuleHandler} - 填充规则处理器接口</li>
 * </ul>
 * 
 * <h2>使用示例</h2>
 * <pre>{@code
 * // 实现自定义填充规则处理器
 * @Component
 * public class CustomFillRuleHandler implements IFillRuleHandler {
 *     @Override
 *     public Object execute(String ruleCode, JSONObject param) {
 *         // 自定义填充逻辑
 *         return "填充值";
 *     }
 * }
 * }</pre>
 * 
 * @since 4.0.0
 * @author JeecgBoot Team
 */
package org.jeecg.common.handler;