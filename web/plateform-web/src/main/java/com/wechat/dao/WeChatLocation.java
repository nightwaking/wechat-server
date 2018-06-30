package com.wechat.dao;

import javax.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Access(AccessType.FIELD)
@Table(name = "LM_WECHAT", indexes = {
        @Index(name = "IDX_WECHAT", columnList = "sensor_code")
})
public class WeChatLocation extends com.alex.commons.jpa.PersistenceEntity{
    /** 设备编号*/
    @Column(name="sensor_code", length = 12, nullable=false)
    private String sensorCode;
    /** 纬度*/
    @Column(name="sensor_latitude", length = 20)
    private String sensorLatitude;
    /** 经度*/
    @Column(name="sensor_longitude", length = 20)
    private String sensorLongitude;
    /** 保存时间*/
    @Column (name="save_time")
    private Date saveTime;
}