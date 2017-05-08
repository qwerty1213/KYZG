package test.bwie.com.example.ins7566.kyzg.http.UrlUtils;

/**
 * Created by INS7566 on 2017/5/8.
 */

public class UrlUtils {
    //网站总结口
    public static final String  BASE_URL = "http://www.oschina.net/";
    //拼接的接口新闻
    public static final String getNews = BASE_URL+"action/api/news_list";
    //详情
    public static final String getDetail = BASE_URL + "action/api/news_detail";
    //热点

    public static final String getRedian = BASE_URL+"action/api/news_list";

    //博客

    public static final String getBlog = BASE_URL+"action/api/blog_list";

    //博客详情

    public static final String getBlogDetail = BASE_URL+"action/api/blog_detail";

    //搜索
    public static final String getSearch = BASE_URL +"action/api/search_list";

    //最新动弹

    public static final String getNewDT = BASE_URL+"action/api/tweet_list";

    //热门动弹

    public static final String ReMenDT = BASE_URL+"action/api/tweet_list";

    /**
     * 发表动弹
     */

    public static final String sendTweet = BASE_URL+"action/api/tweet_pub";

    /*
   动弹点赞
    */

    /**
     * 获取粉丝关注
     */
    public static final String getFenSi = BASE_URL+"action/api/friends_list";


    public static final String DianZan = BASE_URL+"action/api/tweet_like";

    //取消点赞
    public static final String DisDianZan = BASE_URL+"action/api/tweet_unlike";


    /**
     * 动弹评论
     */
    public static final String Pinglun = BASE_URL+"action/api/comment_pub";

    /**
     * 获取评论
     */

    public static final String getPinglun =BASE_URL+"action/api/comment_list";

    //登陆

    public static final String Login = BASE_URL+"action/api/login_validate";

    /**
     * 获取用户信息
     */
    public static final String getUserName = BASE_URL+"action/api/my_information";


    //开源软件

    public static final String kaiYuan = BASE_URL+"action/api/softwarecatalog_list";

    //开源推荐
    public static final String KY_Tuijian = BASE_URL+"action/api/software_list";

    //二级分类列表
    public static final String KY_Second = BASE_URL+"action/api/softwaretag_list";

    /**
     * 开源软件详情
     */
    public static final String KY_Detail = BASE_URL+"action/api/software_detail";


    /*
    搜索中的找人

     */
    public static final String SerachPeople=BASE_URL+"action/api/find_user";


    /**
     * 活动
     */
    public static final String HuoDong = BASE_URL+"action/api/event_list";


}
