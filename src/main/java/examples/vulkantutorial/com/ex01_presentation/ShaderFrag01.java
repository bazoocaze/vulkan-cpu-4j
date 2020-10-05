package examples.vulkantutorial.com.ex01_presentation;

import com.github.bazoocaze.vulkancpu4j.vulkan.glsl.ShaderLayout;
import com.github.bazoocaze.vulkancpu4j.vulkan.glsl.ShaderLayoutType;
import org.joml.Vector3f;
import org.joml.Vector4f;

public class ShaderFrag01 {

    @ShaderLayout(type = ShaderLayoutType.In)
    public Vector3f fragColor;

    @ShaderLayout(location = 0, type = ShaderLayoutType.Out)
    public Vector4f outColor;

    public void main() {
        outColor = new Vector4f(fragColor, 1.0f);
    }
}
