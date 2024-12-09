package me.javaexample.javademo.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

@Component
public class CloseEventListener implements ApplicationListener<ContextClosedEvent> {

    private static final Logger logger = LogManager.getLogger(CloseEventListener.class);

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        logger.info("on applicaiton event : closed");
    }
}
