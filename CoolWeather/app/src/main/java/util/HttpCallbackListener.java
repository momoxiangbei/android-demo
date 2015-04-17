package util;

/**
 * Created by mmxb on 2015/3/26.
 */
public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
