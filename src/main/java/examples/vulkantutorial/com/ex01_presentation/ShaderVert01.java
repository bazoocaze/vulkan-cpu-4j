package examples.vulkantutorial.com.ex01_presentation;

import com.github.bazoocaze.vulkancpu4j.vulkan.glsl.ShaderLayout;
import com.github.bazoocaze.vulkancpu4j.vulkan.glsl.ShaderLayoutType;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

public class ShaderVert01 {

    final Vector2f[] positions = new Vector2f[]{
            new Vector2f(0.0f, -0.5f),
            new Vector2f(0.5f, 0.5f),
            new Vector2f(-0.5f, 0.5f)
    };

    final Vector3f[] colors = new Vector3f[]{
            new Vector3f(1.0f, 0.0f, 0.0f),
            new Vector3f(0.0f, 1.0f, 0.0f),
            new Vector3f(0.0f, 0.0f, 1.0f),
    };

    @ShaderLayout(type = ShaderLayoutType.Out)
    public Vector3f fragColor;

    public Vector4f gl_Position;
    public int gl_VertexIndex;

    public void main() {
        gl_Position = new Vector4f(positions[gl_VertexIndex], 0.0f, 1.0f);
        fragColor = colors[gl_VertexIndex];
    }
}
