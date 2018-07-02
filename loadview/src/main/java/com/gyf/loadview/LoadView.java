package com.gyf.loadview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Load view.
 *
 * @author geyifeng
 */
public class LoadView extends FrameLayout {

    private Context mContext;

    private ImageView mImageView;
    private TextView mTextView;
    private LinearLayout mLinearLayout;
    private ProgressBar mProgressBar;
    private View mLoadView;

    /**
     * The current status.
     */
    private LoadStatus mCurrentStatus = LoadStatus.UNDO;

    /**
     * The loading gravity.
     */
    private int mLoadingGravity;
    /**
     * The image text gravity.
     */
    private int mImageTextGravity;

    /**
     * The loading left margin.
     */
    private int mLoadingLeftMargin;
    /**
     * The loading top margin.
     */
    private int mLoadingTopMargin;
    /**
     * The loading right margin.
     */
    private int mLoadingRightMargin;
    /**
     * The loading bottom margin.
     */
    private int mLoadingBottomMargin;

    /**
     * The image text left margin.
     */
    private int mImageTextLeftMargin;
    /**
     * The image text top margin.
     */
    private int mImageTextTopMargin;
    /**
     * The image text right margin.
     */
    private int mImageTextRightMargin;
    /**
     * The image text bottom margin.
     */
    private int mImageTextBottomMargin;

    /**
     * The image left margin.
     */
    private int mImageLeftMargin;
    /**
     * The image top margin.
     */
    private int mImageTopMargin;
    /**
     * The image right margin.
     */
    private int mImageRightMargin;
    /**
     * The image bottom margin.
     */
    private int mImageBottomMargin;

    /**
     * The text left margin.
     */
    private int mTextLeftMargin;
    /**
     * The text top margin.
     */
    private int mTextTopMargin;
    /**
     * The text right margin.
     */
    private int mTextRightMargin;
    /**
     * The text bottom margin.
     */
    private int mTextBottomMargin;

    /**
     * The fail text.
     */
    private String mFailText;
    /**
     * The error net text.
     */
    private String mErrorNetText;
    /**
     * The empty text.
     */
    private String mEmptyText;

    /**
     * The fail res.
     */
    @DrawableRes
    private int mFailRes;
    /**
     * The error net res.
     */
    @DrawableRes
    private int mErrorNetRes;
    /**
     * The empty res.
     */
    @DrawableRes
    private int mEmptyRes;
    /**
     * The fail text color.
     */
    @ColorInt
    private int mFailTextColor;
    /**
     * The error net text color.
     */
    @ColorInt
    private int mErrorNetTextColor;
    /**
     * The empty text color.
     */
    @ColorInt
    private int mEmptyTextColor;
    /**
     * The fail image color.
     */
    @ColorInt
    private int mFailImageColor;
    /**
     * The error net image color.
     */
    @ColorInt
    private int mErrorNetImageColor;
    /**
     * The empty image color.
     */
    @ColorInt
    private int mEmptyImageColor;
    /**
     * The default loading color.
     */
    @ColorInt
    private int mDefaultLoadingColor = 0;
    /**
     * The text size.
     */
    private float mTextSize;

    /**
     * The fail image color enabled.
     */
    private boolean mFailImageColorEnabled;
    /**
     * The error net image color enabled.
     */
    private boolean mErrorNetImageColorEnabled;
    /**
     * The empty image color enabled.
     */
    private boolean mEmptyImageColorEnabled;
    /**
     * The on loading listener.
     */
    private OnLoadingListener mOnLoadingListener;
    /**
     * The is loading.
     */
    private boolean mIsLoading;
    /**
     * The is default loading.
     */
    private boolean mIsDefaultLoading = true;
    /**
     * The loading view width.
     */
    private int mLoadingViewWidth;
    /**
     * The loading view height.
     */
    private int mLoadingViewHeight;

    /**
     * Instantiates a new Load view.
     *
     * @param context the context
     */
    public LoadView(@NonNull Context context) {
        this(context, null);
    }

