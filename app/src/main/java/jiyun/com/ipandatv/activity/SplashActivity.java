package jiyun.com.ipandatv.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import jiyun.com.ipandatv.R;

/**
 * Created by INS7566 on 2017/7/11.
 */

public class SplashActivity extends Activity{
    private LinearLayout imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anima);
        imageView = (LinearLayout) findViewById(R.id.anima_linear);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.donghua);
        imageView.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isFirstShow();
                finish();
                Intent intent = new Intent(SplashActivity.this,FirstPagerActivity.class);
                startActivity(intent);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
    /**
     * 记录是否首次登录，是，跳到引导页，否则跳到首页
     */
    public void isFirstShow(){
        SharedPreferences preferences = getSharedPreferences("goods",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        boolean isFirst = preferences.getBoolean("isFirst", true);
        if(isFirst){
            Intent intent = new Intent(SplashActivity.this,FirstPagerActivity.class);
            startActivity(intent);
            editor.putBoolean("isFirst",false);
            editor.commit();

        }else{
            Intent intent = new Intent(SplashActivity.this,MainActivity.class);
            startActivity(intent);


        }
    }
}
