package com.resqhub.resqhub.controller;

import com.resqhub.resqhub.command.DispatchCommand;
import com.resqhub.resqhub.command.DispatchInvoker;
import com.resqhub.resqhub.singleton.SystemLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dispatch")
public class DispatchController {

    @Autowired
    private DispatchInvoker invoker;

    @PostMapping("/send")
    public String sendDispatch(@RequestParam String responder, @RequestParam String location) {
        SystemLogger.getInstance().log("Executing dispatch command for: " + responder);
        DispatchCommand command = new DispatchCommand(responder, location);
        return invoker.executeCommand(command);
    }

    @PostMapping("/undo")
    public String undoDispatch() {
        SystemLogger.getInstance().log("Undo last dispatch action requested");
        return invoker.undoLastCommand();
    }
}