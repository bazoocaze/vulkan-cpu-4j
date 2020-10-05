package com.github.bazoocaze.vulkancpu4j.glm;

public class vec4 {

    public float x;
    public float y;
    public float z;
    public float w;

    public vec4(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public vec4(vec2 input, float z, float w) {
        this.x = input.x;
        this.y = input.y;
        this.z = z;
        this.w = w;
    }

    public vec4(vec3 input, float w) {
        this.x = input.x;
        this.y = input.y;
        this.z = input.z;
        this.w = w;
    }
}