    /**
     * Instantiates a new Load view.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public LoadView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.LoadView);

        //获得Loading的Gravity
        mLoadingGravity = typedArray.getInt(R.styleable.LoadView_load_loading_gravity,
                typedArray.getInt(R.styleable.LoadView_load_gravity,
                        LoadManager.getInstance().getLoadingGravity()));
        //获得ImageText的Gravity
        mImageTextGravity = typedArray.getInt(R.styleable.LoadView_load_image_text_gravity,
                typedArray.getInt(R.styleable.LoadView_load_gravity,
                        LoadManager.getInstance().getImageTextGravity()));

        //获得Loading的Margin
        mLoadingLeftMargin = (int) typedArray.getDimension(R.styleable.LoadView_load_loading_margin_left,
                typedArray.getDimension(R.styleable.LoadView_load_loading_margin,
                        typedArray.getDimension(R.styleable.LoadView_load_margin_left,
                                typedArray.getDimension(R.styleable.LoadView_load_margin,
                                        LoadManager.getInstance().getLoadingLeftMargin()))));
        mLoadingTopMargin = (int) typedArray.getDimension(R.styleable.LoadView_load_loading_margin_top,
                typedArray.getDimension(R.styleable.LoadView_load_loading_margin,
                        typedArray.getDimension(R.styleable.LoadView_load_margin_top,
                                typedArray.getDimension(R.styleable.LoadView_load_margin,
                                        LoadManager.getInstance().getLoadingTopMargin()))));
        mLoadingRightMargin = (int) typedArray.getDimension(R.styleable.LoadView_load_loading_margin_right,
                typedArray.getDimension(R.styleable.LoadView_load_loading_margin,
                        typedArray.getDimension(R.styleable.LoadView_load_margin_right,
                                typedArray.getDimension(R.styleable.LoadView_load_margin,
                                        LoadManager.getInstance().getLoadingRightMargin()))));
        mLoadingBottomMargin = (int) typedArray.getDimension(R.styleable.LoadView_load_loading_margin_bottom,
                typedArray.getDimension(R.styleable.LoadView_load_loading_margin,
                        typedArray.getDimension(R.styleable.LoadView_load_margin_bottom,
                                typedArray.getDimension(R.styleable.LoadView_load_margin,
                                        LoadManager.getInstance().getLoadingBottomMargin()))));

        //获得ImageText的Margin
        mImageTextLeftMargin = (int) typedArray.getDimension(R.styleable.LoadView_load_image_text_margin_left,
                typedArray.getDimension(R.styleable.LoadView_load_image_text_margin,
                        typedArray.getDimension(R.styleable.LoadView_load_margin_left,
                                typedArray.getDimension(R.styleable.LoadView_load_margin,
                                        LoadManager.getInstance().getImageTextLeftMargin()))));
        mImageTextTopMargin = (int) typedArray.getDimension(R.styleable.LoadView_load_image_text_margin_top,
                typedArray.getDimension(R.styleable.LoadView_load_image_text_margin,
                        typedArray.getDimension(R.styleable.LoadView_load_margin_top,
                                typedArray.getDimension(R.styleable.LoadView_load_margin,
                                        LoadManager.getInstance().getImageTextTopMargin()))));
        mImageTextRightMargin = (int) typedArray.getDimension(R.styleable.LoadView_load_image_text_margin_right,
                typedArray.getDimension(R.styleable.LoadView_load_image_text_margin,
                        typedArray.getDimension(R.styleable.LoadView_load_margin_right,
                                typedArray.getDimension(R.styleable.LoadView_load_margin,
                                        LoadManager.getInstance().getImageTextRightMargin()))));
        mImageTextBottomMargin = (int) typedArray.getDimension(R.styleable.LoadView_load_image_text_margin_bottom,
                typedArray.getDimension(R.styleable.LoadView_load_image_text_margin,
                        typedArray.getDimension(R.styleable.LoadView_load_margin_bottom,
                                typedArray.getDimension(R.styleable.LoadView_load_margin,
                                        LoadManager.getInstance().getImageTextBottomMargin()))));

        //获得Image的Margin
        mImageLeftMargin = (int) typedArray.getDimension(R.styleable.LoadView_load_image_margin_left,
                typedArray.getDimension(R.styleable.LoadView_load_image_margin,
                        LoadManager.getInstance().getImageLeftMargin()));
        mImageTopMargin = (int) typedArray.getDimension(R.styleable.LoadView_load_image_margin_top,
                typedArray.getDimension(R.styleable.LoadView_load_image_margin,
                        LoadManager.getInstance().getImageTopMargin()));
        mImageRightMargin = (int) typedArray.getDimension(R.styleable.LoadView_load_image_margin_right,
                typedArray.getDimension(R.styleable.LoadView_load_image_margin,
                        LoadManager.getInstance().getImageRightMargin()));
        mImageBottomMargin = (int) typedArray.getDimension(R.styleable.LoadView_load_image_margin_bottom,
                typedArray.getDimension(R.styleable.LoadView_load_image_margin,
                        LoadManager.getInstance().getImageBottomMargin()));

        //获得Text的Margin
        mTextLeftMargin = (int) typedArray.getDimension(R.styleable.LoadView_load_text_margin_left,
                typedArray.getDimension(R.styleable.LoadView_load_text_margin,
                        LoadManager.getInstance().getTextLeftMargin()));
        mTextTopMargin = (int) typedArray.getDimension(R.styleable.LoadView_load_text_margin_top,
                typedArray.getDimension(R.styleable.LoadView_load_text_margin,
                        LoadManager.getInstance().getTextTopMargin()));
        mTextRightMargin = (int) typedArray.getDimension(R.styleable.LoadView_load_text_margin_right,
                typedArray.getDimension(R.styleable.LoadView_load_text_margin,
                        LoadManager.getInstance().getTextRightMargin()));
        mTextBottomMargin = (int) typedArray.getDimension(R.styleable.LoadView_load_text_margin_bottom,
                typedArray.getDimension(R.styleable.LoadView_load_text_margin,
                        LoadManager.getInstance().getTextBottomMargin()));

        //获得加载失败显示的文字
        String failText = typedArray.getString(R.styleable.LoadView_load_text_fail);
        mFailText = failText != null ? failText : LoadManager.getInstance().getFailText();

        //获得加载网络错误显示的文字
        String errorNetText = typedArray.getString(R.styleable.LoadView_load_text_error_net);
        mErrorNetText = errorNetText != null ? errorNetText : LoadManager.getInstance().getErrorNetText();

        //获得加载数据为空显示的文字
        String emptyText = typedArray.getString(R.styleable.LoadView_load_text_empty);
        mEmptyText = emptyText != null ? emptyText : LoadManager.getInstance().getEmptyText();

        //获得加载失败显示的图片
        mFailRes = typedArray.getResourceId(R.styleable.LoadView_load_res_fail,
                LoadManager.getInstance().getFailRes());

        //获得加载网络错误显示的图片
        mErrorNetRes = typedArray.getResourceId(R.styleable.LoadView_load_res_error_net,
                LoadManager.getInstance().getErrorNetRes());

        //获得加载数据为空显示的图片
        mEmptyRes = typedArray.getResourceId(R.styleable.LoadView_load_res_empty,
                LoadManager.getInstance().getEmptyRes());

        //获得加载失败显示的文字颜色
        mFailTextColor = typedArray.getColor(R.styleable.LoadView_load_text_color_fail,
                typedArray.getColor(R.styleable.LoadView_load_text_color,
                        LoadManager.getInstance().getFailTextColor()));

        //获得加载网络错误显示的文字颜色
        mErrorNetTextColor = typedArray.getColor(R.styleable.LoadView_load_text_color_error_net,
                typedArray.getColor(R.styleable.LoadView_load_text_color,
                        LoadManager.getInstance().getErrorNetTextColor()));

        //获得加载数据为空显示的文字颜色
        mEmptyTextColor = typedArray.getColor(R.styleable.LoadView_load_text_color_empty,
                typedArray.getColor(R.styleable.LoadView_load_text_color,
                        LoadManager.getInstance().getEmptyTextColor()));

        //获得加载失败显示的图片颜色
        mFailImageColor = typedArray.getColor(R.styleable.LoadView_load_image_color_fail,
                typedArray.getColor(R.styleable.LoadView_load_image_color,
                        LoadManager.getInstance().getFailImageColor()));

        //获得加载网络错误显示的图片颜色
        mErrorNetImageColor = typedArray.getColor(R.styleable.LoadView_load_image_color_error_net,
                typedArray.getColor(R.styleable.LoadView_load_image_color,
                        LoadManager.getInstance().getErrorNetImageColor()));

        //获得加载数据为空显示的图片颜色
        mEmptyImageColor = typedArray.getColor(R.styleable.LoadView_load_image_color_empty,
                typedArray.getColor(R.styleable.LoadView_load_image_color,
                        LoadManager.getInstance().getEmptyImageColor()));

        //文字的大小
        mTextSize = typedArray.getDimension(R.styleable.LoadView_load_text_size,
                LoadUtils.sp2px(mContext, LoadManager.getInstance().getTextSize()));

        //默认loading的颜色
        mDefaultLoadingColor = typedArray.getColor(R.styleable.LoadView_load_default_loading_color,
                LoadManager.getInstance().getDefaultLoadingColor());
        if (mDefaultLoadingColor == 0) {
            mDefaultLoadingColor = LoadUtils.getDarkColorAccent(context);
        }

        //加载失败图片的颜色是否可以修改，默认不可以
        mFailImageColorEnabled = typedArray.getBoolean(R.styleable.LoadView_load_image_color_fail_enabled,
                typedArray.getBoolean(R.styleable.LoadView_load_image_color_enabled,
                        LoadManager.getInstance().isFailImageColorEnabled()));
        //加载w网络失败图片的颜色是否可以修改，默认不可以
        mErrorNetImageColorEnabled = typedArray.getBoolean(R.styleable.LoadView_load_image_color_error_net_enabled,
                typedArray.getBoolean(R.styleable.LoadView_load_image_color_enabled,
                        LoadManager.getInstance().isErrorNetImageColorEnabled()));
        //加载数据为空图片的颜色是否可以修改，默认不可以
        mEmptyImageColorEnabled = typedArray.getBoolean(R.styleable.LoadView_load_image_color_empty_enabled,
                typedArray.getBoolean(R.styleable.LoadView_load_image_color_enabled,
                        LoadManager.getInstance().isEmptyImageColorEnabled()));

        initView();
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        if (childCount > 3) {
            throw new IllegalStateException("LoadView can host only one ‘Loading’ child view");
        }
        if (childCount == 3) {
            mIsDefaultLoading = false;
            View loadingChild = getChildAt(2);
            removeView(loadingChild);
            removeView(getChildAt(1));
            addView(loadingChild);
            mLoadView = loadingChild;
            mLoadingViewWidth = mLoadView.getMeasuredWidth();
            mLoadingViewHeight = mLoadView.getMeasuredHeight();
            changeView();
        }

        for (int i = 0; i < getChildCount(); i++) {
            LayoutParams params = (LayoutParams) getChildAt(i).getLayoutParams();
            if (i == 0 || mIsDefaultLoading) {
                params.width = LayoutParams.WRAP_CONTENT;
                params.height = LayoutParams.WRAP_CONTENT;
            } else {
                params.width = mLoadingViewWidth;
                params.height = mLoadingViewHeight;
            }
        }

        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),
                MeasureSpec.getSize(heightMeasureSpec));
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            LayoutParams params = (LayoutParams) childView.getLayoutParams();
            if (i == 0) {
                params.gravity = mImageTextGravity;
                params.setMargins(mImageTextLeftMargin, mImageTextTopMargin,
                        mImageTextRightMargin, mImageTextBottomMargin);
            }
            if (i == 1) {
                params.gravity = mLoadingGravity;
                params.setMargins(mLoadingLeftMargin, mLoadingTopMargin,
                        mLoadingRightMargin, mLoadingBottomMargin);
            }
        }
        super.onLayout(changed, left, top, right, bottom);
    }

    private void initView() {

        mImageView = new ImageView(mContext);

        mTextView = new TextView(mContext);
        mTextView.setGravity(Gravity.CENTER);

        mLinearLayout = new LinearLayout(mContext);
        mLinearLayout.setOrientation(LinearLayout.VERTICAL);
        mLinearLayout.setClickable(true);

        mLinearLayout.addView(mImageView);
        mLinearLayout.addView(mTextView);

        addView(mLinearLayout);


        if (LoadManager.getInstance().getLoadingLayoutId() != 0) {
            mLoadView = LayoutInflater.from(mContext).inflate(LoadManager.getInstance().
                    getLoadingLayoutId(), this, false);
        }
        if (mLoadView == null) {
            mProgressBar = new ProgressBar(mContext);
            setDefaultLoadingColor();
            mLoadView = mProgressBar;
        }
        addView(mLoadView);
        changeView();
    }

    private void changeView() {
        switch (mCurrentStatus) {
            case UNDO:
            case SUCCESS:
                setClickable(false);
                mLinearLayout.setVisibility(GONE);
                mLoadView.setVisibility(GONE);
                mLinearLayout.setClickable(false);
                if (mOnLoadingListener != null && mIsLoading) {
                    mIsLoading = false;
                    mOnLoadingListener.onLoadingEnd(mLoadView);
                }
                break;
            case LOADING:
                setClickable(true);
                mLinearLayout.setVisibility(GONE);
                mLoadView.setVisibility(VISIBLE);
                mLinearLayout.setClickable(false);
                if (mOnLoadingListener != null) {
                    mIsLoading = true;
                    mOnLoadingListener.onLoadingStart(mLoadView);
                }
                break;
            case FAIL:
                setClickable(true);
                mImageView.setImageResource(mFailRes);
                if (mFailImageColorEnabled) {
                    setImageDrawable(mFailImageColor);
                }
                mTextView.setText(mFailText);
                mTextView.setTextColor(mFailTextColor);
                mLinearLayout.setVisibility(VISIBLE);
                mLoadView.setVisibility(GONE);
                mLinearLayout.setClickable(true);
                if (mOnLoadingListener != null && mIsLoading) {
                    mIsLoading = false;
                    mOnLoadingListener.onLoadingEnd(mLoadView);
                }
                break;
            case ERROR_NET:
                setClickable(true);
                mImageView.setImageResource(mErrorNetRes);
                if (mErrorNetImageColorEnabled) {
                    setImageDrawable(mErrorNetImageColor);
                }
                mTextView.setText(mErrorNetText);
                mTextView.setTextColor(mErrorNetTextColor);
                mLinearLayout.setVisibility(VISIBLE);
                mLoadView.setVisibility(GONE);
                mLinearLayout.setClickable(false);
                if (mOnLoadingListener != null && mIsLoading) {
                    mIsLoading = false;
                    mOnLoadingListener.onLoadingEnd(mLoadView);
                }
                break;
            case EMPTY:
                setClickable(true);
                mImageView.setImageResource(mEmptyRes);
                if (mEmptyImageColorEnabled) {
                    setImageDrawable(mEmptyImageColor);
                }
                mTextView.setText(mEmptyText);
                mTextView.setTextColor(mEmptyTextColor);
                mLinearLayout.setVisibility(VISIBLE);
                mLoadView.setVisibility(GONE);
                mLinearLayout.setClickable(false);
                if (mOnLoadingListener != null && mIsLoading) {
                    mIsLoading = false;
                    mOnLoadingListener.onLoadingEnd(mLoadView);
                }
                break;
            default:
                break;
        }
        changeImageTextView();
    }

    private void changeImageTextView() {
        LinearLayout.LayoutParams imageParams = (LinearLayout.LayoutParams) mImageView.getLayoutParams();
        imageParams.setMargins(mImageLeftMargin, mImageTopMargin, mImageRightMargin, mImageBottomMargin);
        mImageView.setLayoutParams(imageParams);
        mImageView.setVisibility(mImageView.getDrawable() == null ? GONE : VISIBLE);

        LinearLayout.LayoutParams textParams = (LinearLayout.LayoutParams) mTextView.getLayoutParams();
        textParams.setMargins(mTextLeftMargin, mTextTopMargin, mTextRightMargin, mTextBottomMargin);
        mTextView.setLayoutParams(textParams);
        mTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
        mTextView.setVisibility("".equals(mTextView.getText().toString().trim()) ? GONE : VISIBLE);
    }


    /**
     * 设置当前loadView的状态
     *
     * @param loadStatus the load status
     */
    public void setCurrentStatus(LoadStatus loadStatus) {
        mCurrentStatus = loadStatus;
        changeView();
    }

