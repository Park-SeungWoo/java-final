package com.jeremy.frames.menus.helps;

import com.jeremy.frames.AbstractFrame;
import com.jeremy.core.constants.FontsNColors;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;

import javax.swing.*;
import java.awt.*;

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
            webView.getEngine().load("https://jeremy-park.notion.site/About-Project-1389b5e5a1ad481f85b4d8bb12279540?pvs=4");
        });
    }
}
