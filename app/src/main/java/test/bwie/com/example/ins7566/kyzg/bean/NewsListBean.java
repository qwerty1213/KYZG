package test.bwie.com.example.ins7566.kyzg.bean;

import java.util.List;


public class NewsListBean {
    private String catalog;
    private String pagesize;
    private String newsCount;
    private List<NewsBean> newslist;

    @Override
    public String toString() {
        return "NewsListBean{" +
                "catalog='" + catalog + '\'' +
                ", pagesize='" + pagesize + '\'' +
                ", newsCount='" + newsCount + '\'' +
                ", newslist=" + newslist +
                '}';
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getPagesize() {
        return pagesize;
    }

    public void setPagesize(String pagesize) {
        this.pagesize = pagesize;
    }

    public String getNewsCount() {
        return newsCount;
    }

    public void setNewsCount(String newsCount) {
        this.newsCount = newsCount;
    }

    public List<NewsBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewsBean> newslist) {
        this.newslist = newslist;
    }

    public static class NewsBean {
        private String id;
        private String title;
        private String body;
        private String commentCount;
        private String author;
        private String authorid;
        private String pubDate;
        private String url;
        private NewstypeBean newstype;

        @Override
        public String toString() {
            return "NewsBean{" +
                    "author='" + author + '\'' +
                    ", id='" + id + '\'' +
                    ", title='" + title + '\'' +
                    ", body='" + body + '\'' +
                    ", commentCount='" + commentCount + '\'' +
                    ", authorid='" + authorid + '\'' +
                    ", pubDate='" + pubDate + '\'' +
                    ", url='" + url + '\'' +
                    ", newstype=" + newstype +
                    '}';
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(String commentCount) {
            this.commentCount = commentCount;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getAuthorid() {
            return authorid;
        }

        public void setAuthorid(String authorid) {
            this.authorid = authorid;
        }

        public String getPubDate() {
            return pubDate;
        }

        public void setPubDate(String pubDate) {
            this.pubDate = pubDate;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public NewstypeBean getNewstype() {
            return newstype;
        }

        public void setNewstype(NewstypeBean newstype) {
            this.newstype = newstype;
        }

        public static class NewstypeBean {
            private String type;
            private String authoruid2;
            private String eventurl;
            private String attachment;

            public String getAttachment() {
                return attachment;
            }

            public void setAttachment(String attachment) {
                this.attachment = attachment;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getAuthoruid2() {
                return authoruid2;
            }

            public void setAuthoruid2(String authoruid2) {
                this.authoruid2 = authoruid2;
            }

            public String getEventurl() {
                return eventurl;
            }

            public void setEventurl(String eventurl) {
                this.eventurl = eventurl;
            }
        }
    }

}
