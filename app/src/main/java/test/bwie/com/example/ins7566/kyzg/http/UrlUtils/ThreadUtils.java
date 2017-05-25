package test.bwie.com.example.ins7566.kyzg.http.UrlUtils;

import android.os.Handler;
import android.os.Looper;



public class ThreadUtils {

    private static Handler sHandler = new Handler(Looper.getMainLooper());
    public static void runOnUIThread(Runnable task){
        sHandler.post(task);
    }

    public static void runOnStubThread(Runnable task){
        new Thread(task).start();
    }
}