    /**
     * 获得当前loadView的状态
     *
     * @return the current status
     */
    public LoadStatus getCurrentStatus() {
        return mCurrentStatus;
    }

    /**
     * 设置加载中的view
     *
     * @param layoutId the layout id
     */
    public void setLoadingView(@LayoutRes int layoutId) {
        setLoadingView(LayoutInflater.from(mContext).inflate(layoutId, this, false));
    }

    /**
     * 设置加载中的view
     *
     * @param view the view
     */
    public void setLoadingView(View view) {
        setLoadingView(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    /**
     * 设置加载中的view
     *
     * @param view   the view
     * @param width  the width 单位px
     * @param height the height 单位px
     */
    public void setLoadingView(View view, int width, int height) {
        mIsDefaultLoading = false;
        mLoadingViewWidth = width;
        mLoadingViewHeight = height;
        removeView(mLoadView);
        this.mLoadView = view;
        addView(mLoadView);
    }

    /**
     * 设置失败的图片资源
     *
     * @param failRes the fail res
     */
    public void setFailRes(@DrawableRes int failRes) {
        this.mFailRes = failRes;
        changeView();
    }

    /**
     * 设置网络错误的图片资源
     *
     * @param errorNetRes the error net res
     */
    public void setErrorNetRes(@DrawableRes int errorNetRes) {
        this.mErrorNetRes = errorNetRes;
        changeView();
    }


    /**
     * 设置无数据的图片资源
     *
     * @param emptyRes the empty res
     */
    public void setEmptyRes(@DrawableRes int emptyRes) {
        this.mEmptyRes = emptyRes;
        changeView();
    }

    /**
     * 设置加载失败文字
     *
     * @param failText the fail text
     */
    public void setFailText(String failText) {
        this.mFailText = failText;
        changeView();
    }

    /**
     * 设置加载成功文字
     *
     * @param errorNetText the error net text
     */
    public void setErrorNetText(String errorNetText) {
        this.mErrorNetText = errorNetText;
        changeView();
    }

    /**
     * 设置加载为空文字
     *
     * @param emptyText the empty text
     */
    public void setEmptyText(String emptyText) {
        this.mEmptyText = emptyText;
        changeView();
    }

    /**
     * 设置文字的颜色
     *
     * @param textColor the text color
     */
    public void setTextColor(@ColorInt int textColor) {
        mFailTextColor = textColor;
        mErrorNetTextColor = textColor;
        mEmptyTextColor = textColor;
        changeView();
    }

    /**
     * 设置失败展示的字体颜色
     *
     * @param failTextColor the fail text color
     */
    public void setFailTextColor(@ColorInt int failTextColor) {
        this.mFailTextColor = failTextColor;
        changeView();
    }

    /**
     * 设置网络加载错误展示的字体颜色
     *
     * @param errorNetTextColor the error net text color
     */
    public void setErrorNetTextColor(@ColorInt int errorNetTextColor) {
        this.mErrorNetTextColor = errorNetTextColor;
        changeView();
    }

    /**
     * 设置数据为空展示的字体颜色
     *
     * @param emptyTextColor the empty text color
     */
    public void setEmptyTextColor(@ColorInt int emptyTextColor) {
        this.mEmptyTextColor = emptyTextColor;
        changeView();
    }

    /**
     * 设置图片的颜色
     *
     * @param imageColor the image color
     */
    public void setImageColor(@ColorInt int imageColor) {
        this.mFailImageColor = imageColor;
        this.mErrorNetImageColor = imageColor;
        this.mEmptyImageColor = imageColor;
        changeView();
    }

    /**
     * 设置失败图片的颜色
     *
     * @param failImageColor the fail image color
     */
    public void setFailImageColor(@ColorInt int failImageColor) {
        this.mFailImageColor = failImageColor;
        changeView();
    }

    /**
     * 设置网络错误图片的颜色
     *
     * @param errorNetImageColor the error net image color
     */
    public void setErrorNetImageColor(@ColorInt int errorNetImageColor) {
        this.mErrorNetImageColor = errorNetImageColor;
        changeView();
    }

    /**
     * 设置数据为空图片的颜色
     *
     * @param emptyImageColor the empty image color
     */
    public void setEmptyImageColor(@ColorInt int emptyImageColor) {
        this.mEmptyImageColor = emptyImageColor;
        changeView();
    }

    /**
     * 设置默认的loading的颜色
     *
     * @param defaultLoadingColor the default loading color
     */
    public void setDefaultLoadingColor(@ColorInt int defaultLoadingColor) {
        this.mDefaultLoadingColor = defaultLoadingColor;
        setDefaultLoadingColor();
        changeView();
    }

    /**
     * android 5.0 支持修改progressBar颜色
     */
    private void setDefaultLoadingColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ColorStateList colorStateList = ColorStateList.valueOf(mDefaultLoadingColor);
            mProgressBar.setIndeterminateTintList(colorStateList);
            mProgressBar.setIndeterminateTintMode(PorterDuff.Mode.SRC_ATOP);
        }
    }

