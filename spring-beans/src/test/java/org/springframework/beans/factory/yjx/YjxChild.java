package org.springframework.beans.factory.yjx;

/**
 * 2019/11/21 14:08
 *
 * @author: yjx
 * @since 1.0
 */
public class YjxChild {

    private String name;

    public YjxChild(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "YjxChild{" +
                "name='" + name + '\'' +
                '}';
    }
}
