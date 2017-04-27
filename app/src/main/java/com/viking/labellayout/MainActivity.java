package com.viking.labellayout;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.viking.labellayout.view.LabelLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    private String[] strArray=new String[]{
            "起运地:北京","抵达地:包头","发货时间:2016-11-27","煤矿","20英尺集装箱","铁运","货到付款","小命","18102154156"
    };

    private LabelLayout mLabelLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLabelLayout = (LabelLayout) findViewById(R.id.ll_layout);
        TextView tv= (TextView) findViewById(R.id.tv_isOpen);
        mLabelLayout.setHandleClikeEventOnThis(tv);
        initData();
    }

    private void initData()
    {
        //方法1
//        LayoutInflater mInflater = LayoutInflater.from(this);
//        for (int i = 0; i < strArray.length; i++) {
//            TextView tv = (TextView) mInflater.inflate(R.layout.item_tv,
//                    mLabelLayout, false);
//            tv.setText(strArray[i]);
//            tv.setId(i);
//            tv.setOnClickListener(this);
//            mLabelLayout.addView(tv);
//        }
        //方法2
        refreshLine(-1);
    }

    private void refreshLine(int checkId) {
        mLabelLayout.removeAllViews();
        for (int i = 0; i < strArray.length; i++){
            if (i == checkId) {
                mLabelLayout.addView(newRadioButton("line", this, strArray[i], i, 95, 40, true));
            } else {
                mLabelLayout.addView(newRadioButton("line", this, strArray[i], i, 95, 40, false));
            }
        }
    }

    /**
     * 自定义radioButton
     *
     * @param mContext
     * @param text
     * @param id
     * @param checked  是否可点击
     * @return
     */
    public TextView newRadioButton(String tag, Context mContext, String text, int id, int width, int height, boolean checked) {
        TextView button = new TextView(mContext);
        button.setId(id);
        if (checked) {
            button.setBackgroundResource(R.drawable.bg_orange_corner);
            button.setTextColor(Color.BLACK);
        } else {
            button.setBackgroundResource(R.drawable.bg_gray_corner);
            button.setTextColor(Color.GRAY);
        }
        button.setText(text);
        button.setTextSize(14);
        button.setGravity(Gravity.CENTER);
        button.setTag(tag);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(dp2Px(this,width),dp2Px(this,height));
        layoutParams.setMargins( dp2Px(this,5),dp2Px(this,5),dp2Px(this,5),dp2Px(this,5));
        button.setLayoutParams(layoutParams);
        button.setOnClickListener(this);
        return button;
    }


    @Override
    public void onClick(View view) {
        String str= (String) view.getTag();
        if(str.equals("line")){
            Toast.makeText(this,"view:"+view.getId(),Toast.LENGTH_SHORT).show();
            refreshLine(view.getId());
        }
//        switch (view.getId()){
//            case 1:
//                Toast.makeText(this,"view:"+view.getId(),Toast.LENGTH_SHORT).show();
//                break;
//            case 2:
//                Toast.makeText(this,"view:"+view.getId(),Toast.LENGTH_SHORT).show();
//                break;
//            case 3:
//                Toast.makeText(this,"view:"+view.getId(),Toast.LENGTH_SHORT).show();
//                break;
//            case 4:
//                Toast.makeText(this,"view:"+view.getId(),Toast.LENGTH_SHORT).show();
//                break;
//            case 5:
//                Toast.makeText(this,"view:"+view.getId(),Toast.LENGTH_SHORT).show();
//                break;
//            case 6:
//                Toast.makeText(this,"view:"+view.getId(),Toast.LENGTH_SHORT).show();
//                break;
//            case 7:
//                Toast.makeText(this,"view:"+view.getId(),Toast.LENGTH_SHORT).show();
//                break;
//        }
    }

    /**
     * dp转px
     * @param context
     * @param dp
     * @return
     */
    public static int dp2Px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