    /**
     * 设置字体大小,单位sp
     *
     * @param textSize the text size
     */
    public void setTextSize(int textSize) {
        this.mTextSize = LoadUtils.sp2px(mContext, textSize);
        changeView();
    }

    /**
     * 设置图片的颜色
     *
     * @param color the color
     */
    private void setImageDrawable(@ColorInt int color) {
        Drawable drawable = mImageView.getDrawable();
        if (drawable != null) {
            drawable.setColorFilter(color, PorterDuff.Mode.SRC_IN);
            mImageView.setImageDrawable(drawable);
        }
    }

    /**
     * 设置是否可以修改图片的颜色
     *
     * @param imageColorEnabled the image color enabled
     */
    public void setImageColorEnabled(boolean imageColorEnabled) {
        this.mFailImageColorEnabled = imageColorEnabled;
        this.mErrorNetImageColorEnabled = imageColorEnabled;
        this.mEmptyImageColorEnabled = imageColorEnabled;
        changeView();
    }

    /**
     * 设置是否可以修改失败图片的颜色
     *
     * @param failImageColorEnabled the fail image color enabled
     */
    public void setFailImageColorEnabled(boolean failImageColorEnabled) {
        this.mFailImageColorEnabled = failImageColorEnabled;
        changeView();
    }

