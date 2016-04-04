package com.owo.sources.AOP;

import com.owo.sources.AOP.WebView.WebViewClient;

public class StatisticWebViewClient implements WebViewClient {
	private WebViewClient mClient;

	public StatisticWebViewClient(WebViewClient client) {
		mClient = client;
	}

	@Override
	public void onPageStart() {
		mClient.onPageStart();
		StatisticProxy.doStatistic("onPageStart");
	}

	@Override
	public void onPageEnd() {
		mClient.onPageEnd();
		StatisticProxy.doStatistic("onPageEnd");
	}

	@Override
	public void showJSDialog(String title, String content) {
		mClient.showJSDialog(title, content);
		StatisticProxy.doStatistic("showJSDialog");
	}
}