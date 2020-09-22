package com.github.bazoocaze.vulkancpu4j.vulkan;

public class VkLayerProperties {

    /** The name of the layer */
    public final String layerName;

    /** the Vulkan version the layer was written to */
    public final int specVersion;

    /** is the version of this layer. It is an integer, increasing with backward compatible changes. */
    public final int implementationVersion;

    /** provides additional details that can be used by the application to identify the layer. */
    public final String description;

    public VkLayerProperties(String layerName, int specVersion, int implementationVersion, String description) {
        this.layerName = layerName;
        this.specVersion = specVersion;
        this.implementationVersion = implementationVersion;
        this.description = description;
    }
}
