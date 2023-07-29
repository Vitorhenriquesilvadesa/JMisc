package engine;

import imgui.ImGui;

public class ImGuiLayer {

    private boolean showText = false;

    public void imgui() {
        ImGui.begin("Cool Window");

        if (ImGui.button("I am a button")) {
            showText = true;
        }

        if (showText) {
            ImGui.text("You clicked a button");
            ImGui.sameLine();
            if (ImGui.button("Showing text")) {
                showText = false;
            }
        }

        ImGui.end();
    }
}
