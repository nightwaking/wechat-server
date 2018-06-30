package com.wechat.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface WeChatRepository extends JpaSpecificationExecutor<WeChatLocation>, PagingAndSortingRepository<WeChatLocation, String>{
    WeChatLocation findBySensorCode(String sensorCode);
}
