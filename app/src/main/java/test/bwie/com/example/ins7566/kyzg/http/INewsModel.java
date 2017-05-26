package test.bwie.com.example.ins7566.kyzg.http;

import test.bwie.com.example.ins7566.kyzg.http.callback.MyCallback;

import static android.os.Build.VERSION_CODES.M;

/**
 * Created by INS7566 on 2017/5/9.
 */

public interface INewsModel {
    //新闻
    void news(String catalog, String pageIndex, String pageSize, MyCallback callback);

    //咨询详情
    void NewsDetail(String id, MyCallback callback);

    //博客
    void Blog(String type, String pageIndex, String pageSize, MyCallback callback);

    //博客详情
    void BlogDetail(String id, MyCallback callback);

    //热点
    void HotSpot(String catalog, String pageIndex, String pageSize, String show, MyCallback callback);

    //推荐
    void Recommend(String type, String pageIndex, String pageSize, MyCallback callback);

    //最新动弹
    void NewestTweent(String uid, String pageIndex, String pageSize, MyCallback callback);

    //热门动弹
    void HotTweent(String uid, String pageIndex, String pageSize, MyCallback callback);

    //我的动弹
    void MineTweent(String uid, String pageIndex, String pageSize, MyCallback callback);

    //轮播图
    void ipanda(MyCallback callback);

    //搜索
    void getSearch(String catalog, String content, String pageIndex, String pageSize, MyCallback callback);

    //搜索找人
    void SerachPeople(String name, MyCallback callback);

    //发表动弹
    void sendMsg(String uid, String msg, String img, String amr, MyCallback callback);

    //点赞
    void DianZan(String tweetid, String uid, String ownerOfTweet, MyCallback callback);

    //取消点赞
    void Unlike(String tweetid, String uid, String ownerOfTweet, MyCallback callback);

    //动弹评论
    void pinglun(String catalog, String id, String uid, String content, String isPostToMyzone, MyCallback callback);

    //获取评论
    void getPinlun(String catalog, String id, String pageIndex, String pagesize, MyCallback callback);

    //开源软件
    void KaiYuan(String type, MyCallback callback);

    //开源二级跳转
    void KaiYuanTag(String tag, MyCallback callback);

    void KY_Second(String searchTag, String pageIndex, String pageSize, MyCallback callback);

    void KaiYuanTJ(String searchTag, String pageIndex, String pageSize, MyCallback callback);

    //开源软件详情
    void KYDetail(String ident, MyCallback callback);

    //线下活动
    void HuoDong(String uid, String pageIndex, MyCallback callback);

    //线下活动详情
    void HuoDongDetail(String id, MyCallback callback);

    //摇一摇
    void Yaoyiyao(MyCallback callback);

}
