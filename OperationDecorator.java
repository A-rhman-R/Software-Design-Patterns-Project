package Calc;

public class OperationDecorator implements Operation {
    protected Operation wrapped;

    public OperationDecorator(Operation op) {
        this.wrapped = op;
    }

    @Override
    public double calculate(double n1, double n2) {
        return wrapped.calculate(n1, n2);
    }

    @Override
    public String getSymbol() {
        return wrapped.getSymbol();
    }
}
