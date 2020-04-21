package com.heima.model.mappers.app;

import com.heima.model.behavior.pojos.ApShowBehavior;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: chenhp
 * @Date: 2020/03/09/15:17
 * @Description:
 */
/*@Mapper*/
public interface ApShowBehaviorMapper {
    /*String TABLE = "ap_show_behavior";
    final String COLUMN = " a.id, a.entry_id, a.article_id, a.is_click, a.show_time, a.created_time";

    @SelectProvider(type = Provider.class, method = "selectListByEntryIdAndArticleIds")
    @Results({
            @Result(column = "entry_id", property = "entryId"),
            @Result(column = "article_id", property = "articleId"),
            @Result(column = "is_click", property = "isClick"),
            @Result(column = "show_time", property = "showTime"),
            @Result(column = "created_time", property = "createdTime"),
    })*/
    List<ApShowBehavior> selectListByEntryIdAndArticleIds(@Param("entryId") Integer id, @Param("articleIds") Integer[] articleIds);
/*
    @InsertProvider(type = Provider.class, method = "saveShowBehavior")*/
    void saveShowBehavior(@Param("articleIds") Integer[] articleIds, @Param("entryId") Integer id,@Param("date") Date date);


    /*class Provider {
        public String selectListByEntryIdAndArticleIds(Map<String, Object> param) {
            Integer[] article_ids = (Integer[]) param.get("articleIds");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("select ");
            stringBuilder.append(COLUMN);
            stringBuilder.append(" from ");
            stringBuilder.append(TABLE);
            stringBuilder.append(" a where a.entry_id = #{entryId} ");
            if (!(article_ids.length == 0)) {
                stringBuilder.append("and a.article_id in");
                stringBuilder.append(" ( ");
                for (Integer i : article_ids) {
                    stringBuilder.append(i + " ,");
                }
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                stringBuilder.append(" ) ");
            }
            return stringBuilder.toString();
        }

        public String saveShowBehavior(Map<String, Object> param) {
            Integer[] article_ids = (Integer[]) param.get("articleIds");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(" INSERT into ");
            stringBuilder.append(TABLE);
            stringBuilder.append(" (id, entry_id, article_id,is_click, show_time, created_time) values ");
            for (Integer i : article_ids) {
                stringBuilder.append("( "+i+",#{entryId},"+i+", 0 ,now(),now() ),");
            }
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
            return  stringBuilder.toString();

        }
    }*/

}
