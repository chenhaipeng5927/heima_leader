package com.heima.article.apis;

import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.common.dtos.ResponseResult;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: chenhp
 * @Date: 2020/03/05/10:35
 * @Description:
 */
public interface ArticleHomeControllerApi {
    /**
     * @Description: 加载首页文章
     * @Author: chenhp
     * @Date: 2020/3/5 10:36
     * @Param:
     * @Return: com.heima.model.common.dtos.ResponseResult
     */
    public ResponseResult load(ArticleHomeDto dto);

    /**
     * @Description: 加载更多
     * @Author: chenhp
     * @Date: 2020/3/5 10:37
     * @Param:
     * @Return: com.heima.model.common.dtos.ResponseResult
     */
    public ResponseResult loadMore(ArticleHomeDto dto);

    /**
     * @Description: 加载最新的文章
     * @Author: chenhp
     * @Date: 2020/3/5 10:37
     * @Param:
     * @Return: com.heima.model.common.dtos.ResponseResult
     */
    public ResponseResult loadNew(ArticleHomeDto dto);
}
