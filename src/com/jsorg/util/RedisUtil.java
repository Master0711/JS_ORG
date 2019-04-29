package com.jsorg.util;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;


/**
 * ����spring��redis��redisTemplate������
 * ������е�hash ������h��ͷ�ķ���
 * ������е�Set ������s��ͷ�ķ���                    ����ͨ�÷���
 * ������е�List ������l��ͷ�ķ���
 */
public class RedisUtil {
	private RedisTemplate<String, Object> redisTemplate;
	
	public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	//=============================common============================
	/**
	 * ָ������ʧЧʱ��
	 * @param key ��
	 * @param time ʱ��(��)
	 * @return
	 */
	public boolean expire(String key,long time){
		try {
			if(time>0){
				redisTemplate.expire(key, time, TimeUnit.SECONDS);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * ����key ��ȡ����ʱ��
	 * @param key �� ����Ϊnull
	 * @return ʱ��(��) ����0����Ϊ������Ч
	 */
	public long getExpire(String key){
		return redisTemplate.getExpire(key,TimeUnit.SECONDS);
	}
	
	/**
	 * �ж�key�Ƿ����
	 * @param key ��
	 * @return true ���� false������
	 */
	public boolean hasKey(String key){
		try {
			return redisTemplate.hasKey(key);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * ɾ������
	 * @param key ���Դ�һ��ֵ ����
	 */
	@SuppressWarnings("unchecked")
	public void del(String ... key){
		if(key!=null&&key.length>0){
			if(key.length==1){
				redisTemplate.delete(key[0]);
			}else{
				redisTemplate.delete(CollectionUtils.arrayToList(key));
			}
		}
	}
	
	//============================String=============================
	/**
	 * ��ͨ�����ȡ
	 * @param key ��
	 * @return ֵ
	 */
	public Object get(String key){
		return key==null?null:redisTemplate.opsForValue().get(key);
	}
	
	/**
	 * ��ͨ�������
	 * @param key ��
	 * @param value ֵ
	 * @return true�ɹ� falseʧ��
	 */
	public boolean set(String key,Object value) {
		 try {
			redisTemplate.opsForValue().set(key, value);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	/**
	 * ��ͨ������벢����ʱ��
	 * @param key ��
	 * @param value ֵ
	 * @param time ʱ��(��) timeҪ����0 ���timeС�ڵ���0 ������������
	 * @return true�ɹ� false ʧ��
	 */
	public boolean set(String key,Object value,long time){
		try {
			if(time>0){
				redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
			}else{
				set(key, value);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * ����
	 * @param key ��
	 * @param by Ҫ���Ӽ�(����0)
	 * @return
	 */
	public long incr(String key, long delta){  
		if(delta<0){
			throw new RuntimeException("�������ӱ������0");
		}
		return redisTemplate.opsForValue().increment(key, delta);
    }
	
	/**
	 * �ݼ�
	 * @param key ��
	 * @param by Ҫ���ټ�(С��0)
	 * @return
	 */
	public long decr(String key, long delta){  
		if(delta<0){
			throw new RuntimeException("�ݼ����ӱ������0");
		}
        return redisTemplate.opsForValue().increment(key, -delta);  
    }  
	
	//================================Map=================================
	/**
	 * HashGet
	 * @param key �� ����Ϊnull
	 * @param item �� ����Ϊnull
	 * @return ֵ
	 */
	public Object hget(String key,String item){
		return redisTemplate.opsForHash().get(key, item);
	}
	
	/**
	 * ��ȡhashKey��Ӧ�����м�ֵ
	 * @param key ��
	 * @return ��Ӧ�Ķ����ֵ
	 */
	public Map<Object,Object> hmget(String key){
		return redisTemplate.opsForHash().entries(key);
	}
	
	/**
	 * HashSet
	 * @param key ��
	 * @param map ��Ӧ�����ֵ
	 * @return true �ɹ� false ʧ��
	 */
	public boolean hmset(String key, Map<String,Object> map){  
        try {
			redisTemplate.opsForHash().putAll(key, map);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    }
	
	/**
	 * HashSet ������ʱ��
	 * @param key ��
	 * @param map ��Ӧ�����ֵ
	 * @param time ʱ��(��)
	 * @return true�ɹ� falseʧ��
	 */
    public boolean hmset(String key, Map<String,Object> map, long time){  
        try {
			redisTemplate.opsForHash().putAll(key, map);
			if(time>0){
				expire(key, time);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    }
	
	/**
	 * ��һ��hash���з�������,��������ڽ�����
	 * @param key ��
	 * @param item ��
	 * @param value ֵ
	 * @return true �ɹ� falseʧ��
	 */
	public boolean hset(String key,String item,Object value) {
		 try {
			redisTemplate.opsForHash().put(key, item, value);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * ��һ��hash���з�������,��������ڽ�����
	 * @param key ��
	 * @param item ��
	 * @param value ֵ
	 * @param time ʱ��(��)  ע��:����Ѵ��ڵ�hash����ʱ��,���ｫ���滻ԭ�е�ʱ��
	 * @return true �ɹ� falseʧ��
	 */
	public boolean hset(String key,String item,Object value,long time) {
		 try {
			redisTemplate.opsForHash().put(key, item, value);
			if(time>0){
				expire(key, time);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * ɾ��hash���е�ֵ
	 * @param key �� ����Ϊnull
	 * @param item �� ����ʹ��� ����Ϊnull
	 */
    public void hdel(String key, Object... item){  
		redisTemplate.opsForHash().delete(key,item);
    } 
    
    /**
     * �ж�hash�����Ƿ��и����ֵ
     * @param key �� ����Ϊnull
     * @param item �� ����Ϊnull
     * @return true ���� false������
     */
    public boolean hHasKey(String key, String item){
		return redisTemplate.opsForHash().hasKey(key, item);
    } 
	
	/**
	 * hash���� ���������,�ͻᴴ��һ�� �����������ֵ����
	 * @param key ��
	 * @param item ��
	 * @param by Ҫ���Ӽ�(����0)
	 * @return
	 */
	public double hincr(String key, String item,double by){  
        return redisTemplate.opsForHash().increment(key, item, by);
    }
	
	/**
	 * hash�ݼ�
	 * @param key ��
	 * @param item ��
	 * @param by Ҫ���ټ�(С��0)
	 * @return
	 */
	public double hdecr(String key, String item,double by){  
        return redisTemplate.opsForHash().increment(key, item,-by);  
    }  
	
	//============================set=============================
	/**
	 * ����key��ȡSet�е�����ֵ
	 * @param key ��
	 * @return
	 */
	public Set<Object> sGet(String key){
		try {
			return redisTemplate.opsForSet().members(key);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * ����value��һ��set�в�ѯ,�Ƿ����
	 * @param key ��
	 * @param value ֵ
	 * @return true ���� false������
	 */
	public boolean sHasKey(String key,Object value){
		try {
			return redisTemplate.opsForSet().isMember(key, value);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * �����ݷ���set����
	 * @param key ��
	 * @param values ֵ �����Ƕ��
	 * @return �ɹ�����
	 */
	public long sSet(String key, Object...values) {
        try {
            return redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
        	e.printStackTrace();
        	return 0;
        }
    }
	
	/**
	 * ��set���ݷ��뻺��
	 * @param key ��
	 * @param time ʱ��(��)
	 * @param values ֵ �����Ƕ��
	 * @return �ɹ�����
	 */
	public long sSetAndTime(String key,long time,Object...values) {
        try {
        	Long count = redisTemplate.opsForSet().add(key, values);
        	if(time>0) expire(key, time);
            return count;
        } catch (Exception e) {
        	e.printStackTrace();
        	return 0;
        }
    }
	
	/**
	 * ��ȡset����ĳ���
	 * @param key ��
	 * @return
	 */
	public long sGetSetSize(String key){
		try {
			return redisTemplate.opsForSet().size(key);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * �Ƴ�ֵΪvalue��
	 * @param key ��
	 * @param values ֵ �����Ƕ��
	 * @return �Ƴ��ĸ���
	 */
	public long setRemove(String key, Object ...values) {
        try {
            Long count = redisTemplate.opsForSet().remove(key, values);
            return count;
        } catch (Exception e) {
        	e.printStackTrace();
        	return 0;
        }
    }
    //===============================list=================================
    
	/**
	 * ��ȡlist���������
	 * @param key ��
	 * @param start ��ʼ
	 * @param end ����  0 �� -1��������ֵ
	 * @return
	 */
	public List<Object> lGet(String key,long start, long end){
		try {
			return redisTemplate.opsForList().range(key, start, end);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * ��ȡlist����ĳ���
	 * @param key ��
	 * @return
	 */
	public long lGetListSize(String key){
		try {
			return redisTemplate.opsForList().size(key);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * ͨ������ ��ȡlist�е�ֵ
	 * @param key ��
	 * @param index ����  index>=0ʱ�� 0 ��ͷ��1 �ڶ���Ԫ�أ��������ƣ�index<0ʱ��-1����β��-2�����ڶ���Ԫ�أ���������
	 * @return
	 */
	public Object lGetIndex(String key,long index){
		try {
			return redisTemplate.opsForList().index(key, index);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * ��list���뻺��
	 * @param key ��
	 * @param value ֵ
	 * @param time ʱ��(��)
	 * @return
	 */
	public boolean lSet(String key, Object value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
        	e.printStackTrace();
        	return false;
        }
    }
	
	/**
	 * ��list���뻺��
	 * @param key ��
	 * @param value ֵ
	 * @param time ʱ��(��)
	 * @return
	 */
	public boolean lSet(String key, Object value, long time) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if (time > 0) expire(key, time);
            return true;
        } catch (Exception e) {
        	e.printStackTrace();
        	return false;
        }
    }
	
	/**
	 * ��list���뻺��
	 * @param key ��
	 * @param value ֵ
	 * @param time ʱ��(��)
	 * @return
	 */
	public boolean lSet(String key, List<Object> value) {
	    try {
			redisTemplate.opsForList().rightPushAll(key, value);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    }
	
	/**
	 * ��list���뻺��
	 * @param key ��
	 * @param value ֵ
	 * @param time ʱ��(��)
	 * @return
	 */
	public boolean lSet(String key, List<Object> value, long time) {
	    try {
			redisTemplate.opsForList().rightPushAll(key, value);
			if (time > 0) expire(key, time);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    }
	
	/**
	 * ���������޸�list�е�ĳ������
	 * @param key ��
	 * @param index ����
	 * @param value ֵ
	 * @return
	 */
	public boolean lUpdateIndex(String key, long index,Object value) {
	    try {
			redisTemplate.opsForList().set(key, index, value);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    } 
	
	/**
	 * �Ƴ�N��ֵΪvalue 
	 * @param key ��
	 * @param count �Ƴ����ٸ�
	 * @param value ֵ
	 * @return �Ƴ��ĸ���
	 */
	public long lRemove(String key,long count,Object value) {
		try {
			Long remove = redisTemplate.opsForList().remove(key, count, value);
			return remove;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
}