package com.vr_mu.vrmu.views.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment {
    protected static final String TAG = "BaseFragment";

    /**
     * 当前依附的activity
     */
    protected FragmentActivity mActivity;

    /**
     * 根view
     */
    protected View mRootView;

    /**
     * 是否对用户可见
     */
    protected boolean mIsVisible;

    /**
     * 是否加载完成
     * 当执行完oncreatview,View的初始化方法后方法后即为true
     */
    protected boolean mIsPrepare;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mActivity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("BaseFragment", "当前碎片=======:" + this + "，布局：" + mRootView);
        if (mRootView == null) {
            mRootView = inflater.inflate(setLayoutResouceId(), container, false);
        }
        initView();
        initData(getArguments());
        mIsPrepare = true;
        onLazyLoad();
        setListener();
        return mRootView;
    }

    /**
     * 初始化数据
     *
     * @param arguments 接收到的从其他地方传递过来的参数
     */
    protected void initData(Bundle arguments) {

    }

    /**
     * 初始化View
     */
    protected void initView() {
        Log.d(TAG, "initView>>>>>>>>>>>>>>: " + this);
    }

    /**
     * 设置监听事件
     */
    protected void setListener() {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        this.mIsVisible = isVisibleToUser;

        if (isVisibleToUser) {
            onVisibleToUser();
        }
    }

    /**
     * 用户可见时执行的操作
     */
    protected void onVisibleToUser() {
        if (mIsPrepare && mIsVisible) {
            onLazyLoad();
        }
    }

    /**
     * 懒加载，仅当用户可见切view初始化结束后才会执行
     */
    protected void onLazyLoad() {

    }

    @SuppressWarnings("unchecked")
    protected <T extends View> T findViewById(int id) {
        if (mRootView == null) {
            return null;
        }
        return (T) mRootView.findViewById(id);
    }

    /**
     * 设置根布局资源id
     */
    protected abstract int setLayoutResouceId();


}
