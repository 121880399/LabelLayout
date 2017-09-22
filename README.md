# LabelLayout
可以自动换行的标签

# 实现功能
1.动态的展示标签
2.当一行不够展示自动换行
3.每个标签都可以点击
4.每个标签的样式都可以变换

# 如何使用？
1.如果只是纯粹的展示数据的话
     先画标签的样式
```
     <?xml version="1.0" encoding="utf-8"?>
    <TextView xmlns:android="http://schemas.android.com/apk/res/android"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_margin="5dp"
        android:background="@drawable/tv_bg"
        android:textColor="#5BC4ED"
        android:text="Helloworld" >
    </TextView>
```
然后创建主布局
```
    <?xml version="1.0" encoding="utf-8"?>
<RelativeLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    >
    <TextView
        android:id="@+id/tv_tag"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="搜索条件:"
        android:layout_marginLeft="15dp"
        android:layout_marginTop="5dp"
        />
    <com.viking.labellayout.view.LabelLayout
        android:id="@+id/ll_layout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_toRightOf="@id/tv_tag"
        >

    </com.viking.labellayout.view.LabelLayout>
    <TextView
        android:id="@+id/tv_isOpen"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="展开"
        android:layout_alignParentRight="true"
        android:layout_marginRight="15dp"
        android:layout_marginTop="5dp"
        android:onClick="onViewClick"
        />
</RelativeLayout>
```
接着在代码里面添加数据
```      
      LayoutInflater mInflater = LayoutInflater.from(this);
        for (int i = 0; i < strArray.length; i++) {
            TextView tv = (TextView) mInflater.inflate(R.layout.item_tv,
                    mLabelLayout, false);
            tv.setText(strArray[i]);
            tv.setId(i);
            tv.setOnClickListener(this);
            mLabelLayout.addView(tv);
        }
  ```    
 实现Onclick方法就可以完成点击事件
 
 2.如果除了展示数据,还要实现排他性选择，比如选了一个标签那么这个标签会发生颜色的变化，其他标签恢复正常。就用下面这种方法。
 动态的创建TextView
  
 ```
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
    
    每次点击标签以后刷新数据
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
 ```
 具体的见Demo
        
