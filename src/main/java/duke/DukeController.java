package duke;

import java.io.IOException;
import java.util.Scanner;

public class DukeController {

    private static Task[] tasks = new Task[100];
    private static int currentTaskLength = 0;
    private static Scanner sc = new Scanner(System.in);
    private static String input;
    private static int currentCommand;


    public static void run() throws IOException {
        DukeIO.initialize();
        while (true){
            input = sc.nextLine();
            currentCommand = DukeCommandValidator.getCommand(input);
            switch (currentCommand){
            case DukeCommands.INVALID_COMMAND:{
                System.out.println("Please enter a valid command!");
                DukeUI.printMenu();
                break;
            }
            case DukeCommands.LIST:{
                DukeIO.readDukeData();
                list();
                break;
            }
            case DukeCommands.EXIT:{
                return;
            }
            case DukeCommands.DONE:{
                DukeIO.readDukeData();
                done();
                DukeIO.writeDukeData();
                break;
            }
            case DukeCommands.ADD:{
                DukeIO.readDukeData();
                add();
                DukeIO.writeDukeData();
                break;
            }
            default: {
                System.out.println("Unknown error has occurred! Please try again.");
            }
            }
        }
    }

    public static void list(){
        for(int i=0; i<currentTaskLength; i++){
            System.out.printf("%d.", i+1);
            tasks[i].printTask();
        }
    }

    public static void done(){
        int taskIndex = Integer.parseInt(input.substring(5)) - 1;
        if(taskIndex >= currentTaskLength || taskIndex < 0){
            System.out.println("The task index you have entered is out of bound!");
            return;
        }
        else{
            tasks[taskIndex].setIsDone(true);
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println("["
                    + tasks[taskIndex].getStatusIcon() + "] "
                    + tasks[taskIndex].getDescription());
        }

    }

    public static void add(){
        if(DukeCommandValidator.isToDoValid(input)==true){
            tasks[currentTaskLength] = new ToDo(input);
        }else if(DukeCommandValidator.isDeadlineValid(input)==true){
            tasks[currentTaskLength] = new Deadline(input);;
        }else if(DukeCommandValidator.isEventValid(input)){
            tasks[currentTaskLength] = new Event(input);
        }else{
            System.out.println("Invalid syntax for add commands! Please try again!");
            DukeUI.printMenu();
            return;
        }
        System.out.printf("Got it. I've added this task:\n  ");
        tasks[currentTaskLength].printTask();
        System.out.printf("Now you have %d tasks in the list.\n", currentTaskLength + 1);
        currentTaskLength++;
    }

    public static int getCurrentTaskLength(){
        return currentTaskLength;
    }

    public static void setCurrentTaskLength(int newlength){
        currentTaskLength = newlength;
    }

    public static Task[] getTasks(){
        return tasks;
    }

    public static void setTasks(Task[] newTasks){
        tasks = newTasks;
    }
}
