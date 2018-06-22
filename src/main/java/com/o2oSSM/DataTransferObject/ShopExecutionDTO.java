package com.o2oSSM.DataTransferObject;

import com.o2oSSM.DataObject.Shop;
import com.o2oSSM.Enums.ShopStateEnum;
import lombok.Data;

import java.util.List;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/23
 * 16:28
 * #
 */
@Data
public class ShopExecutionDTO {
    //结果状态，结果码，数字
    private Integer state;
    //状态标识，结果信息
    private String stataInfo;
    //店铺数量
    private Integer shopCount;
    //操作的店铺（增删改查shop用到）
    private Shop shop;
    //获取shop列表
    private List<Shop> shopList;

    //构造器
    public ShopExecutionDTO() {
    }

    //失败的构造器
    public ShopExecutionDTO(ShopStateEnum shopStateEnum) {
        this.state = shopStateEnum.getCode();
        this.stataInfo = shopStateEnum.getStateMsg();
    }

    //成功的构造器，返回信息和DTO
    public ShopExecutionDTO(ShopStateEnum shopStateEnum,Shop shop) {
        this.state = shopStateEnum.getCode();
        this.stataInfo = shopStateEnum.getStateMsg();
        this.shop = shop;
    }

}
