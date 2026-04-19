package net.fawnoculus.ntm.api.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that an annotated Element should only be used on the Client, as calling it on the Server may cause undefined behavior, or simply doesn't work correctly.<br>
 * This exits because on neoforge using @Environment (which is remapped to @OnlyIn by architectury) causes a warning because
 * neoforge doesn't support removing annotated elements from the runtime anymore.
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.PACKAGE})
public @interface ClientOnly {
}
