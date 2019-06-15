/*

----------------------------------------------------------- CHALLENGE ------------------------------------------------------------

»»»»»»»»»» VERIFY IF TRIANGLE IS VALID

 input
    3 positive integers
    a = 1, b = 2, c = 3

 output
    Triangle name
    1 - Equilateral
    2 - Isosceles
    3 - Scalene
    4 - Not a triangle

 */

public class Triangle {

    private final int EQUILATERAL    = 1;
    private final int ISOSCELES      = 2;
    private final int SCALENE        = 3;
    private final int NOT_A_TRIANGLE = 0;

    private int a;
    private int b;
    private int c;

    private int isTriangle;

    public Triangle(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
        checkTriangle();
    }

    private void checkTriangle() {
        if ( a < 0 || b < 0 || c < 0 ) {
            this.isTriangle = this.NOT_A_TRIANGLE;
            return;
        }
        if ( a == b && b == c) {
            this.isTriangle = this.EQUILATERAL;
            return;
        }
        if ( (a == b && a != c) || ( b == c && b != a ) || (c == a && c != b) ) {
            this.isTriangle = this.ISOSCELES;
            return;
        }
        if ( a != b && b != c ) {
            this.isTriangle = this.SCALENE;
            return;
        }
    }

    @Override
    public String toString() {
        return "Triangle Result = "  + isTriangle;
    }

    public static void main(String[] args) {
        System.out.println(new Triangle(1,1,1).toString()); //EQUILATERAL
        System.out.println(new Triangle(2,1,2).toString()); //ISOSCELES
        System.out.println(new Triangle(2,1,7).toString()); //SCALENE
        System.out.println(new Triangle(-3,1,4).toString()); //SCALENE

    }
}
