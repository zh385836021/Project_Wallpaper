package com.test.zh.project_wallpaper.Bean;

import java.util.List;

/**
 * Created by samsung on 2016/6/3.
 */
public class SearchListBean {


    /**
     * keyword : 文字
     * imgs : ["http://bzpic.spriteapp.cn/picture2/M00/00/1C/wKiFRlKKWfOAbdHzAAG73_7b9kM47.jpeg","http://bzpic.spriteapp.cn/picture2/M00/00/1B/wKiFWVKKWfKAB_7pAAKM8JmRhbY85.jpeg","http://bzpic.spriteapp.cn/picture2/M00/00/1B/wKiFRlKKWfGAUA5fAABrBhZ4evE07.jpeg"]
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String keyword;
        private List<String> imgs;

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public List<String> getImgs() {
            return imgs;
        }

        public void setImgs(List<String> imgs) {
            this.imgs = imgs;
        }
    }
}
