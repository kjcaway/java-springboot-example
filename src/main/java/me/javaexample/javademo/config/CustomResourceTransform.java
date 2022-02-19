package me.javaexample.javademo.config;


import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.resource.ResourceTransformer;
import org.springframework.web.servlet.resource.ResourceTransformerChain;
import org.springframework.web.servlet.resource.TransformedResource;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;

@Component
public class CustomResourceTransform implements ResourceTransformer {

    @Value("${spring.profiles.active:default}")
    private String activeProfile;

    @Value("${otherservice.url:null}")
    private String serviceUrl;

    @Override
    public Resource transform(HttpServletRequest request, Resource resource, ResourceTransformerChain transformerChain) throws IOException {
        String resourceStr = IOUtils.toString(resource.getInputStream(), UTF_8);
        resourceStr = resourceStr.replace("</body>", "<div style=\"position: absolute; bottom: 5px;\">Author</div>\n</body>");
        resourceStr = resourceStr.replace("##CURRENT_ENV##", activeProfile);
        resourceStr = resourceStr.replace("##OTHER_SERVICE_URL##", serviceUrl);

        return new TransformedResource(resource, resourceStr.getBytes());
    }
}
