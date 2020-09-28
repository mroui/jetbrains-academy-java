import java.util.Stack;

interface Movable {
    int getX();

    int getY();

    void setX(int newX);

    void setY(int newY);
}

interface Storable {
    int getInventoryLength();

    String getInventoryItem(int index);

    void setInventoryItem(int index, String item);
}

interface Command {
    void execute();

    void undo();
}

class CommandMove implements Command {
    Movable entity;
    int xMovement;
    int yMovement;
    Stack<Integer> previousX = new Stack<>();
    Stack<Integer> previousY = new Stack<>();

    @Override
    public void execute() {
        previousX.push(entity.getX());
        previousY.push(entity.getY());
        entity.setX(xMovement);
        entity.setY(yMovement);
    }

    @Override
    public void undo() {
        if (previousX.size() > 0)
            entity.setX(previousX.pop());
        if (previousY.size() > 0)
            entity.setY(previousY.pop());
    }
}

class CommandPutItem implements Command {
    Storable entity;
    String item;
    Stack<Integer> previous = new Stack<>();

    @Override
    public void execute() {
        for (int i = 0; i < entity.getInventoryLength(); i++) {
            if (entity.getInventoryItem(i) == null) {
                entity.setInventoryItem(i, item);
                previous.push(i);
                return;
            }
        }
    }

    @Override
    public void undo() {
        if (previous.size() > 0)
            entity.setInventoryItem(previous.pop(), null);
    }
}