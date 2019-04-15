package com.mlink.base.service;
import com.mlink.base.entity.Material;
import com.mlink.base.mapper.MaterialMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
/**
* Created by yuehaibin on 2018/10/24.
* 注:  需要回归接口添加 注解 ：@Transactional(rollbackFor = Exception.class)
*/
@Service
@EnableTransactionManagement
public class MaterialService  {

    @Autowired(required = false)
    private MaterialMapper materialMapper;

    public void insert(Material material) {
        materialMapper.insert(material);
    }

    public void updateByPrimaryKey(Material material) {
        materialMapper.updateByPrimaryKey(material);
    }

    public void deleteByPrimaryKey(String id) {
        materialMapper.deleteByPrimaryKey(id);
    }

    public Material selectByPrimaryKey(String id) {
        return materialMapper.selectByPrimaryKey(id);
    }

    public List<Material> selectAll() {
        return materialMapper.selectAll();
    }

}


