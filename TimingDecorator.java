package Calc;

/**
 * يقيس زمن تنفيذ العملية ويطبع الوقت بالـ milliseconds.
 */
public class TimingDecorator extends OperationDecorator {

    public TimingDecorator(Operation wrapped) {
        super(wrapped);
    }

    @Override
    public double calculate(double num1, double num2) {
        long start = System.nanoTime();
        double result = super.calculate(num1, num2);
        long end = System.nanoTime();
        long durationMicros = (end - start) / 1000;
        System.out.println("[TIME] " + num1 + " " + wrapped.getSymbol() + " " + num2 +
                " -> " + durationMicros + " µs");
        return result;
    }
}
