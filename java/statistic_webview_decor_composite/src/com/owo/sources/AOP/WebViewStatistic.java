package com.owo.sources.AOP;

public class WebViewStatistic {
    public static interface WebViewClient {
        void onPageStart();

        void onPageEnd();

        void showJSDialog(String title, String content);
    }

    static class WebView {
        public void load(WebViewClient client) {
            client.onPageStart();
            client.showJSDialog("title", "content");
            client.onPageEnd();
        }
    }

    static class StatisticProxy {
        public void doStatistic(String param) {
            System.out.println("StatisticProxy.doStatistic:" + param);
        }
    }

    static class LayerWebViewClient implements WebViewClient {
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

    static class StatisticInterceptor  implements WebViewClient {
        private final StatisticProxy mStatisticProxy = new StatisticProxy();
        private WebViewClient mClient;
        public StatisticInterceptor(WebViewClient client) {
            mClient = client;
        }

        @Override
        public void onPageStart() {
            mClient.onPageStart();
            mStatisticProxy.doStatistic("onPageStart");
        }

        @Override
        public void onPageEnd() {
            mClient.onPageEnd();
            mStatisticProxy.doStatistic("onPageEnd");
        }

        @Override
        public void showJSDialog(String title, String content) {
            mStatisticProxy.doStatistic("showJSDialog");
        }
    }

    public static void sampleMain() {
        {
            WebView webView = new WebView();
            webView.load(new StatisticInterceptor(new WebViewClient() {

                @Override
                public void showJSDialog(String title, String content) {
                    System.out.print("WebViewClient.showJSDialog");
                }

                @Override
                public void onPageStart() {
                    System.out.print("WebViewClient.onPageStart");
                }

                @Override
                public void onPageEnd() {
                    System.out.print("WebViewClient.onPageEnd");
                }
            }));
        }

    }
}
