package com.test.zh.project_wallpaper.Bean;

import java.util.List;

/**
 * Created by Zane on 2016/5/31.
 */
public class ClassifyBean {

    /**
     * ID : 1042
     * TipContent : 小编日也加班为大家寻找美图，恳请大家给个好评！
     * DescWords : 清纯 | 可爱 | 性感
     * LockType : 0
     * LockKey : {"URLScheme":"https://itunes.apple.com/cn/app/id507388249","title":"u89e3u9501u63d0u793a","appID":"507388249"}
     * PicCategoryName : 美女图片
     * PicCategoryLogo : /picture1/M00/14/65/wKiFR1Ycm6KAGnq3AABVPcUNCOg695.jpg
     * CategoryPic : http://bzpic.spriteapp.cn/picture1/M00/14/65/wKiFR1Ycm6KAGnq3AABVPcUNCOg695.jpg
     * SecondCategoryList : [{"ID":2140,"BigCategoryId":1042,"PicCategoryName":"性感","CategoryPic":""},{"ID":2142,"BigCategoryId":1042,"PicCategoryName":"清纯","CategoryPic":""},{"ID":2143,"BigCategoryId":1042,"PicCategoryName":"萝莉","CategoryPic":""},{"ID":2144,"BigCategoryId":1042,"PicCategoryName":"气质","CategoryPic":""},{"ID":2666,"BigCategoryId":1042,"PicCategoryName":"模特","CategoryPic":""},{"ID":2667,"BigCategoryId":1042,"PicCategoryName":"女优","CategoryPic":""}]
     * tags : [{"id":"164","name":"美女"},{"id":"29318","name":"美女"},{"id":"357","name":"性感"},{"id":"34852","name":"性感迷人"},{"id":"34312","name":"美女 性感"},{"id":"32680","name":"清新"},{"id":"29321","name":"性感"},{"id":"32634","name":"女神"},{"id":"34151","name":"萌妹子"},{"id":"36666","name":"气质"}]
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String DescWords;
        private String PicCategoryName;
        private String CategoryPic;

        public String getDescWords() {
            return DescWords;
        }

        public void setDescWords(String DescWords) {
            this.DescWords = DescWords;
        }

        public String getPicCategoryName() {
            return PicCategoryName;
        }

        public void setPicCategoryName(String PicCategoryName) {
            this.PicCategoryName = PicCategoryName;
        }

        public String getCategoryPic() {
            return CategoryPic;
        }

        public void setCategoryPic(String CategoryPic) {
            this.CategoryPic = CategoryPic;
        }
    }
}
