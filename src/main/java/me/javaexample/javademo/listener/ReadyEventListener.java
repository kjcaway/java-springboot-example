package me.javaexample.javademo.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ReadyEventListener implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger logger = LogManager.getLogger(ReadyEventListener.class);

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        logger.info("on applicaiton event : ready");
    }
}

