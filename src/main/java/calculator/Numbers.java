package calculator;

public class Numbers {
    private double zahl1;
    private double zahl2;
    private double result; // Antworte Server
    private String operation; // Zeichen für Operationen: "+", "-", "*", "/"

    public Numbers(double zahl1, double zahl2, String operation) {
        this.zahl1 = zahl1;
        this.zahl2 = zahl2;
        this.operation = operation;
    }


    public double getZahl1() {
        return zahl1;
    }

    public void setZahl1(double zahl1) {
        this.zahl1 = zahl1;
    }

    public double getZahl2() {
        return zahl2;
    }

    public void setZahl2(double zahl2) {
        this.zahl2 = zahl2;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }
}