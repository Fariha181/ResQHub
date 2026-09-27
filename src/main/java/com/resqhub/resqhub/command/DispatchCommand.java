package com.resqhub.resqhub.command;

public class DispatchCommand implements Command {

    private String responderType;
    private String location;
    private boolean isDispatched = false;

    public DispatchCommand(String responderType, String location) {
        this.responderType = responderType;
        this.location = location;
    }

    @Override
    public String execute() {
        isDispatched = true;
        return "DISPATCHED: " + responderType + " team sent to " + location;
    }

    @Override
    public String undo() {
        if (isDispatched) {
            isDispatched = false;
            return "CANCELLED/ROLLBACK: " + responderType + " team recalled from " + location;
        }
        return "No active dispatch to cancel.";
    }
}