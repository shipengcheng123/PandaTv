package jiyun.com.ipandatv.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import java.util.Map;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;


/**
 * Created by Nathen
 * On 2016/04/27 10:49
 */
public class JCVideoPlayerStandardShowTitleAfterFullscreen extends JCVideoPlayerStandard {
  public JCVideoPlayerStandardShowTitleAfterFullscreen(Context context) {
    super(context);
  }

  public JCVideoPlayerStandardShowTitleAfterFullscreen(Context context, AttributeSet attrs) {
    super(context, attrs);
  }




  @Override
  public void setUrlAndObject(String url, Map<String, String> mapHeadData, Object... objects) {
    super.setUrlAndObject(url,mapHeadData, objects);
    if (IF_CURRENT_IS_FULLSCREEN) {
      tvTitle.setVisibility(View.VISIBLE);
    } else {
      tvTitle.setVisibility(View.INVISIBLE);
    }
  }
}
