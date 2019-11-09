package org.springframework.context.annotation.spryjxtest.ext;

import java.lang.annotation.*;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CustomizeComponent {
    String value() default "";
}