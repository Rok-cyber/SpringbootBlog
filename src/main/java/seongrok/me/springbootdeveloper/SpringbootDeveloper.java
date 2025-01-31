package seongrok.me.springbootdeveloper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableJpaAuditing
@SpringBootApplication
public class SpringbootDeveloper {
  public static void main(String[] args) {
    SpringApplication.run(SpringbootDeveloper.class, args);
  }

  @RestController
  public static class TestController {
    @GetMapping("/test")
    public String test() {
      return "test";
    }
  }
}
