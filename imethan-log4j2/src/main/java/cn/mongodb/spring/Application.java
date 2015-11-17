package cn.mongodb.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

/**
 * Application.java
 *
 * @author Ethan Wong
 * @since JDK 1.7
 * @datetime 2015年9月29日上午10:07:57
 */
@Configuration
@ComponentScan
public class Application {

    @Bean
    MessageService mockMessageService() {
        return new MessageService() {
            public String getMessage() {
              return "Hello World!";
            }
        };
    }

  public static void main(String[] args) {
      ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
      MessagePrinter printer = context.getBean(MessagePrinter.class);
      printer.printMessage();
  }
}