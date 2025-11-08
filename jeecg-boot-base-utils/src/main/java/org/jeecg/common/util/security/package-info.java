/**
 * 安全工具包
 * 
 * <p>提供RSA加密、数字签名、JDBC安全等功能。</p>
 * 
 * <h2>核心类</h2>
 * <ul>
 *   <li>{@link org.jeecg.common.util.security.SecurityTools} - 安全工具类（RSA、签名验证）</li>
 *   <li>{@link org.jeecg.common.util.security.JdbcSecurityUtil} - JDBC安全工具</li>
 *   <li>{@link org.jeecg.common.util.security.AbstractQueryBlackListHandler} - 查询黑名单处理器</li>
 *   <li>{@link org.jeecg.common.util.security.entity.MyKeyPair} - 密钥对实体</li>
 *   <li>{@link org.jeecg.common.util.security.entity.SecurityReq} - 加密请求</li>
 *   <li>{@link org.jeecg.common.util.security.entity.SecurityResp} - 加密响应</li>
 *   <li>{@link org.jeecg.common.util.security.entity.SecuritySignReq} - 签名请求</li>
 *   <li>{@link org.jeecg.common.util.security.entity.SecuritySignResp} - 签名响应</li>
 * </ul>
 * 
 * <h2>RSA加密示例</h2>
 * <pre>{@code
 * // 生成RSA密钥对
 * MyKeyPair keyPair = SecurityTools.genKeyPair();
 * String publicKey = keyPair.getPublicKey();
 * String privateKey = keyPair.getPrivateKey();
 * 
 * // 公钥加密
 * String plainText = "Hello World";
 * String encrypted = SecurityTools.encryptByPublicKey(plainText, publicKey);
 * 
 * // 私钥解密
 * String decrypted = SecurityTools.decryptByPrivateKey(encrypted, privateKey);
 * }</pre>
 * 
 * <h2>数字签名示例</h2>
 * <pre>{@code
 * // 私钥签名
 * String data = "important data";
 * String signature = SecurityTools.sign(data, privateKey);
 * 
 * // 公钥验证签名
 * boolean isValid = SecurityTools.verify(data, signature, publicKey);
 * }</pre>
 * 
 * <h2>JDBC安全示例</h2>
 * <pre>{@code
 * // 检查SQL是否安全
 * String sql = "SELECT * FROM users WHERE id = ?";
 * boolean isSafe = JdbcSecurityUtil.checkSql(sql);
 * 
 * // 过滤危险字符
 * String filteredSql = JdbcSecurityUtil.filterDangerString(sql);
 * }</pre>
 * 
 * <h2>特点</h2>
 * <ul>
 *   <li>支持RSA 2048位加密</li>
 *   <li>支持SHA256WithRSA数字签名</li>
 *   <li>防止SQL注入攻击</li>
 *   <li>支持查询黑名单机制</li>
 *   <li>纯静态方法，线程安全</li>
 * </ul>
 * 
 * @author llllxf
 * @version 4.0.0-SNAPSHOT
 * @since 2025-11-08
 */
package org.jeecg.common.util.security;