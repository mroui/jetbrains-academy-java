import java.util.List;

class Sort {
    public static void sortShapes(Shape[] array,
                                  List<Shape> shapes,
                                  List<Polygon> polygons,
                                  List<Square> squares,
                                  List<Circle> circles) {
        for (Shape shape : array) {
            switch (shape.getClass().getName()) {
                case "Shape":
                    shapes.add(shape);
                    break;
                case "Polygon":
                    polygons.add((Polygon) shape);
                    break;
                case "Square":
                    squares.add((Square) shape);
                    break;
                default:
                    circles.add((Circle) shape);
                    break;
            }
        }
    }
}

//Don't change classes below
class Shape {
}

class Polygon extends Shape {
}

class Square extends Polygon {
}

class Circle extends Shape {
}