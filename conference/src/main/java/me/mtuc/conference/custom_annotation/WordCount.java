package me.mtuc.conference.custom_annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = WordCountValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface WordCount {

    int min() default 0;
    int max();
    String message() default "단어 수가 허용된 범위에 있지 않습니다";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
