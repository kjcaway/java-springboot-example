package me.javaexample.javademo.config;


import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.resource.ResourceTransformer;
import org.springframework.web.servlet.resource.ResourceTransformerChain;
import org.springframework.web.servlet.resource.TransformedResource;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;


public class CustomResourceTransform implements ResourceTransformer {
    @Override
    public Resource transform(HttpServletRequest request, Resource resource, ResourceTransformerChain transformerChain) throws IOException {
        String html = IOUtils.toString(resource.getInputStream(), UTF_8);
        html = html.replace("</body>", "<div style=\"position: absolute; bottom: 5px;\">Kang</div>\n</body>");
        return new TransformedResource(resource, html.getBytes());
    }
}
