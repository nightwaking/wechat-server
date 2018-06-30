package com.wechat.web.server;

import com.alex.commons.biz.AssertUtils;
import com.alex.commons.biz.OperateContext;
import com.alex.commons.biz.http.Result;
import com.wechat.dao.WeChatFilter;
import com.wechat.dao.WeChatLocation;
import com.wechat.dao.WeChatRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api(basePath = "weChatScan", value = "weChatScan", description = "微信扫码录入地理位置",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
@RequestMapping(path = "/weChatScan", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class WeChatController {
    @Autowired
    WeChatRepository weChatRepository;

    @ApiOperation(value="微信扫码录入地理位置", notes = "微信扫码录入地理位置。")
    @RequestMapping(value="/saveLocation", method = RequestMethod.POST)
    public Result savNew (
            @RequestBody WeChatFilter filter){
        AssertUtils.argumentNotBlack(filter.getSensorLatitude(), "latitude");
        AssertUtils.argumentNotBlack(filter.getSensorLongitude(), "longitude");
        AssertUtils.argumentNotBlack(filter.getSensorCode(), "sensorCode");
        WeChatLocation webChatLocation = weChatRepository.findBySensorCode(filter.getSensorCode());
        if (webChatLocation != null) {
            return Result.newFailure(Integer.parseInt(webChatLocation.getSensorCode()), "装置:" + webChatLocation.getSensorCode() + "已存在");
        }

        WeChatLocation saveWeChatLocation = new WeChatLocation();
        saveWeChatLocation.setSaveTime(OperateContext.getDefault().getTime());
        saveWeChatLocation.setSensorCode(filter.getSensorCode());
        saveWeChatLocation.setSensorLatitude(filter.getSensorLongitude());
        saveWeChatLocation.setSensorLongitude(filter.getSensorLatitude());

        weChatRepository.save(saveWeChatLocation);
        return Result.newSuccess();
    }

}
