package com.example.yaohao.testproject.utils;

import android.app.Activity;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.yaohao.testproject.R;
import com.lzy.imagepicker.loader.ImageLoader;

import java.io.File;


/**
 * discription:拍照需要用到
 * Created by 宫成浩 on 2018/6/4.
 */

public class GlideImageLoader implements ImageLoader {
	@Override
	public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
		Glide.with(activity)//
				.load(Uri.fromFile(new File(path)))//
				.placeholder(R.drawable.zanwushuju)//
				.error(R.drawable.zanwushuju)//
				.into(imageView);
	}

	@Override
	public void displayImagePreview(Activity activity, String path, ImageView imageView, int width, int height) {
		Glide.with(activity)//
				.load(Uri.fromFile(new File(path)))//
				.placeholder(R.drawable.zanwushuju)//
				.error(R.drawable.zanwushuju)//
				.into(imageView);
	}

	@Override
	public void clearMemoryCache() {

	}
}
