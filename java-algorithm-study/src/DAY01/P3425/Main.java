package DAY01.P3425;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    /*
        - 스택
        - 자료 구조
        - 자료형 범위 설정
     */

    public static ArrayList<String> commandList;
    public static ArrayList<Long> stack;
    public static int N;
    public static long V;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/DAY01/P3425/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {

            commandList = new ArrayList<>();
            String command = br.readLine();
            if(command.equals("QUIT")){
                break;
            }

            while (!command.equals("END")) {
                commandList.add(command);
                command = br.readLine();
            }

            N = Integer.parseInt(br.readLine());
            for (int i = 0; i < N; i++) {
                V = Long.parseLong(br.readLine());

                stack = new ArrayList<>();
                stack.add(V);

                String programRes = "";
                for (int j = 0; j < commandList.size(); j++) {
                    programRes = program(commandList.get(j));
                    if (programRes.equals("ERROR")) {
                        break;
                    }
                }

                if (programRes.equals("ERROR") || stack.size() != 1 || stack.get(0) > 1000000000 || stack.get(0) < -1000000000) {
                    System.out.println("ERROR");
                } else {
                    System.out.println(stack.get(0));
                }
            }
            System.out.println();
            br.readLine();
        }
    }

    public static String program(String command){

        int stackSize = stack.size();
        if(command.startsWith("NUM")) {
            long X = Long.parseLong(command.substring(4));
            stack.add(X);
            return "SUCCESS";
        }

        if (stackSize < 1) {
            return "ERROR";
        }
        long topValue = stack.get(stackSize - 1);

        if(command.equals("POP")){
            stack.remove(stackSize-1);
        }else if (command.equals("INV")) {
            stack.set(stackSize - 1, (-1) * topValue);
        } else if (command.equals("DUP")) {
            stack.add(topValue);
        } else if (command.equals("SWP")) {
            if (stackSize >= 2) {
                stack.set(stackSize - 1, stack.get(stackSize - 2));
                stack.set(stackSize - 2, topValue);
            } else {
                return "ERROR";
            }
        } else {
            if (stackSize < 2) {
                return "ERROR";
            }

            long second = stack.get(stackSize - 2);
            stack.remove(stackSize - 1);
            stack.remove(stackSize - 2);

            if (command.equals("ADD")) {
                stack.add(second + topValue);
            } else if (command.equals("SUB")) {
                stack.add(second - topValue);
            } else if (command.equals("MUL")) {
                stack.add(second * topValue);
            } else if (command.equals("DIV")) {
                if (topValue == 0) {
                    return "ERROR";
                }
                stack.add(second / topValue);
            } else if (command.equals("MOD")) {
                if (topValue == 0) {
                    return "ERROR";
                }
                stack.add(second % topValue);
            }
        }

        return "SUCCESS";
    }
}
