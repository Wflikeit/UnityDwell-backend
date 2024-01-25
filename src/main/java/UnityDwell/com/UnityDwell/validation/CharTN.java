package UnityDwell.com.UnityDwell.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CharTNValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Size(min = 1, max = 1)
@Pattern(regexp = "[TN]")
@ReportAsSingleViolation
public @interface CharTN {
    String message() default "Must be 'T' or 'N'";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
