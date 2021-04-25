package hw_03_2;

import java.util.List;

public class Frog {
    List<String> opportunitiesList = List.of("<<", ">>", "!!");
    private static final int MIN_POSITION = 0;
    private static final int MAX_POSITION = 10;
    private int curCommand = -1;
    private int steps = 0;
    protected int position;

    public Frog() {
        position = 5;
    }

    public boolean jump(int steps) {
        if (position + steps >= MIN_POSITION && position + steps <= MAX_POSITION) {
            position += steps;
            return true;
        }
        return false;
    }

    public void jumpDo(String input, List<FrogCommand> commands, Frog frog) {
        if (this.opportunitiesList.contains(input)) {
            switch (input) {
                case "<<" -> {
                    cancelCommand(commands);
                    return;
                }
                case ">>" -> {
                    returnCommand(commands);
                    return;
                }
                case "!!" -> {
                    repeatCommand(commands, frog);
                    return;
                }
            }
        }
        jumpToCommand(input, commands, frog);
    }

    private void jumpToCommand(String input, List<FrogCommand> commands, Frog frog) {
        steps = Integer.parseInt(input);
        frog.printJumpMessage(steps);
        FrogCommand cmd = new JumpCommands(frog, steps);
        if (cmd.doit()) {
            this.curCommand++;
            commands.add(cmd);
        }
    }

    private void repeatCommand(List<FrogCommand> commands, Frog frog) {
        if (curCommand < 0) {
            System.out.println("Нет команды для повторения");
            return;
        }
        frog.printJumpMessage(steps);
        FrogCommand repeat = commands.get(this.curCommand);
        if (repeat.doit()) {
            this.curCommand++;
            commands.add(repeat);
        }
    }

    private void returnCommand(List<FrogCommand> commands) {
        if (curCommand == commands.size() - 1) {
            System.out.println("С этим ничего не поделать");
            return;
        }
        this.curCommand++;
        commands.get(this.curCommand).doit();
    }

    private void cancelCommand(List<FrogCommand> commands) {
        if (curCommand < 0) {
            System.out.println("Нечего отменять");
            return;
        }
        commands.get(this.curCommand).undo();
        this.curCommand--;
    }

    private void printJumpMessage(int steps) {
        if (position + steps < MIN_POSITION || position + steps > MAX_POSITION) {
            System.out.println("Слишком далеко. Лягушка не пойдет.");
        } else {
            System.out.print("Лягушка пошла...");
        }
    }
}