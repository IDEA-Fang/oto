package com.o2oSSM.Service.IMPL;

import com.o2oSSM.DAO.AreaDAO;
import com.o2oSSM.DataObject.Area;
import com.o2oSSM.Service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/16
 * 20:39
 * #
 */
@Service
public class AreaServiceIMPL  implements AreaService {

    @Autowired
    private AreaDAO areaDAO;

    @Override
    public List<Area> getAreaList() {
        return areaDAO.areaList();
    }
}
