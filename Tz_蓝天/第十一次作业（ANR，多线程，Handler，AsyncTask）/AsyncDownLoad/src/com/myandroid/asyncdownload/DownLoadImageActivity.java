package com.myandroid.asyncdownload;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class DownLoadImageActivity extends Activity implements OnClickListener {

	private TextView textview;
	private ProgressBar progressBar;
	private Button downLoad;
	private ImageView imageview;
	private String imageUrl = "http://a.hiphotos.baidu.com/image/pic/item/0d338744ebf81a4c69b5cbd3d52a6059252da620.jpg";
	private final String ROOT = Environment.getExternalStorageDirectory()
			.getAbsolutePath();
	private int maxLength;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.image);
		initView();
	}

	private void initView() {
		textview = (TextView) findViewById(R.id.textview);
		progressBar = (ProgressBar) findViewById(R.id.progressBar);
		downLoad = (Button) findViewById(R.id.downLoad);
		imageview = (ImageView) findViewById(R.id.imageview);
		downLoad.setOnClickListener(this);
	}

	public void onClick(View v) {

		Task task = new Task();
		task.execute(imageUrl);
	}

	private class Task extends AsyncTask<String, Integer, Bitmap> {

		// 后台加载
		@Override
		protected Bitmap doInBackground(String... params) {
			String url = params[0];
			Bitmap bitmap = null;
			try {
				bitmap = downLoadImage(url);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return bitmap;
		}

		/**
		 * 下载图片
		 * 
		 * @param url
		 * @return
		 * @throws IOException
		 */
		private Bitmap downLoadImage(String url) throws IOException {
			URL u = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) u.openConnection(); // ͨ��url��ַ��Զ������
			conn.setRequestMethod("GET"); // 网络请求方式
			conn.setConnectTimeout(5000); // 网络请求超时

			if (conn.getResponseCode() == 200) {
				InputStream input = conn.getInputStream();
				maxLength = conn.getContentLength();// 获取图片的大小
				progressBar.setMax(maxLength);// 设置进度条最大值为图片的最大容量值
				// Bitmap bitmap=BitmapFactory.decodeStream(input);
				String imageName = url.substring(url.lastIndexOf("/"));
				FileOutputStream os = new FileOutputStream(ROOT + "/"
						+ imageName);
				int len = 0;
				byte[] buffer = new byte[1024];
				while ((len = input.read(buffer)) != -1) {
					os.write(buffer, 0, len);
					// 每读写一次，表示文件下载了len
					// 更新进度条
					this.publishProgress(len);
				}
				os.close();
				input.close();
				Bitmap bitmap = BitmapFactory
						.decodeFile(ROOT + "/" + imageName);
				return bitmap;
			}
			return null;
		}

		//取消任务
		@Override
		protected void onCancelled() {
			// TODO Auto-generated method stub
			super.onCancelled();
		}

		//加载完了以后回调的onPostExecute,实现原理是handler
		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);

			imageview.setImageBitmap(result);
		}

		//预加载
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

		//更新进度条UI，实现原理handler
		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
			int len = values[0];
			//进行进度条刻度更新
			progressBar.setProgress(progressBar.getProgress() + len);
			String text = "下载进度:" + 100 * progressBar.getProgress()
					/ maxLength + "%";
			textview.setText(text);
		}

	}

}