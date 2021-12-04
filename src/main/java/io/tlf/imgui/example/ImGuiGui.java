package io.tlf.imgui.example;

public class ImGuiGui {
    
    private String name = "unknown-gui";
    private Main main;

    public ImGuiGui() {

    }

    public ImGuiGui(String name) {
        this.name = name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public void update() {
        
    }

    public void show() {
        
    }
    
    public void hide() {
        
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
