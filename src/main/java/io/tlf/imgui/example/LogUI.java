package io.tlf.imgui.example;

import imgui.ImGui;
import imgui.ImVec2;
import imgui.extension.texteditor.TextEditor;
import imgui.type.ImBoolean;

public class LogUI extends ImGuiGui {
    TextEditor editor = new TextEditor();

    ImVec2 contentSize = new ImVec2();
    ImBoolean autoScroll = new ImBoolean();

    boolean firstShowing = true;

    LogUI() {
        this("server-log-ui");
    }

    LogUI(String name) {
        setName(name);
        editor.setReadOnly(true);
        autoScroll.set(true);
    }


    @Override
    public void update() {
        if (firstShowing) {
            ImGui.setNextWindowSize(600, 400);
        }
        ImGui.begin("Server Log");
        /*
        ImGui.getWindowContentRegionMax(contentSize);
        if (contentSize.y > 50) {
            contentSize.y -= 50;
        }
        
        ImGui.beginChild("log-editor", contentSize.x, contentSize.y);
        if (autoScroll.get()) {
            editor.setCursorPosition(editor.getTotalLines(), 0);
        }
        editor.render("Server Log Editor");
        ImGui.endChild();
        
         */
        //ImGui.checkbox("Auto Scroll", autoScroll);
        ImGui.end();

        if (firstShowing) {
            firstShowing = false;
        }
    }

    private void log(String msg) {
        editor.setText(editor.getText() + msg + "\n");
    }

    @Override
    public void show() {
        log("Window shown");
    }

    @Override
    public void hide() {
        log("Window hidden");
    }
}
