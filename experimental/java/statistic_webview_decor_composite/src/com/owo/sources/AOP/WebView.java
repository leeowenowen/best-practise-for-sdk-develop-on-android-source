package com.owo.sources.AOP;


public class WebView {
	public static interface WebViewClient {
		void onPageStart();

		void onPageEnd();

		void showJSDialog(String title, String content);
	}

	public void load(WebViewClient client) {
		client.onPageStart();
		client.showJSDialog("title", "content");
		client.onPageEnd();
	}
}
