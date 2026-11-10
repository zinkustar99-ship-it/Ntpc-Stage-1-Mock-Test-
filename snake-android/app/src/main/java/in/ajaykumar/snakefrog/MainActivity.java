package in.ajaykumar.snakefrog;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings;
import android.webkit.WebResourceRequest;
import android.view.KeyEvent;
import android.widget.Toast;

public class MainActivity extends Activity {
    private WebView webView;
    private static final String GAME_URL = "https://zinkustar99-ship-it.github.io/Ntpc-Stage-1-Mock-Test-/snake-game/";
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        webView = new WebView(this);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setMediaPlaybackRequiresUserGesture(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                String host = request.getUrl().getHost();
                if (host != null && host.equals("zinkustar99-ship-it.github.io")) return false;
                Toast.makeText(MainActivity.this, "यह लिंक ऐप के बाहर उपलब्ध है।", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        setContentView(webView);
        webView.loadUrl(GAME_URL);
    }
    @Override public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView != null && webView.canGoBack()) {
            webView.goBack(); return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override protected void onDestroy() {
        if (webView != null) webView.destroy();
        super.onDestroy();
    }
}
