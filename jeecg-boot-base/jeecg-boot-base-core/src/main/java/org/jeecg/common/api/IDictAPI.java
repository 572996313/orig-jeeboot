package org.jeecg.common.api;

import org.jeecg.common.system.vo.*;

import java.util.List;
import java.util.Map;

/**
 * @description 字典服务API
 *
 * 提供与数据字典相关的原子服务。
 * 包括字典项翻译、根据编码查询字典、加载分类字典等功能。
 *
 * @author jeecg-boot
 * @date 2025-11-21
 */
public interface IDictAPI {

    /**
     * 从数据库翻译字典文本
     * @param table 表名
     * @param text 文本字段
     * @param code 编码字段
     * @param key 字典编码
     * @return 字典文本
     */
    String translateDictFromTable(String table, String text, String code, String key);

    /**
     * 翻译字典文本
     * @param code 字典编码
     * @param key 字典值
     * @return 字典文本
     */
    String translateDict(String code, String key);

    /**
     * 根据字典编码查询字典项
     * @param code 字典编码
     * @return 字典项
     */
    List<DictModel> queryDictItemsByCode(String code);

    /**
     * 根据字典编码查询可用的字典项
     * @param code 字典编码
     * @return 可用的字典项
     */
    List<DictModel> queryEnableDictItemsByCode(String code);

    /**
     * 根据字典编码查询表字典数据
     * @param tableFilterSql 表名
     * @param text 文本字段
     * @param code 编码字段
     * @return 表字典数据
     */
    List<DictModel> queryTableDictItemsByCode(String tableFilterSql, String text, String code);

    /**
     * 批量翻译字典
     * @param dictCodes 字典编码（多个逗号分割）
     * @param keys 字典值（多个逗号分割）
     * @return 翻译后的字典
     */
    Map<String, List<DictModel>> translateManyDict(String dictCodes, String keys);

    /**
     * 根据多个key查询表字典数据
     * @param table 表名
     * @param text 文本字段
     * @param code 编码字段
     * @param keys 字典值（多个逗号分割）
     * @param dataSource 数据源
     * @return 表字典数据
     */
    List<DictModel> translateDictFromTableByKeys(String table, String text, String code, String keys, String dataSource);

    /**
     * 查询所有字典
     * @return 所有字典
     */
    List<DictModel> queryAllDict();

    /**
     * 查询所有分类字典
     * @return 所有分类字典
     */
    List<SysCategoryModel> queryAllSysCategory();

    /**
     * 根据查询条件查询表字典
     * @param table 表名
     * @param text 文本字段
     * @param code 编码字段
     * @param filterSql 查询条件
     * @return 表字典
     */
    List<DictModel> queryFilterTableDictInfo(String table, String text, String code, String filterSql);

    /**
     * 根据多个key查询表字典
     * @param table 表名
     * @param text 文本字段
     * @param code 编码字段
     * @param keyArray 字典值数组
     * @return 表字典
     * @deprecated
     */
    @Deprecated
    List<String> queryTableDictByKeys(String table, String text, String code, String[] keyArray);

    /**
     * 加载分类字典的子项
     * @param ids 主键ID（多个逗号分割）
     * @return 分类字典的子项
     */
    List<String> loadCategoryDictItem(String ids);

    /**
     * 根据分类名称加载分类字典的子项
     * @param names 分类名称（多个逗号分割）
     * @param delNotExist 如果不存在是否删除
     * @return 分类字典的子项
     */
    List<String> loadCategoryDictItemByNames(String names, boolean delNotExist);

    /**
     * 加载字典项
     * @param dictCode 字典编码
     * @param keys 字典值（多个逗号分割）
     * @return 字典项
     */
    List<String> loadDictItem(String dictCode, String keys);

    /**
     * 复制低代码字典
     * @param originalAppId 原始AppId
     * @param appId 新的AppId
     * @param tenantId 租户ID
     * @return 复制后的字典
     */
    Map<String, String> copyLowAppDict(String originalAppId, String appId, String tenantId);

    /**
     * 获取字典项
     * @param dictCode 字典编码
     * @return 字典项
     */
    List<DictModel> getDictItems(String dictCode);

    /**
     * 批量获取字典项
     * @param dictCodeList 字典编码列表
     * @return 字典项
     */
    Map<String, List<DictModel>> getManyDictItems(List<String> dictCodeList);

    /**
     * 根据关键字加载字典项
     * @param dictCode 字典编码
     * @param keyword 关键字
     * @param pageNo 页码
     * @param pageSize 每页条数
     * @return 字典项
     */
    List<DictModel> loadDictItemByKeyword(String dictCode, String keyword, Integer pageNo, Integer pageSize);

    /**
     * SQL语句白名单校验
     * @param selectSql SQL语句
     * @return 是否通过
     */
    boolean dictTableWhiteListCheckBySql(String selectSql);

    /**
     * 字典白名单校验
     * @param tableOrDictCode 表名或字典编码
     * @param fields 字段
     * @return 是否通过
     */
    boolean dictTableWhiteListCheckByDict(String tableOrDictCode, String... fields);
}