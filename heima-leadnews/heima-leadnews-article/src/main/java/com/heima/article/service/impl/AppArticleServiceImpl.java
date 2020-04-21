package com.heima.article.service.impl;

import com.heima.article.service.AppArticleService;
import com.heima.common.article.constans.ArticleConstans;
import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.article.pojos.ApArticle;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.mappers.app.ApArticleMapper;
import com.heima.model.mappers.app.ApUserArticleListMapper;
import com.heima.model.user.pojos.ApUser;
import com.heima.model.user.pojos.ApUserArticleList;
import com.heima.utils.threadlocal.AppThreadLocalUtils;
import com.mchange.lang.ShortUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.nntp.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: chenhp
 * @Date: 2020/03/05/10:53
 * @Description:
 */
@Service
@SuppressWarnings("all")
public class AppArticleServiceImpl implements AppArticleService {

    private static final short Max_PAGE_SIZE = 50;
    @Override
    public ResponseResult load(ArticleHomeDto dto, Short type) {
        //参数校验
        if (dto == null) {
            dto = new ArticleHomeDto();
        }
        //时间校验
        if (dto.getMaxBehotTime() == null) {
            dto.setMaxBehotTime(new Date());
        }

        if (dto.getMinBehotTime() == null) {
            dto.setMinBehotTime(new Date());
        }
        //分页参数的校验
        Integer size = dto.getSize();
        if (size == null || size <= 0) {
            size = 20;
        }
        Math.min(size,Max_PAGE_SIZE);
        dto.setSize(size);

        //文章频道参数校验
        if (StringUtils.isEmpty(dto.getTag())){
            dto.setTag(ArticleConstans.DEFAULT_TAG);
        }

        // 类型参数校验
        if (!type.equals(ArticleConstans.LOADTYPE_LOAD_MORE)&&!type.equals(ArticleConstans.LOADTYPE_LOAD_NEW)){
            type = ArticleConstans.LOADTYPE_LOAD_MORE;
        }
        //获取用户信息
        ApUser user = AppThreadLocalUtils.getUser();
        //判断用户是否存在
        if (user!=null){
            //存在   已登录 加载推荐的文章
            List<ApArticle> apArticleList = getUserArticle(user,dto,type);
            return ResponseResult.okResult(apArticleList);
        }else {
            //不存在 未登录，加载默认的文章
            List<ApArticle> apArticles = getDefaultArticle(dto,type);
            return ResponseResult.okResult(apArticles);
        }
    }

    @Autowired
    private ApArticleMapper apArticleMapper;
    /**
     * @Description: 加载默认的文章信息
     * @Author: chenhp
     * @Date: 2020/3/5 11:35
     * @Param:
     * @Return: java.util.List<com.heima.model.article.pojos.ApArticle>
     */
    private List<ApArticle> getDefaultArticle(ArticleHomeDto dto, Short type) {
        return apArticleMapper.loadArticleListByLocation(dto,type);
    }

    @Autowired
    private ApUserArticleListMapper apUserArticleListMapper;
    /**
     * @Description: 从用户的推荐表中查找文章信息，如果没有再从默认的文章信息获取数据
     * @Author: chenhp
     * @Date: 2020/3/5 11:14
     * @Param:
     * @Return: java.util.List<com.heima.model.article.pojos.ApArticle>
     */
    private List<ApArticle> getUserArticle(ApUser user, ArticleHomeDto dto, Short type) {
        List<ApUserArticleList> list = apUserArticleListMapper.loadArticleIdListByUser(user,dto,type);
        if (!list.isEmpty()){
            return apArticleMapper.loadArticleListByIdList(list);
        }else {
             return getDefaultArticle(dto,type);
        }

    }
}
