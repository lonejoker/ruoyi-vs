package com.ruoyi.common.utils;

import java.util.Collection;
import java.util.List;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.framework.redis.RedisCache;
import com.ruoyi.project.system.domain.SysDictData;

/**
 * @author 终于白发始于青丝
 * @Classname DictUtils
 * @Description 类方法说明：字典工具类
 * @Date 2022/3/25 下午 14:05
 */
public class DictUtils {
    /**
     * 分隔符
     */
    public static final String SEPARATOR = ",";

    /**
     * @author 终于白发始于青丝
     * @Methodname setDictCache
     * @Description 类方法说明：设置字典缓存
     * @Return 返回值：void
     * @Params java.lang.String key 参数键
     * @Params java.util.List<com.ruoyi.project.system.domain.SysDictData> dictDatas 字典数据列表
     * @Date 2022/3/25 下午 14:05
     */
    public static void setDictCache(String key, List<SysDictData> dictDatas) {
        SpringUtils.getBean(RedisCache.class).setCacheObject(getCacheKey(key), dictDatas);
    }


    /**
     * @author 终于白发始于青丝
     * @Methodname getDictCache
     * @Description 类方法说明：获取字典缓存
     * @Return 返回值：java.util.List<com.ruoyi.project.system.domain.SysDictData> 字典数据列表
     * @Params java.lang.String key 参数键
     * @Date 2022/3/25 下午 14:05
     */
    public static List<SysDictData> getDictCache(String key) {
        Object cacheObj = SpringUtils.getBean(RedisCache.class).getCacheObject(getCacheKey(key));
        if (StringUtils.isNotNull(cacheObj)) {
            return StringUtils.cast(cacheObj);
        }
        return null;
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname getDictLabel
     * @Description 类方法说明：根据字典类型和字典值获取字典标签
     * @Return 返回值：java.lang.String 字典标签
     * @Params java.lang.String dictType 字典类型
     * @Params java.lang.String dictValue 字典值
     * @Date 2022/3/25 下午 14:05
     */
    public static String getDictLabel(String dictType, String dictValue) {
        return getDictLabel(dictType, dictValue, SEPARATOR);
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname getDictValue
     * @Description 类方法说明：根据字典类型和字典标签获取字典值
     * @Return 返回值：java.lang.String 字典值
     * @Params java.lang.String dictType 字典类型
     * @Params java.lang.String dictLabel 字典标签
     * @Date 2022/3/25 下午 14:06
     */
    public static String getDictValue(String dictType, String dictLabel) {
        return getDictValue(dictType, dictLabel, SEPARATOR);
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname getDictLabel
     * @Description 类方法说明：根据字典类型和字典值获取字典标签
     * @Return 返回值：java.lang.String 字典标签
     * @Params java.lang.String dictType 字典类型
     * @Params java.lang.String dictValue 字典值
     * @Params java.lang.String separator 分隔符
     * @Date 2022/3/25 下午 14:06
     */
    public static String getDictLabel(String dictType, String dictValue, String separator) {
        StringBuilder propertyString = new StringBuilder();
        List<SysDictData> datas = getDictCache(dictType);

        if (StringUtils.containsAny(separator, dictValue) && StringUtils.isNotEmpty(datas)) {
            for (SysDictData dict : datas) {
                for (String value : dictValue.split(separator)) {
                    if (value.equals(dict.getDictValue())) {
                        propertyString.append(dict.getDictLabel()).append(separator);
                        break;
                    }
                }
            }
        } else {
            for (SysDictData dict : datas) {
                if (dictValue.equals(dict.getDictValue())) {
                    return dict.getDictLabel();
                }
            }
        }
        return StringUtils.stripEnd(propertyString.toString(), separator);
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname getDictValue
     * @Description 类方法说明：根据字典类型和字典标签获取字典值
     * @Return 返回值：java.lang.String 字典值
     * @Params java.lang.String dictType 字典类型
     * @Params java.lang.String dictLabel 字典标签
     * @Params java.lang.String separator 分隔符
     * @Date 2022/3/25 下午 14:06
     */
    public static String getDictValue(String dictType, String dictLabel, String separator) {
        StringBuilder propertyString = new StringBuilder();
        List<SysDictData> datas = getDictCache(dictType);

        if (StringUtils.containsAny(separator, dictLabel) && StringUtils.isNotEmpty(datas)) {
            for (SysDictData dict : datas) {
                for (String label : dictLabel.split(separator)) {
                    if (label.equals(dict.getDictLabel())) {
                        propertyString.append(dict.getDictValue()).append(separator);
                        break;
                    }
                }
            }
        } else {
            for (SysDictData dict : datas) {
                if (dictLabel.equals(dict.getDictLabel())) {
                    return dict.getDictValue();
                }
            }
        }
        return StringUtils.stripEnd(propertyString.toString(), separator);
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname removeDictCache
     * @Description 类方法说明：删除指定字典缓存
     * @Return 返回值：void
     * @Params java.lang.String key 字典键
     * @Date 2022/3/25 下午 14:07
     */
    public static void removeDictCache(String key) {
        SpringUtils.getBean(RedisCache.class).deleteObject(getCacheKey(key));
    }

    /**
     * 清空字典缓存
     */
    public static void clearDictCache() {
        Collection<String> keys = SpringUtils.getBean(RedisCache.class).keys(Constants.SYS_DICT_KEY + "*" );
        SpringUtils.getBean(RedisCache.class).deleteObject(keys);
    }

    /**
     * @author 终于白发始于青丝
     * @Methodname getCacheKey
     * @Description 类方法说明：设置cache key
     * @Return 返回值：java.lang.String 缓存键key
     * @Params java.lang.String configKey 参数键
     * @Date 2022/3/25 下午 14:07
     */
    public static String getCacheKey(String configKey) {
        return Constants.SYS_DICT_KEY + configKey;
    }
}
