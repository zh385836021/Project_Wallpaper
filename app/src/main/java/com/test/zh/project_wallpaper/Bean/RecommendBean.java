package com.test.zh.project_wallpaper.Bean;

import java.util.List;

/**
 * Created by Zane on 2016/6/1.
 */
public class RecommendBean {
    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String TotalCount;


        private List<WallpaperListInfoBean> WallpaperListInfo;

        public String getTotalCount() {
            return TotalCount;
        }

        public void setTotalCount(String TotalCount) {
            this.TotalCount = TotalCount;
        }

        public List<WallpaperListInfoBean> getWallpaperListInfo() {
            return WallpaperListInfo;
        }

        public void setWallpaperListInfo(List<WallpaperListInfoBean> WallpaperListInfo) {
            this.WallpaperListInfo = WallpaperListInfo;
        }

        public static class WallpaperListInfoBean {
            private String WallPaperMiddle;
            private String WallPaperBig;
            private String WallPaperDownloadPath;
            private String WallPaperSource;

            public String getWallPaperMiddle() {
                return WallPaperMiddle;
            }

            public void setWallPaperMiddle(String WallPaperMiddle) {
                this.WallPaperMiddle = WallPaperMiddle;
            }

            public String getWallPaperBig() {
                return WallPaperBig;
            }

            public void setWallPaperBig(String WallPaperBig) {
                this.WallPaperBig = WallPaperBig;
            }

            public String getWallPaperDownloadPath() {
                return WallPaperDownloadPath;
            }

            public void setWallPaperDownloadPath(String WallPaperDownloadPath) {
                this.WallPaperDownloadPath = WallPaperDownloadPath;
            }

            public String getWallPaperSource() {
                return WallPaperSource;
            }

            public void setWallPaperSource(String WallPaperSource) {
                this.WallPaperSource = WallPaperSource;
            }
        }
    }


}
