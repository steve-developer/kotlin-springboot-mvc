package com.example.todo.annotation

import com.example.todo.validator.StringDateTimeFormatValidator
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Constraint(validatedBy = [StringDateTimeFormatValidator::class])
@Target(AnnotationTarget.FIELD,
        AnnotationTarget.PROPERTY_GETTER,
        AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class StringDateTimeFormat (
        val pattern: String = "yyyy-MM-dd hh:mm:ss",
        val message: String = "시간 형식이 유효하지 않습니다.",
        val groups: Array<KClass<*>> = [],
        val payload: Array<KClass<out Payload>> = []
)