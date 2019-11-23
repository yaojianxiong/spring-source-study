package org.springframework.beans.factory.yjx;

/**
 * 2019/11/19 11:25
 *
 * @author: yjx
 * @since 1.0
 */
public class YjxBean {

    private Integer age;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "YjxBean{" +
                "age=" + age +
                '}';
    }
}
