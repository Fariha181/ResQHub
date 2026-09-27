package com.resqhub.resqhub.command;

public interface Command {
    String execute();
    String undo();
}