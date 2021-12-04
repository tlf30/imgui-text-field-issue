package io.tlf.imgui.example;

import imgui.ImGui;
import imgui.app.Application;
import imgui.app.Configuration;
import imgui.flag.ImGuiConfigFlags;
import imgui.type.ImBoolean;

import java.util.ArrayList;
import java.util.HashMap;

public class Main extends Application {
    
    ImBoolean showConsole = new ImBoolean(false);
    ImBoolean showLog = new ImBoolean(false);
    
    HashMap<String, ImGuiGui> guis = new HashMap<>();
    ArrayList<String> openUIs = new ArrayList<>();
    
    @Override
    protected void configure(Configuration config) {
        config.setTitle("Dear ImGui is Awesome!");
    }
    
    @Override
    public void preRun() {
        ImGui.getIO().addConfigFlags(ImGuiConfigFlags.DockingEnable);
    }

    @Override
    public void process() {
        ImGui.begin("Test Editors");
        if (ImGui.checkbox("Console", showConsole)) {
            if (showConsole.get()) {
                show("server-console-ui");
            } else {
                hide("server-console-ui");
            }
        }
        if (ImGui.checkbox("Log", showLog)) {
            if (showLog.get()) {
                show("server-log-ui");
            } else {
                hide("server-log-ui");
            }
        }
        ImGui.end();
        
        //Process UIs
        for (String ui : openUIs) {
            ImGuiGui gui = guis.get(ui);
            gui.update();
        }
    }
    
    public void register(ImGuiGui gui) {
        gui.setMain(this);
        guis.put(gui.getName(), gui);
    }
    
    public void show(String ui) {
        if (!guis.containsKey(ui)) {
            return;
        }
        ImGuiGui gui = guis.get(ui);
        gui.show();
        openUIs.add(ui);
    }
    
    public void hide(String ui) {
        if (!openUIs.contains(ui)) {
            return;
        }
       
        openUIs.remove(ui);
        ImGuiGui gui = guis.get(ui);
        gui.hide();
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.register(new ConsoleUI());
        main.register(new LogUI());
        launch(main);
    }
}