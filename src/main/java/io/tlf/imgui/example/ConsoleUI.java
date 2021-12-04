package io.tlf.imgui.example;

import imgui.ImGui;
import imgui.ImVec2;
import imgui.extension.texteditor.TextEditor;
import imgui.flag.ImGuiFocusedFlags;
import imgui.flag.ImGuiKey;
import imgui.type.ImBoolean;
import imgui.type.ImString;

public class ConsoleUI extends ImGuiGui {
    TextEditor editor = new TextEditor();

    ImString command = new ImString();
    ImVec2 contentSize = new ImVec2();
    ImBoolean autoScroll = new ImBoolean();

    boolean requestFocus = true;
    boolean firstShowing = true;

    ConsoleUI() {
        this("server-console-ui");
    }

    ConsoleUI(String name) {
        setName(name);

        editor.setReadOnly(true);
        autoScroll.set(true);
    }
    
    @Override
    public void update() {
        if (firstShowing) {
            ImGui.setNextWindowSize(600, 400);
        }
        ImGui.begin("Server Console");
        /*
        ImGui.getWindowContentRegionMax(contentSize);
        if (contentSize.y > 50) {
            contentSize.y -= 50;
        }
        ImGui.beginChild("console-editor", contentSize.x, contentSize.y);
        if (autoScroll.get()) {
            editor.setCursorPosition(editor.getTotalLines(), 0);
        }
        editor.render("Server Console Editor");
        ImGui.endChild();
        
         */
        if (requestFocus) {
            //ImGui.setKeyboardFocusHere(0);
            requestFocus = false;
        }
        ImGui.inputText("", command);
        /*
        ImGui.sameLine();
        if (ImGui.button("Enter")
                || (ImGui.isWindowFocused(ImGuiFocusedFlags.RootAndChildWindows)
                && ImGui.isKeyPressed(ImGui.getKeyIndex(ImGuiKey.Enter)))) {
            send(command.get());
            command.clear();
            requestFocus = true;
        }
        ImGui.sameLine();
        ImGui.checkbox("Auto Scroll", autoScroll);
        
         */
        ImGui.end();

        if (firstShowing) {
            firstShowing = false;
        }
    }

    private void send(String command) {
        command = command.trim();
        if (command.isEmpty()) {
            return;
        }
        log(">" + command + "\n");
        System.out.println("Sending command: " + command);
    }

    private void log(String msg) {
        editor.setText(editor.getText() + msg);
    }

    @Override
    public void show() {
        
    }

    @Override
    public void hide() {

    }
}
