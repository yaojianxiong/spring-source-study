package org.springframework.context.annotation.spryjxtest;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 2019/10/29 10:34
 *
 * @author: yjx
 * @since 1.0
 */
public class TestLiteFullBean {
    @Test
    public void liteFullBeanTest(){
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(ComponentBean.class,ConfigurationBean.class);
        ctx.refresh();
        ComponentBean componentBean = ctx.getBean(ComponentBean.class);
        ConfigurationBean configurationBean = ctx.getBean(ConfigurationBean.class);
        System.out.println("ComponentBean----->"+componentBean);
        System.out.println("configurationBean----->"+configurationBean);

        Object bean1 = ctx.getBean("bean1");
        Object bean2 = ctx.getBean("bean2");
        System.out.println("bean1------->"+bean1);
        System.out.println("bean2------->"+bean2);
        System.out.println("ctx.bean1------>"+bean1.hashCode());
        System.out.println("ctx.bean2------>"+bean2.hashCode());
        ctx.close();
    }

    @Component
    static class ComponentBean {
        @Bean
        public ExampleBean bean1(){
            return new ExampleBean();
        }
        @Bean
        public String printCode1(ExampleBean bean1){
            System.out.println("printCode1()-------->"+bean1.hashCode());
            System.out.println("bean1()-------->"+bean1().hashCode());
            return "";
        }

    }
    @Configuration
    static class ConfigurationBean {
        @Bean
        public ExampleBean bean2(){
            return new ExampleBean();
        }
        @Bean
        public String printCode2(ExampleBean bean2){
            System.out.println("printCode2()-------->"+bean2.hashCode());
            System.out.println("bean2()-------->"+bean2().hashCode());
            return "";
        }
    }

    static class ExampleBean{
        public ExampleBean(){
            System.out.println("ExampleBean()-------->"+this.hashCode());
        }
    }
}
