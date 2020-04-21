package com.heima.article.service;


import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.common.dtos.ResponseResult;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: chenhp
 * @Date: 2020/03/05/10:42
 * @Description:
 */



public interface AppArticleService {


    /**
     * @Description: type:1.加载更多 2加载更新
     * @Author: chenhp
     * @Date: 2020/3/5 10:52
     * @Param:
     * @Return: com.heima.model.common.dtos.ResponseResult
     */
    ResponseResult load(ArticleHomeDto dto, Short type);
}
