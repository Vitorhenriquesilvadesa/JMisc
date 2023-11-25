#version 330 core

layout(location = 0) in vec3 a_Pos;
layout(location = 1) in vec2 a_TexCoord;

out vec3 color;
out vec2 texCoord;

uniform mat4 u_ProjectionMatrix;
uniform mat4 u_TransformationMatrix;
uniform mat4 u_ViewMatrix;

void main() {
    vec4 pos = vec4(a_Pos, 1.0);
    gl_Position = u_ProjectionMatrix * u_TransformationMatrix * pos;
    color = a_Pos;
    texCoord = a_TexCoord;
}