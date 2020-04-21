package com.heima.article;

import com.heima.article.service.AppArticleService;
import com.heima.common.article.constans.ArticleConstans;
import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.user.pojos.ApUser;
import com.heima.utils.threadlocal.AppThreadLocalUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: chenhp
 * @Date: 2020/03/05/13:50
 * @Description:
 */

@SpringBootTest(classes = ArticleJarApplication.class)
@RunWith(SpringRunner.class)
public class ArticleTest {
    @Autowired
    private AppArticleService appArticleService;

    @Test
    public void testLoad(){
      /*  ApUser apUser = new ApUser();
        apUser.setId(1111l);
        AppThreadLocalUtils.setUser(apUser);*/
        ArticleHomeDto dto = new ArticleHomeDto();
        ResponseResult data = appArticleService.load(dto,ArticleConstans.LOADTYPE_LOAD_MORE);
        System.out.println(data.getData());
    }

}
