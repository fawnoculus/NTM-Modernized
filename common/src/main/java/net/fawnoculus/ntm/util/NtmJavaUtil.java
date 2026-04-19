package net.fawnoculus.ntm.util;

import net.fawnoculus.ntm.NtmConfig;
import org.jetbrains.annotations.NotNull;

import java.io.PrintWriter;
import java.io.StringWriter;

// Generic Stuff to make Java more bearable
public class NtmJavaUtil {
    /**
     * Makes a {@link Throwable} look pretty for logging
     * (I know that you can just pass a Throwable directly to a logger,
     * but this may also be used for output to the chat and allows for disabling the stacktrace)
     *
     * @see NtmJavaUtil#makePretty(Throwable, boolean)
     */
    public static @NotNull String makePretty(@NotNull Throwable throwable) {
        return makePretty(throwable, NtmConfig.PRINT_STACKTRACE.getValue());
    }

    /**
     * Makes a {@link Throwable} look pretty for logging
     * (I know that you can just pass a Throwable directly to a logger,
     * but this may also be used for output to the chat and allows for disabling the stacktrace)
     */
    public static @NotNull String makePretty(@NotNull Throwable throwable, boolean stacktrace) {
        if (stacktrace) {
            StringWriter writer = new StringWriter();
            writer.append(throwable.toString());
            writer.append('\n');

            PrintWriter printWriter = new PrintWriter(writer);
            throwable.printStackTrace(printWriter);
            printWriter.flush();
            printWriter.close();

            return writer.toString();
        }

        return throwable.toString();
    }

    /**
     * Creates a {@link VarArgFunction} to be used in context where you need to make a functional interface that will always just return a value
     * this can be used by using "{@linkplain NtmJavaUtil NtmJavaUtil.}{@linkplain NtmJavaUtil#valueFunc(Object) valueFunc(VALUE_HERE)}{@linkplain VarArgFunction#apply(Object...) ::apply}"
     *
     * @param value the value that will be returned by the created function
     */
    @SuppressWarnings("JavadocDeclaration")
    public static <T> VarArgFunction<T> valueFunc(T value) {
        return ignored -> value;
    }

    public interface VarArgFunction<T> {
        T apply(Object... objects);
    }
}
