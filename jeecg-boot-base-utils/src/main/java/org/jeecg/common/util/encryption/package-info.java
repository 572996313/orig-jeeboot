/**
 * 加密工具包
 * 
 * <p>提供常用的加密解密功能，包括AES对称加密等。</p>
 * 
 * <h2>核心类</h2>
 * <ul>
 *   <li>{@link org.jeecg.common.util.encryption.AesEncryptUtil} - AES加密工具类</li>
 *   <li>{@link org.jeecg.common.util.encryption.EncryptedString} - 加密字符串注解</li>
 * </ul>
 * 
 * <h2>AES加密示例</h2>
 * <pre>{@code
 * // 加密
 * String plainText = "Hello World";
 * String key = "1234567890123456"; // 16位密钥
 * String encrypted = AesEncryptUtil.encrypt(plainText, key);
 * 
 * // 解密
 * String decrypted = AesEncryptUtil.decrypt(encrypted, key);
 * System.out.println(decrypted); // 输出: Hello World
 * }</pre>
 * 
 * <h2>EncryptedString注解示例</h2>
 * <pre>{@code
 * public class User {
 *     private String username;
 *     
 *     @EncryptedString
 *     private String password; // 标记此字段需要加密存储
 *     
 *     @EncryptedString
 *     private String idCard;   // 身份证号加密
 * }
 * }</pre>
 * 
 * <h2>特点</h2>
 * <ul>
 *   <li>采用AES/CBC/PKCS5Padding模式</li>
 *   <li>支持自定义密钥</li>
 *   <li>纯静态方法，无外部依赖</li>
 *   <li>线程安全</li>
 * </ul>
 * 
 * @author llllxf
 * @version 4.0.0-SNAPSHOT
 * @since 2025-11-08
 */
package org.jeecg.common.util.encryption;