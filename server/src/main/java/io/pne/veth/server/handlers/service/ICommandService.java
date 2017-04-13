package io.pne.veth.server.handlers.service;

import io.pne.veth.server.handlers.service.model.ShellCommand;

import java.io.IOException;

public interface ICommandService {

    String executeCommand(ShellCommand aCommand) throws IOException;
}
