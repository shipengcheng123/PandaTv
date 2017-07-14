package fm.jiecao.jcvideoplayer_lib;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>全屏的activity</p>
 * <p>fullscreen activity</p>
 * Created by Nathen
 * On 2015/12/01 11:17
 */
public class JCFullScreenActivity extends Activity {


  //从正常状态下进入视频播放
  static void toActivityFromNormal(Context context, int state, String url, Class videoPlayClass, Object... obj) {
    CURRENT_STATE = state;
    DIRECT_FULLSCREEN = false;
    URL = url;
    VIDEO_PLAYER_CLASS = videoPlayClass;
    OBJECTS = obj;
    Intent intent = new Intent(context, JCFullScreenActivity.class);
    context.startActivity(intent);
  }

  /**
   * <p>直接进入全屏播放</p>
   * <p>Full screen play video derictly</p>
   *
   * @param context        context
   * @param url            video url
   * @param videoPlayClass your videoplayer extends JCAbstraceVideoPlayer
   * @param obj            custom param
   */
  public static void toActivity(Context context, String url, Map<String, String> headData, Class videoPlayClass, Object... obj) {
    if (headData!=null) {
      mapHeadData = new HashMap<>();
      mapHeadData.clear();
      mapHeadData.putAll(mapHeadData);
    }
    CURRENT_STATE = JCVideoPlayer.CURRENT_STATE_NORMAL;
    URL = url;
    DIRECT_FULLSCREEN = true;
    VIDEO_PLAYER_CLASS = videoPlayClass;
    OBJECTS = obj;
    //直接开启全屏Activity播放视频
    Intent intent = new Intent(context, JCFullScreenActivity.class);
    context.startActivity(intent);
  }

  JCVideoPlayer jcVideoPlayer;
  /**
   * 刚启动全屏时的播放状态
   */
  static int CURRENT_STATE = -1;      //当前的状态
  public static String URL;     //视频播放的地址
  public static Map<String, String> mapHeadData = null;//视屏播放参数的集合
  static boolean DIRECT_FULLSCREEN = false;//是否直接充满全屏
  static Class VIDEO_PLAYER_CLASS;//采用哪个视频播放类
  static Object[] OBJECTS;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    View decor = this.getWindow().getDecorView();
    decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

    try {
      Constructor<JCVideoPlayerStandard> constructor = VIDEO_PLAYER_CLASS.getConstructor(Context.class);
      jcVideoPlayer = constructor.newInstance(this);
      setContentView(jcVideoPlayer);
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }

    jcVideoPlayer.IF_CURRENT_IS_FULLSCREEN = true;
    jcVideoPlayer.IF_FULLSCREEN_IS_DIRECTLY = DIRECT_FULLSCREEN;
    jcVideoPlayer.setUrlAndObject(URL,mapHeadData==null?null:mapHeadData,OBJECTS);
    jcVideoPlayer.setStateAndUi(CURRENT_STATE);

    if (jcVideoPlayer.IF_FULLSCREEN_IS_DIRECTLY) {
      jcVideoPlayer.ivStart.performClick();
    } else {
      JCVideoPlayer.IF_RELEASE_WHEN_ON_PAUSE = true;
      JCMediaManager.intance().listener = jcVideoPlayer;
    }
  }

  @Override
  public void onBackPressed() {
    jcVideoPlayer.backFullscreen();
  }

  @Override
  protected void onPause() {
    super.onPause();
    JCVideoPlayer.releaseAllVideos();
//        finish();
  }
}
