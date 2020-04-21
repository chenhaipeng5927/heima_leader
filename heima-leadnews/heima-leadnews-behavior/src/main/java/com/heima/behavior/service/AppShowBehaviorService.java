package com.heima.behavior.service;

import com.heima.model.behavior.dtos.ShowBehaviorDto;
import com.heima.model.common.dtos.ResponseResult;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: chenhp
 * @Date: 2020/03/09/14:30
 * @Description:
 */
public interface AppShowBehaviorService {
    /**
     * @Description: 存储行为数据
     * @Author: chenhp
     * @Date: 2020/3/9 14:31
     * @Param:
     * @Return: com.heima.model.common.dtos.ResponseResult
     */
    public ResponseResult saveShowBehavior(ShowBehaviorDto dto);
}
