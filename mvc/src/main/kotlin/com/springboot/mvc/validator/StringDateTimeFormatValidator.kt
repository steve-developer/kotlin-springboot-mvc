package com.springboot.mvc.validator

import com.springboot.mvc.annotation.StringDateTimeFormat
import java.lang.Exception
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class StringDateTimeFormatValidator : ConstraintValidator<StringDateTimeFormat, String>{

    private var pattern : String?=null

    override fun initialize(constraintAnnotation: StringDateTimeFormat?) {
        pattern = constraintAnnotation?.pattern
    }

    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {

        return try{
            LocalDateTime.parse(value, DateTimeFormatter.ofPattern(pattern))
            true
        }catch (e: Exception){
            false
        }
    }

}