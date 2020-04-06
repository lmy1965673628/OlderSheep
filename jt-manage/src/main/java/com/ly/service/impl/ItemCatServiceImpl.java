package com.ly.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ly.mapper.ItemCatMapper;
import com.ly.pojo.ItemCat;
import com.ly.service.ItemCatService;
import com.ly.vo.EasyUITree;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Resource
    private ItemCatMapper itemCatMapper;

    @Override
    public String findItemCatNameById(Long itemCatId) {

        ItemCat itemCat = itemCatMapper.selectById(itemCatId);
        return itemCat.getName();
    }

    /**
     * EasyUITree VO对象
     * 依赖
     * ItemCat	  数据库对象
     * <p>
     * 思路:
     * 1.先查询数据库List信息
     * 2.将数据库对象转化为VO对象.
     */
    public List<ItemCat> findItemCatList(Long parentId) {
        QueryWrapper<ItemCat> queryWrapper =
                new QueryWrapper<ItemCat>();
        queryWrapper.eq("parent_id", parentId);
        List<ItemCat> itemCatList = itemCatMapper.selectList(queryWrapper);
        return itemCatList;
    }

    @Override
    public List<EasyUITree> findEasyUITreeList(Long parentId) {
        //1.定义返回数据
        List<EasyUITree> treeList = new ArrayList<EasyUITree>();
        List<ItemCat> itemCatList = findItemCatList(parentId);
        for (ItemCat itemCat : itemCatList) {
            EasyUITree easyUITree = new EasyUITree();
            String state = itemCat.getIsParent() ? "closed" : "open";
            easyUITree.setId(itemCat.getId())
                    .setText(itemCat.getName())
                    //如果是父级菜单应该关闭,否则应该打开
                    .setState(state);
            treeList.add(easyUITree);
        }

        return treeList;
    }
}
