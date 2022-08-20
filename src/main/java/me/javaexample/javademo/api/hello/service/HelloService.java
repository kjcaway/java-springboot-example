package me.javaexample.javademo.api.hello.service;

import me.anno.AnnoAop;
import me.javaexample.javademo.annotation.ForceReturnAnnotation;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    @ForceReturnAnnotation
    public Object forceReturnExam(){
        String result = "forceReturnExam";
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    @AnnoAop
    public Object anno(){
        String result = "anno";
        return result;
    }
}
