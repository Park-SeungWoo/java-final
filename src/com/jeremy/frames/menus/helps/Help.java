package com.jeremy.frames.menus.helps;

import com.jeremy.frames.AbstractFrame;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;

public class Help extends AbstractFrame {
    // components
    private JFXPanel jfxPanelComp;
    public Help(){
        this("Project Introduction");
    }
    public Help(String title) {
        super(title, 1200, 825);
    }

    @Override
    protected void setFrameConfig() {
        super.setFrameConfig();
        this.setLayout(null);
    }
    @Override
    protected void setComponentsConfig() {
        this.jfxPanelComp = new JFXPanel();
        this.jfxPanelComp.setBounds(0, 25, this.frameW, this.frameH);
        this.runWebView();
    }
    @Override
    protected void addComponentListener() {
    }
    @Override
    public void closeWindow() {
        this.setVisible(false);  // dispose 되어 java fx thread가 종료 되면 runLater()을 다시 호출해도 다시 켜지지 않는 듯 그냥 set visible false로 해서 숨겨주니 잘 됨
    }
    private void runWebView() {
        Platform.runLater(() -> {
            WebView webView = new WebView();
            jfxPanelComp.setScene(new Scene(webView));
            webView.getEngine().load("https://jeremy-park.notion.site/About-Project-1389b5e5a1ad481f85b4d8bb12279540?pvs=4");
        });
    }
}
