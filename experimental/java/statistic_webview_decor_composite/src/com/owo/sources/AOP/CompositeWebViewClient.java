package com.owo.sources.AOP;

import java.util.ArrayList;
import java.util.List;

import com.owo.sources.AOP.WebView.WebViewClient;

public class CompositeWebViewClient implements WebViewClient {
	private List<WebViewClient> mClients = new ArrayList<>();

	public CompositeWebViewClient(WebViewClient... clients) {
		for (WebViewClient client : clients) {
			mClients.add(client);
		}
	}

	@Override
	public void onPageStart() {
		for (WebViewClient client : mClients) {
			client.onPageStart();
		}
	}

	@Override
	public void onPageEnd() {
		for (WebViewClient client : mClients) {
			client.onPageEnd();
		}
	}

	@Override
	public void showJSDialog(String title, String content) {
		for (WebViewClient client : mClients) {
			client.showJSDialog(title, content);
		}
	}
}
