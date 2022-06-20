package com.coders.laundry.repository;

import com.coders.laundry.domain.entity.LaundryEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LaundryRepository {

    int insert(LaundryEntity laundry);

    LaundryEntity selectById(@Param("laundryId") int laundryId);

    int update(LaundryEntity laundry);

    int delete(@Param("laundryId") int laundryId);
}
