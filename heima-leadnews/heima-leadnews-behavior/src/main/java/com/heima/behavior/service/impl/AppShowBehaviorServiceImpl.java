package com.heima.behavior.service.impl;

import com.heima.behavior.service.AppShowBehaviorService;
import com.heima.model.article.pojos.ApArticle;
import com.heima.model.behavior.dtos.ShowBehaviorDto;
import com.heima.model.behavior.pojos.ApBehaviorEntry;
import com.heima.model.behavior.pojos.ApShowBehavior;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.mappers.app.ApBehaviorEntryMapper;
import com.heima.model.mappers.app.ApShowBehaviorMapper;
import com.heima.model.user.pojos.ApUser;
import com.heima.utils.common.DateUtils;
import com.heima.utils.threadlocal.AppThreadLocalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: chenhp
 * @Date: 2020/03/09/14:32
 * @Description:
 */
@Service
public class AppShowBehaviorServiceImpl implements AppShowBehaviorService {

    @Autowired
    private ApBehaviorEntryMapper apBehaviorEntryMapper;

    @Autowired
    private ApShowBehaviorMapper apShowBehaviorMapper;
    @Override
    public ResponseResult saveShowBehavior(ShowBehaviorDto dto) {
        //获取用户信息,获取设备id
        ApUser user = AppThreadLocalUtils.getUser();
        //根据当前用户信息或设备id查询行为实体 ap_behavior_entry
        if (user == null && (dto.getEquipmentId() == null)) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE);
        }
        Long userId = null;
        if(user!=null){
            userId = user.getId();
        }
        ApBehaviorEntry apBehaviorEntry = apBehaviorEntryMapper.selectByUserIdOrEquipemntId(userId,dto.getEquipmentId());
        if (apBehaviorEntry == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //获取前台传来的文章列表
       /* List<ApArticle> articleIds = dto.getArticleIds();*/
        Integer[] articleIds = new Integer[dto.getArticleIds().size()];
        for (int i= 0;i<articleIds.length;i++){
            articleIds[i] = dto.getArticleIds().get(i).getId();
        }
        //根据行为实体id和文章列表id查询app行为表  app_show_behavior
        List<ApShowBehavior> apShowBehaviors = apShowBehaviorMapper.selectListByEntryIdAndArticleIds(apBehaviorEntry.getId(),articleIds);
        //数据的过滤,需要删除表中已经存在的文章id
        List<Integer> list = Arrays.asList(articleIds);
        List<Integer> list1 = new ArrayList<>(list);
        if (!apShowBehaviors.isEmpty()){
            apShowBehaviors.forEach(item->{
                Integer articleId = item.getArticleId();
                list1.remove(articleId);
            });
        }
        //保存操作
        if (!list.isEmpty()){
            articleIds = new Integer[list.size()];
            list1.toArray(articleIds);
            apShowBehaviorMapper.saveShowBehavior(articleIds,apBehaviorEntry.getId(), new Date());
        }

        return ResponseResult.okResult(0);

    }
}
