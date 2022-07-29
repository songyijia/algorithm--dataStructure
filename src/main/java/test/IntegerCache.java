package test;

public class IntegerCache {
    public static void main(String[] args) {
        int a = 1;Integer b = new Integer(1);
        int c = 200;Integer d = new Integer(200);
        System.out.println(a==b);
        System.out.println(c==d);
        Integer e=1; Integer f = 200;
        System.out.println(b==e);
        System.out.println(d==f);
        Integer g=100,h=100;
        System.out.println(g==h);
        Integer i=200,l=200;
        System.out.println(i==l);
    }
}
