package jp.co.net_marketing.checkredirects;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		result = (TextView) findViewById(R.id.result);

		try {
			Uri u = getIntent().getData();
			WebView content = (WebView) findViewById(R.id.content);
			content.setWebViewClient(new CheckClient());
			content.loadUrl(u.toString());
		} catch (Exception e) {
			Toast.makeText(this, "no data found", Toast.LENGTH_SHORT).show();
		}
	}

	Handler h = new Handler();
	TextView result;

	class CheckClient extends WebViewClient {
		@Override
		public void onPageStarted(WebView view, final String url, Bitmap favicon) {
			h.post(new Runnable() {
				@Override
				public void run() {
					result.setText(result.getText() + "\n" + url);
				}
			});

			super.onPageStarted(view, url, favicon);
		}
	}
}
