package com.heima.model.mappers.app;

import com.heima.model.behavior.pojos.ApBehaviorEntry;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: chenhp
 * @Date: 2020/03/09/15:08
 * @Description:
 */
/*@Mapper*/
public interface ApBehaviorEntryMapper {

 /*   String TABLE = "ap_behavior_entry";

    final String COLUMN = "a.id, a.type, a.entry_id, a.created_time";

    @SelectProvider(type = Provider.class, method = "selectByUserIdOrEquipemntId")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "type", property = "type"),
            @Result(column = "entry_id", property = "entryId"),
            @Result(column = "created_time", property = "createdTime"),
            @Result(column = "burst", property = "burst"),
    })*/
    ApBehaviorEntry selectByUserIdOrEquipemntId(@Param("userId") Long userId, @Param("equipmentId") Integer equipmentId);
/*
    class Provider {
        public String selectByUserIdOrEquipemntId(Map<String, Object> param) {
            Long userId = (Long) param.get("userId");
            Integer equipmentId = (Integer) param.get("equipmentId");
            StringBuilder sqlBuilder = new StringBuilder();
            sqlBuilder.append("SELECT ");
            sqlBuilder.append(COLUMN);
            sqlBuilder.append(" from ");
            sqlBuilder.append(TABLE);
            sqlBuilder.append(" a");
            if (!(userId == null)) {
                sqlBuilder.append(" where a.entry_id = #{userId} and a.type = 1");
            }
            if ((userId == null) && equipmentId != null) {
                sqlBuilder.append(" where a.entry_id = #{equipmentId} and a.type = 0");
            }
            return sqlBuilder.toString();
        }
    }*/
}
