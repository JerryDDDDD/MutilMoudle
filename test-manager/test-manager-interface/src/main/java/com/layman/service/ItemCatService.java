package com.layman.service;

import com.layman.pojo.EasyUITreeNode;

import java.util.List;

public interface ItemCatService {
    List<EasyUITreeNode> getItemCatList(long parentId);
}