    /**
     * 设置是否可以修改网络错误图片的颜色
     *
     * @param errorNetImageColorEnabled the error net image color enabled
     */
    public void setErrorNetImageColorEnabled(boolean errorNetImageColorEnabled) {
        this.mErrorNetImageColorEnabled = errorNetImageColorEnabled;
        changeView();
    }

    /**
     * 设置是否可以修改数据为空图片的颜色
     *
     * @param emptyImageColorEnabled the empty image color enabled
     */
    public void setEmptyImageColorEnabled(boolean emptyImageColorEnabled) {
        this.mEmptyImageColorEnabled = emptyImageColorEnabled;
        changeView();
    }

    /**
     * Sets margins.单位px
     *
     * @param left   the left
     * @param top    the top
     * @param right  the right
     * @param bottom the bottom
     */
    public void setMargins(int left, int top, int right, int bottom) {
        mImageTextLeftMargin = left;
        mImageTextTopMargin = top;
        mImageTextRightMargin = right;
        mImageTextBottomMargin = bottom;

        mLoadingLeftMargin = left;
        mLoadingTopMargin = top;
        mLoadingRightMargin = right;
        mLoadingBottomMargin = bottom;

        requestLayout();
    }

    /**
     * Sets image text margins.单位px
     *
     * @param left   the left
     * @param top    the top
     * @param right  the right
     * @param bottom the bottom
     */
    public void setImageTextMargins(int left, int top, int right, int bottom) {
        mImageTextLeftMargin = left;
        mImageTextTopMargin = top;
        mImageTextRightMargin = right;
        mImageTextBottomMargin = bottom;

        requestLayout();
    }

