package es.upm.miw.iwvg_devops.lambdas;

public class ArithmeticUtils {
    public static Fraction multiplicate(Fraction a, Fraction b) {
        return new Fraction(a.getNumerator()*b.getNumerator(), a.getDenominator()*b.getDenominator());
    }

    public static Fraction divide(Fraction a, Fraction b) {
        Fraction f = new Fraction(a.getNumerator()*b.getDenominator(), a.getDenominator()*b.getNumerator());
        return f;
    }

    public static Fraction addition(Fraction a, Fraction b) {
        int mcm = mcm(a.getDenominator(),b.getDenominator());
        return new Fraction((((mcm/a.getDenominator()) * a.getNumerator()) + (((mcm/b.getDenominator()) * b.getNumerator()))),mcm);
    }

    private static int mcd(int num1, int num2) {

        int a = Math.max(num1, num2);
        int b = Math.min(num1, num2);

        int resultado = 0;
        do {
            resultado = b;
            b = a % b;
            a = resultado;
        } while (b != 0);

        return resultado;

    }

    private static int mcm(int num1, int num2) {

        int a = Math.max(num1, num2);
        int b = Math.min(num1, num2);

        int resultado = (a / mcd(num1, num2)) * b;

        return resultado;
    }

    public static Fraction substraction(Fraction a, Fraction b) {
        int mcm = mcm(a.getDenominator(),b.getDenominator());
        return new Fraction((((mcm/a.getDenominator()) * a.getNumerator()) - (((mcm/b.getDenominator()) * b.getNumerator()))),mcm);
    }
}
