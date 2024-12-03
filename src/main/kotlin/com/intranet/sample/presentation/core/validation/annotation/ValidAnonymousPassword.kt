package com.intranet.sample.presentation.core.validation.annotation

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass

// 커스텀 어노테이션 예시

@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY_GETTER)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [AnonymousPasswordValidator::class])
annotation class ValidAnonymousPassword(
    val message: String = "비밀번호는 최소 4자리 이상, 공백금지",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)

class AnonymousPasswordValidator : ConstraintValidator<ValidAnonymousPassword, String> {
    override fun isValid(value: String?, context: ConstraintValidatorContext): Boolean =
        if (value.isNullOrBlank() || value.contains(" ")) false
        else value.length >= 4
}