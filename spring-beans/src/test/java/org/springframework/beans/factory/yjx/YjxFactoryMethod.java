package org.springframework.beans.factory.yjx;

/**
 * 2019/11/21 14:10
 *
 * @author: yjx
 * @since 1.0
 */
public class YjxFactoryMethod {

    public static YjxChild createStaticYjxChild(){
        return new YjxChild("testStatic");
    }

    public  YjxChild createYjxChild(){
        return new YjxChild("test");
    }
}
