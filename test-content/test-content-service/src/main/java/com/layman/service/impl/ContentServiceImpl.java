package com.layman.service.impl;

import com.layman.jedis.JedisClient;
import com.layman.mapper.TbContentMapper;
import com.layman.pojo.TaotaoResult;
import com.layman.pojo.TbContent;
import com.layman.pojo.TbContentExample;
import com.layman.service.ContentService;
import com.layman.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

    private String testPicUri = "classpath:timg.jpg";
    @Autowired
    TbContentMapper tbContentMapper;

    @Autowired
    JedisClient jedisClient;

    @Value("${CONTENT_KEY}")
    private String CONTENT_KEY;

    @Override
    public TaotaoResult saveContent(TbContent content) {

        //2. 补全其它属性
        content.setCreated(new Date());
        content.setUpdated(new Date());
        content.setPic(testPicUri);
        content.setPic2(testPicUri);
        //3. 插入
        tbContentMapper.insert(content);
        // 当添加内容时,需要清空此内容所属的分类下的所有缓存
        try {
            jedisClient.hdel(CONTENT_KEY,content.getCategoryId()+"");
            System.out.println("当插入是清空缓存");
        } catch (Exception e) {
            e.printStackTrace();
        }


        return TaotaoResult.ok();
    }

    @Override
    public List<TbContent> getContentListByCatId(Long categoryId) {

        // 添加缓存不能影响正常的业务逻辑

        // 判断是否redis中有数据 如果有直接从redis中返回
        try {
            String jsonstr = jedisClient.hget(CONTENT_KEY,categoryId+""); //从Redis数据库中获取内容分类下的多有的内容
            // 如果存在有缓存
            if (StringUtils.isNotBlank(jsonstr)){
                System.out.println("cache is blank");
                return JsonUtils.jsonToList(jsonstr,TbContent.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        //2. 创建example
        TbContentExample example = new TbContentExample();
        //3. 设置查询条件
        example.createCriteria().andCategoryIdEqualTo(categoryId);
        //4. 执行查询
        List<TbContent> list = tbContentMapper.selectByExample(example);
        //5. 返回


        // 将数据写入到Redis数据库中
            //注入Jedisclient
            //调用方法写入Redis
        try {
            System.out.println("cache is null");
            jedisClient.hset(CONTENT_KEY,categoryId+"",JsonUtils.objectToJson(list));
            jedisClient.expire(CONTENT_KEY,60*60);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }
}
