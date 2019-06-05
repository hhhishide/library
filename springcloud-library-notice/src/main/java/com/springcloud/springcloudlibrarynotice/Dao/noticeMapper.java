package com.springcloud.springcloudlibrarynotice.Dao;

import com.springcloud.springcloudlibrarynotice.pojo.notice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface noticeMapper {

    public int addnotice(notice notice);

    public List<notice> selectnotice();
}
