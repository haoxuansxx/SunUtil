package annotation;

import java.lang.annotation.Repeatable;

/**
 * 自定义重复注解
 */
@Repeatable(Authors.class)
@interface Author {
    String name();
}

@interface Authors {
    Author[] value();
}