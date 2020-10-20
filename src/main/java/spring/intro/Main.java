package spring.intro;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.intro.config.AppConfig;
import spring.intro.model.User;
import spring.intro.service.UserService;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);
        userService.add(new User("user1", "111", "user1@mail.com"));
        userService.add(new User("user2", "222", "user2@mail.com"));
        userService.add(new User("user3", "333", "user3@mail.com"));
        System.out.println(userService.listUsers());
    }
}
