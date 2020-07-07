package com.example.spring.batch.persistence;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.persistence.Convert;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateTimeConverter;

@Target({ METHOD, FIELD, TYPE })
@Retention(RUNTIME)
@Convert(converter = LocalDateTimeConverter.class)
public @interface LocalDateTimeColumn {

}
