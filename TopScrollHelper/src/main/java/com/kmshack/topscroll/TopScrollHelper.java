package com.kmshack.topscroll;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.PixelFormat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.ScrollView;

/**
 * Created by kmshack on 15. 10. 6..
 */
public class TopScrollHelper {

    private static String TAG = "TopScroll";
    private static boolean DEBUG = false;

    private static int TOUCH_BOUND_WIDTH = 80;

    private static TopScrollHelper sInstance;

    private Context mContext;

    private View mView;
    private List<View> mTargetScrollView;

    private WindowManager mWindowManager;
    private GestureDetector mGestureDetector;

    public synchronized static TopScrollHelper getInstance(Context context) {

        if (sInstance != null) {
            return sInstance;
        }

        sInstance = new TopScrollHelper(context);
        return sInstance;
    }

    private TopScrollHelper(Context context) {
        mContext = context;
        init();
    }

    private void init() {
        if (DEBUG)
            Log.d(TAG, "init");

        mWindowManager = ((WindowManager) mContext.getApplicationContext().getSystemService(Context.WINDOW_SERVICE));
        mTargetScrollView = new ArrayList<View>();
        mGestureDetector = new GestureDetector(mContext, new SimpleOnGestureListener() {

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                for (View view : mTargetScrollView) {
                    if (DEBUG)
                        Log.d(TAG, "is shown ? " + view.isShown());

                    if (view.isShown()) {
                        if (view instanceof AbsListView) {
                            if (((AbsListView) view).getAdapter() != null && ((AbsListView) view).getAdapter().getCount() > 15)
                                ((AbsListView) view).setSelection(15);

                            ((AbsListView) view).smoothScrollToPosition(0, 0);
                        } else if (view instanceof ScrollView) {
                            ((ScrollView) view).smoothScrollTo(0, 0);
                        } else if (view instanceof WebView) {
                            ((WebView) view).scrollTo(0, 0);
                        } else if(view instanceof RecyclerView){
                            if (((RecyclerView) view).getAdapter() != null && ((RecyclerView) view).getAdapter().getItemCount() > 15 && ((RecyclerView) view).getLayoutManager()!=null) {
                                ((RecyclerView) view).getLayoutManager().scrollToPosition(15);
                            }

                            ((RecyclerView)view).smoothScrollToPosition(0);
                        } else if(view instanceof NestedScrollView){
                            ((NestedScrollView) view).smoothScrollTo(0, 0);
                        }
                    }
                }
                return super.onDoubleTap(e);
            }

        });

    }

    private void createView() {

        if (mView != null)
            return;

        if (DEBUG)
            Log.d(TAG, "createView");

        mView = new View(mContext);
        mView.setBackgroundColor(DEBUG ? 0x33ff0000 : 0x00ffffff);
        mView.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mGestureDetector.onTouchEvent(event);
                return mView.onTouchEvent(event);
            }
        });

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
        lp.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;
        lp.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
        lp.width = (int) (TOUCH_BOUND_WIDTH * mContext.getResources().getDisplayMetrics().scaledDensity);
        lp.height = (int) (25 * mContext.getResources().getDisplayMetrics().scaledDensity);
        lp.format = PixelFormat.TRANSPARENT;
        lp.y = (int) (10 * mContext.getResources().getDisplayMetrics().scaledDensity);

        mWindowManager.addView(mView, lp);
    }

    private void removeView() {
        if (mView == null)
            return;

        if (DEBUG)
            Log.d(TAG, "removeView");

        mWindowManager.removeView(mView);
        mView = null;
    }

    public void addTargetScrollView(View view) {
        if (view == null)
            return;

        if (DEBUG)
            Log.d(TAG, "addTargetScrollView");

        mTargetScrollView.add(view);

        if (getTragetViewCount() > 0) {
            createView();
        }

    }

    public void removeTargetScrollView(View view) {
        if (view == null) {
            if (DEBUG)
                Log.e(TAG, "view is null");

            return;
        }

        if (DEBUG)
            Log.d(TAG, "removeTargetScrollView");

        mTargetScrollView.remove(view);

        if (getTragetViewCount() <= 0) {
            removeView();
        }

    }

    public void onDestory() {
        if (DEBUG)
            Log.d(TAG, "onDestory");

        removeView();
        mContext = null;
    }

    private int getTragetViewCount() {
        if (mTargetScrollView == null)
            return 0;

        if (DEBUG)
            Log.d(TAG, "getTragetViewCount : " + mTargetScrollView.size());

        return mTargetScrollView.size();
    }

}
