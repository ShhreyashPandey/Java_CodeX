public class Ques2 {
    public int add(int a, int b) {
        return a + b;
    }
    public double add(double a, double b) {
        return a + b;
    }
    public double add(int a, double b) {
        return a + b;
    }
    public static void main(String[] args) {
        Ques2 math = new Ques2();

        System.out.println("Adding integers: " + math.add(2, 3));
        System.out.println("Adding doubles: " + math.add(3.6, 5.8));
        System.out.println("Adding integer and double: " + math.add(4, 7.1));
    }
}