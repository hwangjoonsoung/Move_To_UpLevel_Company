package me.mtuc.conference.custom_annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class WordCountValidator implements ConstraintValidator<WordCount, String> {

    private int min;
    private int max;

    @Override
    public void initialize(WordCount constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        // validation에서 not blank를 적용할 예정이기 때문에
        if (value == null) {
            return true;
        }

        String trimmed = value.trim();
        if (trimmed.isEmpty()) {
            return (min == 0);
        }

        String[] words = trimmed.split("\\s+");
        int wordCount = words.length;

        return wordCount >= min && wordCount <= max;
    }
}
