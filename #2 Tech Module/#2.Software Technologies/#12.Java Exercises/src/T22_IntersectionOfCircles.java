import java.util.*;

public class T22_IntersectionOfCircles {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] dimensions1 = Arrays.stream(scan.nextLine().split("\\s"))
                .mapToInt(Integer::parseInt).toArray();

        int[] dimensions2 = Arrays.stream(scan.nextLine().split("\\s"))
                .mapToInt(Integer::parseInt).toArray();

        Circle circle1 = new Circle(dimensions1[0], dimensions1[1], dimensions1[2]);
        Circle circle2 = new Circle(dimensions2[0], dimensions2[1], dimensions2[2]);
        System.out.println(circlesIntersect(circle1, circle2) ? "Yes" : "No");

    }

    private static boolean circlesIntersect(Circle c1, Circle c2) {
        double deltaX = c1.x - c2.x;
        double deltaY = c1.y - c2.y;
        double distanceCircleCenters = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
        return distanceCircleCenters <= c1.radius + c2.radius;

    }

    static class Circle {
        private int x;
        private int y;
        private int radius;

        public Circle(int x, int y, int radius) {
            this.x = x;
            this.y = y;
            this.radius = radius;
        }
    }
}