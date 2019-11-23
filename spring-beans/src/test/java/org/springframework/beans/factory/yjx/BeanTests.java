package org.springframework.beans.factory.yjx;

import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.Resource;

import static org.springframework.tests.TestResourceUtils.qualifiedResource;

/**
 * 2019/11/19 11:26
 *
 * @author: yjx
 * @since 1.0
 */
public class BeanTests {

    private static final Class<?> CLASS = BeanTests.class;
    private static final Resource beans = qualifiedResource(CLASS, "beans.xml");
    private static final Resource factoryBean = qualifiedResource(CLASS, "factoryBeans.xml");

    //如果有Id，则id对应的名称是bean的最终名称，也就是容器中对应的key
    //如果只设置了name属性，则name属性的第一个值为容器中对应的key
    //如果没有name 和id属性，则使用默认的生产规则，如org.springframework.beans.factory.yjx.YjxBeanTests + # + count
    @Test
    public void testAlis() {

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        new XmlBeanDefinitionReader(beanFactory).loadBeanDefinitions(beans);
        Object hello = beanFactory.getBean("hello");
        Object yjx = beanFactory.getBean("yjx");
        Object yjxBeanTests = beanFactory.getBean("yjxBeanTests");
        System.out.println(yjx);
    }

    @Test
    public void testFactoryBean() {

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        new XmlBeanDefinitionReader(beanFactory).loadBeanDefinitions(factoryBean);
        Object yjx = beanFactory.getBean("yjxFactoryBeanTests");
        System.out.println(yjx);
        //继续从缓存中获取
        Object yjx1 = beanFactory.getBean("yjxFactoryBeanTests");
    }

    @Test
    public void testFactoryMethod() {

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        new XmlBeanDefinitionReader(beanFactory).loadBeanDefinitions(factoryBean);
        Object yjx = beanFactory.getBean("yjxChild");

        Object yjx1 = beanFactory.getBean("yjxChild1");
        System.out.println(yjx);
        System.out.println(yjx1);
    }
}
