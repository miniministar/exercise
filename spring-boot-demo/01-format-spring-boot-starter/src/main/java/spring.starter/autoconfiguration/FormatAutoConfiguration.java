package spring.starter.autoconfiguration;


import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import spring.starter.format.FormatProcessor;
import spring.starter.format.JsonFormatProcessor;
import spring.starter.format.StringFormatProcessor;

@Configuration
public class FormatAutoConfiguration {

    /**
     *
     * @return
     *
     */

    //metadata-auto....
    @ConditionalOnMissingClass("com.alibaba.fastjson.JSON")
    @Bean
    @Primary
    public FormatProcessor stringFormat(){
        return new StringFormatProcessor();
    }

    @ConditionalOnClass(name = "com.alibaba.fastjson.JSON")
    @Bean
    public FormatProcessor jsonFormat(){
        return new JsonFormatProcessor();
    }

}
