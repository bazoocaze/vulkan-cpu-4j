package com.github.bazoocaze.vulkancpu4j.vulkan.glsl;

public @interface ShaderLayout {

    int UNDEFINED_LOCATION = -1;

    ShaderLayoutType type();

    int location() default UNDEFINED_LOCATION;
}
