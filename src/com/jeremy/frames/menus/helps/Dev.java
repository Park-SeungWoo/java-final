package com.jeremy.frames.menus.helps;

import com.jeremy.frames.AbstractFrame;
import com.jeremy.core.constants.FontsNColors;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;

public class Dev extends AbstractFrame {
    // components
    private JFXPanel jfxPanelComp;
    public Dev(){
        this("Developer Introduction");
    }
    public Dev(String title) {
        super(title, 1200, 825);
    }

    @Override
    protected void setFrameConfig() {
        super.setFrameConfig();
        this.setBackground(FontsNColors.Colors.orange);
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

    private void runWebView() {
        Platform.runLater(() -> {
            WebView webView = new WebView();
            this.jfxPanelComp.setScene(new Scene(webView));
            webView.getEngine().load("https://jeremy-park.notion.site/About-me-portfolio-59f81123775d476a9b12444a5ca1a79e?pvs=4");
        });
    }

}
