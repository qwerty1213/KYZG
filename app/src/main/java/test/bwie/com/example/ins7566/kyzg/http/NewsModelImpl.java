package test.bwie.com.example.ins7566.kyzg.http;

import java.util.HashMap;
import java.util.Map;

import test.bwie.com.example.ins7566.kyzg.http.UrlUtils.UrlUtils;
import test.bwie.com.example.ins7566.kyzg.http.callback.MyCallback;
import test.bwie.com.example.ins7566.kyzg.http.okhttp.HttpFactory;

import static android.os.Build.VERSION_CODES.M;

/**
 * Created by INS7566 on 2017/5/9.
 */

public class NewsModelImpl implements INewsModel {

    @Override
    public void news(String catalog, String pageIndex, String pageSize, MyCallback callback) {
        Map<String, String> params = new HashMap<>();
        params.put("catalog", catalog);
        params.put("pageIndex", pageIndex);
        params.put("pageSize", pageSize);
        HttpFactory.create().Get(UrlUtils.getNews, params, callback);
    }

    @Override
    public void NewsDetail(String id, MyCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        HttpFactory.create().Get(UrlUtils.getDetail, map, callback);
    }

    @Override
    public void Blog(String type, String pageIndex, String pageSize, MyCallback callback) {
        Map<String, String> params = new HashMap<>();
        params.put("type", type);
        params.put("pageIndex", pageIndex);
        params.put("pageSize", pageSize);
        HttpFactory.create().Get(UrlUtils.getBlog, params, callback);
    }

    @Override
    public void BlogDetail(String id, MyCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        HttpFactory.create().Get(UrlUtils.getBlogDetail, map, callback);
    }

    @Override
    public void HotSpot(String catalog, String pageIndex, String pageSize, String show, MyCallback callback) {
        Map<String, String> params = new HashMap<>();
        params.put("catalog", catalog);
        params.put("pageIndex", pageIndex);
        params.put("pageSize", pageSize);
        params.put("show", show);
        HttpFactory.create().Get(UrlUtils.getRedian, params, callback);
    }

    @Override
    public void Recommend(String type, String pageIndex, String pageSize, MyCallback callback) {
        Map<String, String> params = new HashMap<>();
        params.put("type", type);
        params.put("pageIndex", pageIndex);
        params.put("pageSize", pageSize);
        HttpFactory.create().Get(UrlUtils.getBlog, params, callback);
    }

    @Override
    public void NewestTweent(String uid, String pageIndex, String pageSize, MyCallback callback) {
        Map<String, String> params = new HashMap<>();
        params.put("uid", uid);
        params.put("pageIndex", pageIndex);
        params.put("pageSize", pageSize);
        HttpFactory.create().Get(UrlUtils.getNewDT, params, callback);
    }

    @Override
    public void HotTweent(String uid, String pageIndex, String pageSize, MyCallback callback) {
        Map<String, String> params = new HashMap<>();
        params.put("uid", uid);
        params.put("pageIndex", pageIndex);
        params.put("pageSize", pageSize);
        HttpFactory.create().Get(UrlUtils.ReMenDT, params, callback);

    }

    @Override
    public void MineTweent(String uid, String pageIndex, String pageSize, MyCallback callback) {
        Map<String, String> params = new HashMap<>();
        params.put("uid", uid);
        params.put("pageIndex", pageIndex);
        params.put("pageSize", pageSize);
        HttpFactory.create().Post(UrlUtils.mineTweet, params, callback);

    }

    @Override
    public void ipanda(MyCallback callback) {
        Map<String, String> map = new HashMap<>();
        HttpFactory.create().Get(UrlUtils.ipanda, map, callback);
    }

    //搜索
    @Override
    public void getSearch(String catalog, String content, String pageIndex, String pageSize, MyCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("catalog", catalog);
        map.put("content", content);
        map.put("pageIndex", pageIndex);
        map.put("pageSize", pageSize);
        HttpFactory.create().Get(UrlUtils.getSearch, map, callback);
    }

    @Override
    public void SerachPeople(String name, MyCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        HttpFactory.create().Get(UrlUtils.SerachPeople, map, callback);
    }

    //发表动弹
    @Override
    public void sendMsg(String uid, String msg, String img, String amr, MyCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("msg", msg);
        map.put("img", img);
        map.put("amr", amr);
        HttpFactory.create().Post(UrlUtils.sendTweet, map, callback);
    }

    //点赞
    @Override
    public void DianZan(String tweetid, String uid, String ownerOfTweet, MyCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("tweetid", tweetid);
        map.put("uid", uid);
        map.put("ownerOfTweet", ownerOfTweet);
        HttpFactory.create().Post(UrlUtils.DianZan, map, callback);
    }

    @Override
    public void Unlike(String tweetid, String uid, String ownerOfTweet, MyCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("tweetid", tweetid);
        map.put("uid", uid);
        map.put("ownerOfTweet", ownerOfTweet);
        HttpFactory.create().Post(UrlUtils.DisDianZan, map, callback);
    }

    //评论
    @Override
    public void pinglun(String catalog, String id, String uid, String content, String isPostToMyzone, MyCallback callback) {
        Map<String, String> params = new HashMap<>();
        params.put("catalog", "3");
        params.put("id", id);
        params.put("uid", uid);
        params.put("content", content);
        params.put("isPostToMyZone", "0");
        HttpFactory.create().Post(UrlUtils.Pinglun, params, callback);
    }

    //获取评论
    @Override
    public void getPinlun(String catalog, String id, String pageIndex, String pagesize, MyCallback callback) {
        Map<String, String> params = new HashMap<>();
        params.put("catalog", catalog);
        params.put("id", id);
        params.put("pageIndex", pageIndex);
        params.put("pageSize", pagesize);
        HttpFactory.create().Get(UrlUtils.getPinglun, params, callback);
    }

    @Override
    public void KaiYuan(String type, MyCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("type", type);
        HttpFactory.create().Get(UrlUtils.kaiYuan, map, callback);
    }

    @Override
    public void KaiYuanTag(String tag, MyCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("tag", tag);
        HttpFactory.create().Get(UrlUtils.kaiYuan, map, callback);
    }

    @Override
    public void KY_Second(String searchTag, String pageIndex, String pageSize, MyCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("searchTag", searchTag);
        map.put("pageIndex", pageIndex);
        map.put("pageSize", pageSize);
        HttpFactory.create().Get(UrlUtils.KY_Second, map, callback);
    }

    @Override
    public void KaiYuanTJ(String searchTag, String pageIndex, String pageSize, MyCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("searchTag", searchTag);
        map.put("pageIndex", pageIndex);
        map.put("pageSize", pageSize);
        HttpFactory.create().Get(UrlUtils.KY_Tuijian, map, callback);
    }

    @Override
    public void KYDetail(String ident, MyCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("ident", ident);
        HttpFactory.create().Get(UrlUtils.KY_Detail, map, callback);
    }

    @Override
    public void HuoDong(String uid, String pageIndex, MyCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("pageIndex", pageIndex);
        map.put("uid", uid);
        map.put("pageSize", "20");
        HttpFactory.create().Post(UrlUtils.HuoDong, map, callback);
    }

    @Override
    public void HuoDongDetail(String id, MyCallback callback) {
        Map<String, String> params = new HashMap<>();
        params.put("id", id);
        HttpFactory.create().Get(UrlUtils.HuoDongDetail, params, callback);
    }

    //摇一摇
    @Override
    public void Yaoyiyao(MyCallback callback) {
        Map<String, String> map = new HashMap<>();
        HttpFactory.create().Get(UrlUtils.Yaoyiyao, map, callback);
    }
}
