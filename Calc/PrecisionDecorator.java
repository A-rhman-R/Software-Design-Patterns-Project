package Calc;

/**
 * يقوم بتقريب النتيجة إلى n منازل عشرية.
 */
public class PrecisionDecorator extends OperationDecorator {
    private final int decimals;

    public PrecisionDecorator(Operation wrapped, int decimals) {
        super(wrapped);
        if (decimals < 0) throw new IllegalArgumentException("decimals must be >= 0");
        this.decimals = decimals;
    }

    @Override
    public double calculate(double num1, double num2) {
        double raw = super.calculate(num1, num2);
        double scale = Math.pow(10, decimals);
        double rounded = Math.round(raw * scale) / scale;
        return rounded;
    }
}
