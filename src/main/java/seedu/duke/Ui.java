package seedu.duke;

import com.jakewharton.fliptables.FlipTableConverters;
import seedu.duke.exceptions.CustomException;

import java.util.Scanner;

public class Ui {
    private static final int NEW_LINE = 48;
    public boolean isPlaying = true;

    public boolean hasStartedGame = false;
    public TopicList topicList;
    public QuestionListByTopic questionListByTopic;

    public String[] inputAnswers;

    public void readCommands(
            Ui ui, QuestionsList questionsList, TopicList topicList,
            QuestionListByTopic questionListByTopic
    ) {
        Parser parser = new Parser();
        Scanner in = new Scanner(System.in);
        printLine();

        while(isPlaying) {
            ui.askForInput();
            String command = in.nextLine();
            try {
                parser.parseCommand(command, ui, questionsList, topicList, questionListByTopic);
            } catch (CustomException e) {
                ui.handleException(e);
            }
        }

        sayBye();
    }

    private void askForInput() {
        System.out.println("Input a command player! // TODO: show possible commands"); // TODO
    }

    private void askForAnswerInput(){
        System.out.print("Enter your answer: ");
    }

    public void printTopicList(TopicList topicList, Ui ui){
        int topicListSize = topicList.getSize();
        System.out.println("Here are the topics in CS2113: ");
        for (int index = 0; index < topicListSize; index++) {
            System.out.println((index + 1) + ". " + topicList.getTopic(index));
        }
        System.out.println("Please choose a topic to play: ");//input command in the form "start [INDEX]
    }

    public void printChosenTopic(
            int topicNum, TopicList topicList, QuestionListByTopic questionListByTopic
    ){
        QuestionsList qnList = new QuestionsList();
        System.out.println("Selected topic: " + topicList.getTopic(topicNum - 1));
        System.out.println("Here are the questions: ");
        qnList = questionListByTopic.getQuestionSet(topicNum - 1);
        int numOfQns = qnList.getSize();
        Question questionUnit;
        String[] inputAnswers = new String[numOfQns];
        String answer;
        for (int index = 0; index < numOfQns; index ++){
            questionUnit = qnList.getQuestionUnit(index);
            System.out.println(questionUnit.getQuestion());
            askForAnswerInput();
            Parser parser = new Parser();
            Scanner in = new Scanner(System.in);
            answer = in.nextLine();
            inputAnswers[index] = answer;
        }
    }


    public void printOneSolution(int questionNum, String solution) {
        System.out.println("The solution for question " + questionNum + ":"
                + System.lineSeparator() + solution);
    }
    public void printAllSolutions(String allSolutions) {
        System.out.println("The solutions are :"
                + System.lineSeparator() + allSolutions);
    }
    private void handleException(CustomException e) {
        System.out.println(e.getMessage() + "TODO: show possible commands"); //TODO
    }
    public void printLine() {
        for (int i = 0; i < NEW_LINE; i += 1) {
            System.out.print("*");
        }
        System.out.println();
    }

    public void sayHi() {
        String logo =
                "______ _                       _____  __   __   _____\n" +
                "| ___ \\ |                     / __  \\/  | /  | |____ |\n" +
                "| |_/ / | __ _ _   _  ___ _ __`' / /'`| | `| |     / /\n" +
                "|  __/| |/ _` | | | |/ _ \\ '__| / /   | |  | |     \\ \\\n" +
                "| |   | | (_| | |_| |  __/ |  ./ /____| |__| |_.___/ /\n" +
                "\\_|   |_|\\__,_|\\__, |\\___|_|  \\_____/\\___/\\___/\\____/\n" +
                "                __/ |\n" +
                "               |___/";

        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");
        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());
        printLine();
    }

    public void sayBye() {
        System.out.println("bye bye, get more sleep zzz");
        printLine();
    }

    public void printTable(String[] headers, Object[][] data) {
        System.out.println(FlipTableConverters.fromObjects(headers, data));
    }

}
