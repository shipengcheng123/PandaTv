package fm.jiecao.jcvideoplayer_lib;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.Map;

/**
 * Manage UI
 * Created by Nathen
 * On 2016/04/10 15:45
 */
public class JCVideoPlayerSimple extends JCVideoPlayer {

  public JCVideoPlayerSimple(Context context) {
    super(context);
  }

  public JCVideoPlayerSimple(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  public int getLayoutId() {
    return R.layout.jc_layout_base;
  }

  @Override
  public void setUrlAndObject(String url, Map<String, String> mapHeadData,Object... objects) {
    super.setUrlAndObject(url,mapHeadData, objects);
    if (IF_CURRENT_IS_FULLSCREEN) {
      ivFullScreen.setImageResource(R.drawable.jc_shrink);
    } else {
      ivFullScreen.setImageResource(R.drawable.jc_enlarge);
    }
  }

  @Override
  public void setStateAndUi(int state) {
    super.setStateAndUi(state);
    switch (CURRENT_STATE) {
      case CURRENT_STATE_NORMAL:
        ivStart.setVisibility(View.VISIBLE);
        break;
      case CURRENT_STATE_PREPAREING:
        ivStart.setVisibility(View.INVISIBLE);
        break;
      case CURRENT_STATE_PLAYING:
        ivStart.setVisibility(View.VISIBLE);
        break;
      case CURRENT_STATE_PAUSE:
        break;
      case CURRENT_STATE_ERROR:
        break;
    }
    updateStartImage();
  }

  private void updateStartImage() {
    if (CURRENT_STATE == CURRENT_STATE_PLAYING) {
      ivStart.setImageResource(R.drawable.jc_click_pause_selector);
    } else if (CURRENT_STATE == CURRENT_STATE_ERROR) {
      ivStart.setImageResource(R.drawable.jc_click_error_selector);
    } else {
      ivStart.setImageResource(R.drawable.jc_click_play_selector);
    }
  }

  @Override
  public void onClick(View v) {
    if (v.getId() == R.id.fullscreen && CURRENT_STATE == CURRENT_STATE_NORMAL) {
      Toast.makeText(getContext(), "Play video first", Toast.LENGTH_SHORT).show();
      return;
    }
    super.onClick(v);
  }

  @Override
  public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
    if (fromUser) {
      if (CURRENT_STATE == CURRENT_STATE_NORMAL) {
        Toast.makeText(getContext(), "Play video first", Toast.LENGTH_SHORT).show();
        return;
      }
    }
    super.onProgressChanged(seekBar, progress, fromUser);
  }
}
