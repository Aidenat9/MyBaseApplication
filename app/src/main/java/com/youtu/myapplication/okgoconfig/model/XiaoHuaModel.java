package com.youtu.myapplication.okgoconfig.model;

import java.util.List;

/**
 * @author sunwei
 *         邮箱：tianmu19@gmail.com
 *         时间：2018/6/1 11:21
 *         包名：com.youtu.myapplication.okgoconfig.model
 *         <p>description:            </p>
 */

public class XiaoHuaModel {

    public long totalPage ;
    public int ps ;
    public int pno ;
    public List<Content>list;
    public class Content{
        public String source;
        public String title;
    }


}
