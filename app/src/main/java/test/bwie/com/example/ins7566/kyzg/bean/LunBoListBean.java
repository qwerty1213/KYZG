package test.bwie.com.example.ins7566.kyzg.bean;

import java.util.List;

/**
 * Created by lenovo on 2017/5/2.
 */

public class LunBoListBean {
     private  String notice;

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * url : http://panda.cntv.cn/2015/12/31/VIDE1451545800992906.shtml
         * image : http://p1.img.cctvpic.com/photoAlbum/page/performance/img/2016/1/5/1451991014992_80.jpg
         * title : 《精彩一刻》树枝和竹子一样吃法
         * videoLength :
         * id : TITE1451991022695401
         * daytime :
         * type :
         * order : 1
         */

        private String url;
        private String image;
        private String title;
        private String videoLength;
        private String id;
        private String daytime;
        private String type;
        private String order;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getVideoLength() {
            return videoLength;
        }

        public void setVideoLength(String videoLength) {
            this.videoLength = videoLength;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDaytime() {
            return daytime;
        }

        public void setDaytime(String daytime) {
            this.daytime = daytime;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }
    }
}
