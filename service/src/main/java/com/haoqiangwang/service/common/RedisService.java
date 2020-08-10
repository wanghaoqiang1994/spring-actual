package com.haoqiangwang.service.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
    private static final Logger logger = LoggerFactory.getLogger(RedisService.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // ============================common=============================

    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    public boolean hasKey(String key){
        try{
            return redisTemplate.hasKey(key);
        }catch (Exception e){
            logger.error("redis操作出现异常！", e);
            return false;
        }
    }

    /**
     * 删除缓存
     * @param key
     */
    public void delete(String ... key){
        if(key != null && key.length > 0){
            if(key.length == 1){
                redisTemplate.delete(key[0]);
            }else{
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    // ============================String=============================

    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 设置key,value
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            logger.error("redis操作出现异常！", e);
            return false;
        }
    }

    /**
     * 根据过期时间设置key,value
     *
     * @param key
     * @param value
     * @param time
     * @return
     */
    public boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            logger.error("redis操作出现异常！", e);
            return false;
        }
    }

    // ============================list=============================

    /**
     * 获取list的缓存内容
     * @param key
     * @param start 起始位置
     * @param end 结束位置，-1 代表所有的
     * @return
     */
    public List<Object> lGet(String key, long start, long end){
        try {
            return redisTemplate.opsForList().range(key, start, end);
        }catch (Exception e){
            logger.error("redis操作出现异常！", e);
            return null;
        }
    }

    /**
     * 获取list缓存的长度
     * @param key
     * @return
     */
    public long lGetListSize(String key){
        try{
            return redisTemplate.opsForList().size(key);
        }catch (Exception e){
            logger.error("redis操作出现异常！", e);
            return 0;
        }
    }

    /**
     * 通过索引获取list缓存中的值
     * @param key
     * @param index
     * @return
     */
    public Object lGetIndex(String key, long index){
        try {
            return redisTemplate.opsForList().index(key, index);
        }catch (Exception e){
            logger.error("redis操作出现异常！", e);
            return null;
        }
    }

    /**
     * 将key1最后一个值移除添加到key2中
     * @param key1
     * @param key2
     * @return
     */
    public Object popAndPush(String key1, String key2){
        try {
            return redisTemplate.opsForList().rightPopAndLeftPush(key1, key2);
        }catch (Exception e){
            logger.error("redis操作出现异常！", e);
            return null;
        }
    }

    /**
     * 向list中添加一个值
     * @param key
     * @param value
     * @return
     */
    public boolean push(String key, Object value){
        try {
             redisTemplate.opsForList().rightPush(key, value);
             return true;
        }catch (Exception e){
            logger.error("redis操作出现异常！", e);
            return false;
        }
    }

    /**
     * 获取list缓存中最后一个值
     * @param key
     * @return
     */
    public Object pop(String key){
        try {
            return redisTemplate.opsForList().leftPop(key);
        }catch (Exception e){
            logger.error("redis操作出现异常！", e);
            return null;
        }
    }

    // ============================map=============================

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     * @param key 键
     * @param item 项
     * @param value 值
     * @return true 成功 false失败
     */
    public boolean hset(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取map中key对应的值
     * @param key
     * @param item
     * @return
     */
    public Object hget(String key, String item){
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * 判断缓存中是否存在该值
     * @param key
     * @param item
     * @return
     */
    public boolean hHasKey(String key, String item){
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    // ============================set=============================

    /**
     * 根据key获取set中的所有值
     * @param key
     * @return
     */
    public Set<Object> sGet(String key){
        try{
            return redisTemplate.opsForSet().members(key);
        }catch (Exception e){
            logger.error("redis操作出现异常！", e);
            return null;
        }
    }

    /**
     * 向set中插入值
     * @param key
     * @param values
     * @return
     */
    public long sSet(String key, Object ... values){
        try{
            return redisTemplate.opsForSet().add(key, values);
        }catch (Exception e){
            logger.error("redis操作出现异常！", e);
            return 0;
        }
    }
}
