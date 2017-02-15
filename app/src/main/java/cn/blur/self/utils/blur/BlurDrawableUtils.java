package cn.blur.self.utils.blur;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import cn.blur.self.BaseApplication;


/**
 * Created by Administrator on 2016/9/9.
 * <p>
 * 高斯模糊 处理工具类
 */

public class BlurDrawableUtils {
    static int mDownScaleFactor = 12;
    static int mBlurRadius = 18;


    /**
     * @param mParent 负容器
     */
    public static void setBlurBack(final ViewGroup mParent, String url_bg) {
//        ObjectAnimator.ofFloat(mParent, "alpha", 0.5f, 0.6f, 0.8f).setDuration(800).start();

        ImageRequest imageRequest = ImageRequestBuilder
                .newBuilderWithSource(Uri.parse(url_bg))
                .setProgressiveRenderingEnabled(true)
                .build();
        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        DataSource<CloseableReference<CloseableImage>>
                dataSource = imagePipeline.fetchDecodedImage(imageRequest, mParent.getContext());

        dataSource.subscribe(new BaseBitmapDataSubscriber() {
            @Override
            protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
            }

            @Override
            protected void onNewResultImpl(final Bitmap loadedImage) {
                if (loadedImage != null) {
                    BaseApplication.getInstance().post(new Runnable() {
                        @Override
                        public void run() {
                            mParent.setBackgroundDrawable(new BitmapDrawable(loadedImage));
                            ImageView mBlurredImageView = new ImageView(mParent.getContext());
                            mBlurredImageView.setScaleType(ImageView.ScaleType.FIT_XY);
                            mParent.removeAllViews();
                            mParent.addView(mBlurredImageView, 0);
                            Bitmap bitmap = loadBitmapFromView(mParent);
                            if (bitmap != null) {
                                bitmap = scaleBitmap(bitmap);
                                bitmap = Blur.fastblur(mParent.getContext(), bitmap, mBlurRadius, false);
                                mBlurredImageView.setImageBitmap(bitmap);
                                mBlurredImageView.setVisibility(View.VISIBLE);
                            }
                        }
                    });
                }
            }
        }, CallerThreadExecutor.getInstance());
    }

    private static Bitmap loadBitmapFromView(View mView) {
        int width = mView.getWidth();

        if (width <= 0) {
            return null;
        }
        Bitmap b = Bitmap.createBitmap(
                mView.getWidth(),
                mView.getHeight(),
                Bitmap.Config.ARGB_4444);

        Canvas c = new Canvas(b);
        mView.draw(c);

        return b;
    }

    private static Bitmap scaleBitmap(Bitmap myBitmap) {

        int width = (myBitmap.getWidth() / mDownScaleFactor);
        int height = (myBitmap.getHeight() / mDownScaleFactor);

        return Bitmap.createScaledBitmap(myBitmap, width, height, false);
    }
}
