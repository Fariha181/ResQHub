package com.resqhub.resqhub.command;

import org.springframework.stereotype.Component;

import java.util.Stack;

@Component
public class DispatchInvoker {

    private Stack<Command> commandHistory = new Stack<>();

    public String executeCommand(Command command) {
        String result = command.execute();
        commandHistory.push(command);
        return result;
    }

    public String undoLastCommand() {
        if (!commandHistory.isEmpty()) {
            Command lastCommand = commandHistory.pop();
            return lastCommand.undo();
        }
        return "No dispatch actions to undo!";
    }
}