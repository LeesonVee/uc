package com.bsoft.utils;/**
 * Arthur Administrator
 * date 2020/1/8.
 */

import com.alibaba.fastjson.JSON;
import com.bsoft.entity.TreeData;

import java.util.*;

/**
 *@ClassName TestUtils
 *@Description TODO
 *@Author Administrator
 *@Date 2020/1/8 10:56
 *@Version 1.0
 **/
public class TestUtils {
    private static List<TreeData> treeDataList;

    static {
//        Map<String, Object> props = new HashMap<>();
//        props.put("parentId", "");
//        props.put("parentText", "");
//        TreeData dept1 = new TreeData("1", "中国", props);
//        props = new HashMap<>();
//        props.put("parentId", "1");
//        props.put("parentText", "中国");
//        TreeData dept2 = new TreeData("2", "北京", props);
//        props = new HashMap<>();
//        props.put("parentId", "1");
//        props.put("parentText", "中国");
//        TreeData dept3 = new TreeData("3", "上海", props);
//        props = new HashMap<>();
//        props.put("parentId", "1");
//        props.put("parentText", "中国");
//        TreeData dept4 = new TreeData("4", "广东", props);
//        props = new HashMap<>();
//        props.put("parentId", "4");
//        props.put("parentText", "广东");
//        TreeData dept5 = new TreeData("5", "广州", props);
//        props = new HashMap<>();
//        props.put("parentId", "4");
//        props.put("parentText", "广东");
//        TreeData dept6 = new TreeData("6", "深圳", props);
//        props = new HashMap<>();
//        props.put("parentId", "5");
//        props.put("parentText", "广州");
//        TreeData dept7 = new TreeData("7", "天河区", props);
//        props = new HashMap<>();
//        props.put("parentId", "");
//        props.put("parentText", "");
//        TreeData dept8 = new TreeData("8", "美国", props);
//        treeDataList = new LinkedList<TreeData>();
//        treeDataList.add(dept1);
//        treeDataList.add(dept2);
//        treeDataList.add(dept3);
//        treeDataList.add(dept4);
//        treeDataList.add(dept5);
//        treeDataList.add(dept6);
//        treeDataList.add(dept7);
//        treeDataList.add(dept8);
    }

    private static List<TreeData> buildTree(List<TreeData> deptList, String pid) {
        List<TreeData> treeList = new ArrayList<TreeData>();
        for (TreeData treeData : deptList) {
            Map<String, Object> props = new HashMap<>();
            props = treeData.getProperties();
            if (props.get("parentId").equals(pid)) {
                treeData.setItems(buildTree(deptList, treeData.getKey()));
                treeList.add(treeData);
            }
        }
        return treeList;
    }

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", buildTree(treeDataList, ""));
        System.out.println(JSON.toJSONString(map));
    }
}