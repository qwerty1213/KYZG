package test.bwie.com.example.ins7566.kyzg.http.okhttp;

/**
 * Created by INS7566 on 2017/5/8.
 */

public class HttpFactory {
    private static final int OKHTTP=0;
    private static final int TYPE=OKHTTP;
    public static IHttp create(){
        IHttp iHttp=null;
        switch (TYPE){
            case OKHTTP:
                iHttp=OKHttpUtils.getInstance();
                break;
        }
        return iHttp;
    }
}
