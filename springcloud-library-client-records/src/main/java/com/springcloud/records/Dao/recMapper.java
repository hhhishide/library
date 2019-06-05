package com.springcloud.records.Dao;

import com.springcloud.records.pojo.face;
import com.springcloud.records.pojo.records;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface recMapper {

    public int addrecords(@Param("username") String username, @Param("content") String content);

    public List<records> getrecordsAll();

    /**
     * 获取所有表情
     * @return
     */
    public List<face> getFace();
}
