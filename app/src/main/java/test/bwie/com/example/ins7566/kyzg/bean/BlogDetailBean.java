package test.bwie.com.example.ins7566.kyzg.bean;

/**
 * Created by INS7566 on 2017/5/10.
 */

//@org.simpleframework.xml.Root(name = "oschina")
public class BlogDetailBean {

    private BlogBean blog;

    public BlogBean getBlog() {
        return blog;
    }

    public void setBlog(BlogBean blog) {
        this.blog = blog;
    }

//    @org.simpleframework.xml.Root(name = "blog")
    public static class BlogBean {
        private String id;
        private String title;
        private String url;
        private String where;
        private String commentCount;
        private String body;
        private String author;
        private String authorid;
        private String documentType;
        private String pubDate;
        private String favorite;

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

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getWhere() {
            return where;
        }

        public void setWhere(String where) {
            this.where = where;
        }

        public String getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(String commentCount) {
            this.commentCount = commentCount;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
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

        public String getDocumentType() {
            return documentType;
        }

        public void setDocumentType(String documentType) {
            this.documentType = documentType;
        }

        public String getPubDate() {
            return pubDate;
        }

        public void setPubDate(String pubDate) {
            this.pubDate = pubDate;
        }

        public String getFavorite() {
            return favorite;
        }

        public void setFavorite(String favorite) {
            this.favorite = favorite;
        }
    }
}
