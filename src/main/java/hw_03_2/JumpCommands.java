package hw_03_2;

public class JumpCommands implements FrogCommand {
    private Frog frog;
    private int steps;

    public JumpCommands(Frog frog, int steps) {
        this.frog = frog;
        this.steps = steps;
    }

    @Override
    public boolean doit() {
        return frog.jump(steps);
    }

    @Override
    public boolean undo() {
        return frog.jump(-steps);
    }

    @Override
    public String toString() {
        return String.format("Лягушка прыгнула на %d %s %s%n",
                Math.abs(steps), Math.abs(steps) == 1 ? "шаг" : "шагов", steps < 0 ? "левее" : "правее");
    }
}