    /**
     * Sets loading margins.单位px
     *
     * @param left   the left
     * @param top    the top
     * @param right  the right
     * @param bottom the bottom
     */
    public void setLoadingMargins(int left, int top, int right, int bottom) {
        mLoadingLeftMargin = left;
        mLoadingTopMargin = top;
        mLoadingRightMargin = right;
        mLoadingBottomMargin = bottom;

        requestLayout();
    }

    /**
     * Sets imageView margins.单位px
     *
     * @param left   the left
     * @param top    the top
     * @param right  the right
     * @param bottom the bottom
     */
    public void setImageMargins(int left, int top, int right, int bottom) {
        mImageLeftMargin = left;
        mImageTopMargin = top;
        mImageRightMargin = right;
        mImageBottomMargin = bottom;
        changeView();
    }

    /**
     * Sets textView margins.单位px
     *
     * @param left   the left
     * @param top    the top
     * @param right  the right
     * @param bottom the bottom
     */
    public void setTextMargins(int left, int top, int right, int bottom) {
        mTextLeftMargin = left;
        mTextTopMargin = top;
        mTextRightMargin = right;
        mTextBottomMargin = bottom;
        changeView();
    }

    /**
     * 设置布局的位置
     *
     * @param gravity the gravity
     */
    public void setGravity(int gravity) {
        gravity = getCommonGravity(gravity);
        if (gravity != mLoadingGravity || gravity != mImageTextGravity) {
            requestLayout();
        }
        mLoadingGravity = gravity;
        mImageTextGravity = gravity;
    }

