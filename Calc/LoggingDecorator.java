package Calc;

/**
 * يسجل العملية إلى System.out أو يمكن تغييره ليكتب في ملف / history
 */
public class LoggingDecorator extends OperationDecorator {

    public LoggingDecorator(Operation wrapped) {
        super(wrapped);
    }

    @Override
    public double calculate(double num1, double num2) {
        double result = super.calculate(num1, num2);
        // تسجيل مبسط؛ يمكنك تعديل التنسيق أو توجيه السجل لملف أو CalculationHistory
        System.out.println("[LOG] " + num1 + " " + wrapped.getSymbol() + " " + num2 + " = " + result);
        return result;
    }
}
