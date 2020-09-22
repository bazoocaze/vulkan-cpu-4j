package com.github.bazoocaze.vulkancpu4j.vulkan.internal.instance;

import com.github.bazoocaze.vulkancpu4j.util.OutRef;
import com.github.bazoocaze.vulkancpu4j.vulkan.VkInstance;
import com.github.bazoocaze.vulkancpu4j.vulkan.VkInstanceCreateInfo;
import com.github.bazoocaze.vulkancpu4j.vulkan.enums.VkResult;

public interface InstanceFactory {

    VkResult create(VkInstanceCreateInfo createInfo, OutRef<VkInstance> instance);

}
