#version 330 core

in vec3 color;
in vec2 texCoord;

uniform sampler2D textureSampler;

out vec4 f_Color;

void main() {
    vec4 testColor = texture(textureSampler, texCoord);

    if(testColor.a == 0.0){
        discard;
    }

    f_Color = testColor;
}