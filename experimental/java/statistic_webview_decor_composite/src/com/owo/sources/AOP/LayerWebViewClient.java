package com.owo.sources.AOP;

import com.owo.sources.AOP.WebView.WebViewClient;

public class LayerWebViewClient implements WebViewClient {
	private WebViewClient mNext;

	public LayerWebViewClient(WebViewClient next) {
		mNext = next;
	}

	@Override
	public void onPageStart() {
		mNext.onPageStart();
	}

	@Override
	public void onPageEnd() {
		mNext.onPageEnd();
	}

	@Override
	public void showJSDialog(String title, String content) {
		mNext.showJSDialog(title, content);
	}
}
