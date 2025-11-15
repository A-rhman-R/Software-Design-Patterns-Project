package Calc;

public class CalculationFacade {
    private boolean enableLogging = true;
    private boolean enableTiming = false;
    private Integer precision = 3;

    public CalculationFacade() {}

    // setters للتهيئة من GUI
    public void setEnableLogging(boolean enableLogging) { this.enableLogging = enableLogging; }
    public void setEnableTiming(boolean enableTiming) { this.enableTiming = enableTiming; }
    public void setPrecision(Integer precision) { this.precision = precision; }

    public double performCalculation(String operator, double n1, double n2) {
        Operation baseOp = OperationFactory.createOperation(operator);
        Operation decorated = OperationDecoratorFactory.applyDecorators(baseOp, enableLogging, enableTiming, precision);

        double result;
        try {
            result = decorated.calculate(n1, n2);
        } catch (ArithmeticException ex) {
            System.err.println("Calculation error: " + ex.getMessage());
            throw ex; // أو تعامل بطريقة مناسبة للـ GUI
        }

        // أضف للسجل 
        CalculationHistory.getInstance().addCalculation(n1 + " " + operator + " " + n2, result);

        return result;
    }
}
