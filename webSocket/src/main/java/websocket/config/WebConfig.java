package websocket.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import websocket.dto.Product;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    static long id = 1L;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Autowired
    private SimpMessagingTemplate template;

    @Scheduled(fixedRate = 5000L)
    public void sendItem() {

        Product prod = new Product(++id, "Milk", 100.0f);
        template.convertAndSend("/topic", prod);
    }

}
