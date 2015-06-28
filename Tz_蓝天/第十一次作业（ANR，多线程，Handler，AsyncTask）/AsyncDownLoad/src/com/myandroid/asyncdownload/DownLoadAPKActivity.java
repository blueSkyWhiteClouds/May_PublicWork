package com.myandroid.asyncdownload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class DownLoadAPKActivity extends Activity implements OnClickListener {

	private TextView tv_apk;
	private ProgressBar progressBar_apk;
	private Button downLoadAPK;
	private String apkUrl = "http://3g.sina.com.cn/tv/soft/weibo/weibo_10105011.apk";
	private final String ROOT = Environment.getExternalStorageDirectory()
			.getAbsolutePath();
	private File file;
	private int maxLength;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.apk);
		initView();
	}

	private void initView() {
		tv_apk = (TextView) findViewById(R.id.tv_apk);
		progressBar_apk = (ProgressBar) findViewById(R.id.progressBar_apk);
		downLoadAPK = (Button) findViewById(R.id.downLoadAPK);
		downLoadAPK.setOnClickListener(this);
	}

	public void onClick(View v) {

		Task task = new Task();
		task.execute(apkUrl);
	}

	private class Task extends AsyncTask<String, Integer, File> {

		// 后台加载
		@Override
		protected File doInBackground(String... params) {
			String url = params[0];
			File apkfile=null;
			try {
				apkfile = downLoadAPK(url);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return apkfile;
		}

		private File downLoadAPK(String url) throws IOException {
			URL u = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5000);
			if (conn.getResponseCode() == 200) {
				Log.i("INFO", conn.getResponseCode()+"");
				InputStream is = conn.getInputStream();
				maxLength=conn.getContentLength();
				progressBar_apk.setMax(maxLength);
				String apkName = url.substring(url.lastIndexOf("/"));
				String path = ROOT + "/" + apkName;
				FileOutputStream os = new FileOutputStream(path);
				file = new File(path);
				int len = 0;
				byte[] buffer = new byte[1024];
				while ((len = is.read(buffer)) != -1) {
					os.write(buffer, 0, len);
					this.publishProgress(len);
				}
				os.close();
				is.close();
				return file;
			}
			return null;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(File result) {
			super.onPostExecute(result);
			Log.i("INFO", ""+result.getName());
			Intent intent=new Intent(Intent.ACTION_VIEW);
			intent.setDataAndType(Uri.fromFile(result), "application/vnd.android.package-archive");
			DownLoadAPKActivity.this.startActivity(intent);
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
			int len=values[0];
			progressBar_apk.setProgress(progressBar_apk.getProgress()+len);
			String text="下载进度："+100*progressBar_apk.getProgress()/maxLength+"%";
			tv_apk.setText(text);
		}

		@Override
		protected void onCancelled() {
			// TODO Auto-generated method stub
			super.onCancelled();
		}

	}

}