    /**
     * 设置Loading布局的位置
     *
     * @param gravity the gravity
     */
    public void setLoadingGravity(int gravity) {
        gravity = getCommonGravity(gravity);
        if (gravity != mLoadingGravity) {
            requestLayout();
        }
        mLoadingGravity = gravity;
    }

    /**
     * 设置图片和文字布局的位置
     *
     * @param gravity the gravity
     */
    public void setImageTextGravity(int gravity) {
        gravity = getCommonGravity(gravity);
        if (gravity != mImageTextGravity) {
            requestLayout();
        }
        mImageTextGravity = gravity;
    }

    private int getCommonGravity(int gravity) {
        if ((gravity & Gravity.RELATIVE_HORIZONTAL_GRAVITY_MASK) == 0) {
            gravity |= Gravity.START;
        }
        if ((gravity & Gravity.VERTICAL_GRAVITY_MASK) == 0) {
            gravity |= Gravity.TOP;
        }
        return gravity;
    }


    /**
     * 设置失败回调监听
     *
     * @param listener the listener
     */
    public void setOnFailClickListener(final OnLoadFailClickListener listener) {
        mLinearLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setCurrentStatus(LoadStatus.LOADING);
                if (listener != null) {
                    listener.onLoadFailClick();
                }
            }
        });
    }

    /**
     * 加载失败时的监听
     */
    public interface OnLoadFailClickListener {
        /**
         * 失败点击事件
         */
        void onLoadFailClick();
    }

    /**
     * 加载监听
     *
     * @param listener the listener
     */
    public void setOnLoadingListener(final OnLoadingListener listener) {
        mOnLoadingListener = listener;
    }

    /**
     * The interface On loading listener.
     */
    public interface OnLoadingListener {
        /**
         * 加载开始时的回调
         *
         * @param loadingView the loading view
         */
        void onLoadingStart(View loadingView);

        /**
         * 加载结束时的回调
         *
         * @param loadingView the loading view
         */
        void onLoadingEnd(View loadingView);
    }
}
