package com.github.bazoocaze.vulkancpu4j.vulkan;

public class VkExtensionProperties {

    public final int specVersion;
    public final String extensionName;

    public VkExtensionProperties(int specVersion, String extensionName) {
        this.specVersion = specVersion;
        this.extensionName = extensionName;
    }

    public String getExtensionName() {
        return this.extensionName;
    }
}
