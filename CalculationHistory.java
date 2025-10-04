package Calc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CalculationHistory {
    // المتغير الثابت instance - الجزء الأساسي في Singleton
    private static CalculationHistory instance;
    
    // قائمة لتخزين العمليات السابقة
    private final List<CalculationRecord> history;
    private final int maxSize = 50000; // أقصى عدد من السجلات المحفوظة
    
    // المُنشئ الخاص - يمنع الإنشاء المباشر من الخارج
    private CalculationHistory() {
        history = new ArrayList<>();
    }
    
    // الطريقة العامة الوحيدة للحصول على النسخة - Singleton Pattern
    public static synchronized CalculationHistory getInstance() {
        if (instance == null) {
            instance = new CalculationHistory();
        }
        return instance;
    }
    
    // إضافة عملية حسابية جديدة إلى التاريخ
    public void addCalculation(String expression, double result) {
        if (history.size() >= maxSize) {
            history.remove(0); // إزالة أقدم سجل إذا تجاوزنا الحد
        }
        history.add(new CalculationRecord(expression, result));
    }
    
    // الحصول على نسخة من التاريخ (لحماية البيانات الأصلية)
    public List<CalculationRecord> getHistory() {
        return new ArrayList<>(history);
    }
    
    // الحصول على آخر عملية
    public CalculationRecord getLastCalculation() {
        if (history.isEmpty()) {
            return null;
        }
        return history.get(history.size() - 1);
    }
    
    // مسح التاريخ بالكامل
    public void clearHistory() {
        history.clear();
    }
    
    // عدد العمليات المحفوظة
    public int getHistorySize() {
        return history.size();
    }
    
    // فئة داخلية تمثل سجل عملية حسابية
    public static class CalculationRecord {
        private final String expression;  // العملية (مثال: "5 + 3")
        private final double result;      // النتيجة
        private final LocalDateTime timestamp; // وقت التنفيذ
        
        public CalculationRecord(String expression, double result) {
            this.expression = expression;
            this.result = result;
            this.timestamp = LocalDateTime.now();
        }
        
        // Getters
        public String getExpression() { return expression; }
        public double getResult() { return result; }
        public LocalDateTime getTimestamp() { return timestamp; }
        
        @Override
        public String toString() {
            return String.format("[%s] %s = %.2f", 
                timestamp.format(DateTimeFormatter.ofPattern("HH:mm:ss")), 
                expression, result);
        }
        
        // نسخة مختصرة للعرض
        public String toShortString() {
            return String.format("%s = %.2f", expression, result);
        }
    }
}