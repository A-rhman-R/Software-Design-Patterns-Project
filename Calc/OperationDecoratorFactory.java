package Calc;

import java.util.ArrayList;
import java.util.List;

/**
 * يزود طريقة تركيب سلسلة من الديكوريتورات على Operation واحد
 * بناءً على إعدادات بسيطة.
 */
public class OperationDecoratorFactory {

    public static Operation applyDecorators(Operation base, boolean enableLogging,
                                            boolean enableTiming, Integer precision) {
        Operation op = base;
        // نطبق من الخارج للداخل أو العكس — هنا نطبق Logging أولاً ثم Precision ثم Timing
        // فهذا يعني أن آخر ديكور يلف النتيجة النهائية حسب الترتيب.
        if (enableLogging) {
            op = new LoggingDecorator(op);
        }
        if (enableTiming) {
            op = new TimingDecorator(op);
        }
        if (precision != null) {
            op = new PrecisionDecorator(op, precision);
        }
        return op;
    }

    // بديل: تطبيق ديكور واحد فقط
    public static Operation withLogging(Operation base) {
        return new LoggingDecorator(base);
    }
}
