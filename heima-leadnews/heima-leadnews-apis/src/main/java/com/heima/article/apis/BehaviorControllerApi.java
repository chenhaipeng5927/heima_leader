package com.heima.article.apis;

import com.heima.model.behavior.dtos.ShowBehaviorDto;
import com.heima.model.common.dtos.ResponseResult;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: chenhp
 * @Date: 2020/03/09/14:10
 * @Description: 用户行为接口
 */
public interface BehaviorControllerApi {

    /**
     * @Description: 保存用户点击文章的行为
     * @Author: chenhp
     * @Date: 2020/3/9 14:21
     * @Param:
     * @Return: com.heima.model.common.dtos.ResponseResult
     */
    ResponseResult saveShowBehavior(ShowBehaviorDto dto);
}
