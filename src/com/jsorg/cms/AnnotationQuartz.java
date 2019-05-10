package com.jsorg.cms;
 
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AnnotationQuartz {
    @Scheduled(cron = "0 0 12 * * ?")
    //需要注意@Scheduled这个注解，它可配置多个属性：cron\fixedDelay\fixedRate
    public void test() {
        System.out.println("...........");
    }
}