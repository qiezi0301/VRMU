package com.vr_mu.vrmu.views.customize;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by zjl on 17/3/27.
 */

public class ListViewForScrollView extends ListView {
    public ListViewForScrollView(Context context) {

        super(context);

    }

    public ListViewForScrollView(Context context, AttributeSet attrs) {

        super(context, attrs);

    }

    public ListViewForScrollView(Context context, AttributeSet attrs, int defStyle) {

        super(context, attrs, defStyle);

    }

    @Override

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);

        super.onMeasure(widthMeasureSpec, expandSpec);

    }
}
