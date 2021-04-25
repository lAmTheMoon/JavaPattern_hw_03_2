package hw_03_2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BufferedReader buffered = new BufferedReader(new InputStreamReader(System.in));
        Frog frog = new Frog();
        List<FrogCommand> commands = new ArrayList<>();
        System.out.println("""
                Выберите команду для лягушки:
                +N - прыгни на N шагов направо
                -N - прыгни на N шагов налево
                << - Undo (отмени последнюю команду)
                >> - Redo (повтори отменённую команду)
                !! - повтори последнюю команду
                0 - выход
                """);
        try {
            while (true) {
                System.out.printf("Лягушка на %d позиции.%nВведите команду для лягушки: ", frog.position);
                String input = buffered.readLine();
                if ("0".equals(input)) {
                    break;
                }
                frog.jumpDo(input, commands, frog);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("\n" + "Путь лягушки: ");
        commands.forEach(System.out::print);
    }
}