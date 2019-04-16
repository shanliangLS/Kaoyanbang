package hehut.scse.kaoyanbang.util;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class ExpandListView extends ListView {
    public ExpandListView (Context context) {
        super(context);
    }

    public  ExpandListView (Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public  ExpandListView (Context context, AttributeSet attrs,
                            int defStyle) {
        super(context, attrs, defStyle);

    }

    /**
     * 解决嵌套ListView显示不全或只显示第一行的问题
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
