package com.owo.sources.AOP;

import com.owo.sources.AOP.WebView.WebViewClient;

public class Main {

	public static void main(String[] args) {
		{
			WebView webView = new WebView();
			webView.load(new StatisticWebViewClient(new WebViewClient() {

				@Override
				public void showJSDialog(String title, String content) {
					System.out.println("WebViewClient.showJSDialog");
				}

				@Override
				public void onPageStart() {
					System.out.println("WebViewClient.onPageStart");
				}

				@Override
				public void onPageEnd() {
					System.out.println("WebViewClient.onPageEnd");
				}
			}));
		}
		{
			WebView webView = new WebView();
			webView.load(new CompositeWebViewClient(new WebViewClient() {
				@Override
				public void onPageStart() {
					StatisticProxy.doStatistic("onPageStart");
				}

				@Override
				public void onPageEnd() {
					StatisticProxy.doStatistic("onPageEnd");
				}

				@Override
				public void showJSDialog(String title, String content) {
					StatisticProxy.doStatistic("showJSDialog");
				}
			}, new WebViewClient() {

				@Override
				public void showJSDialog(String title, String content) {
					System.out.println("WebViewClient.showJSDialog");
				}

				@Override
				public void onPageStart() {
					System.out.println("WebViewClient.onPageStart");
				}

				@Override
				public void onPageEnd() {
					System.out.println("WebViewClient.onPageEnd");
				}
			}));
		}

	}
}
