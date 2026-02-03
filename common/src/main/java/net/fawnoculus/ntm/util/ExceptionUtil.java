package net.fawnoculus.ntm.util;

import net.fawnoculus.ntm.NtmConfig;
import org.jetbrains.annotations.NotNull;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtil {
    public static @NotNull String makePretty(@NotNull Throwable throwable) {
        return makePretty(throwable, NtmConfig.PRINT_STACKTRACE.getValue());
    }

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
}
