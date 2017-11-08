package io.pne.veth.server.handlers.service.impl;

import io.pne.veth.server.handlers.service.ICommandService;
import io.pne.veth.server.handlers.service.model.ShellCommand;

import java.io.*;

public class CommandServiceImpl implements ICommandService {

    @Override
    public String executeCommand(ShellCommand aCommand) throws IOException, InterruptedException {
        Process process = new ProcessBuilder(aCommand.arguments).start();
        String  output  = readOutput(process.getInputStream());
        int     result  = process.waitFor();

        if(result != 0) {
            throw new IllegalStateException("Command returned " + result + ": " + aCommand);
        }

        return output;
    }

    private String readOutput(InputStream aInput) throws IOException {
        StringBuilder sb = new StringBuilder();
        try(LineNumberReader in = new LineNumberReader(new InputStreamReader(aInput))) {
            String line;
            while( ( line = in.readLine()) != null) {
                if(sb.length() > 0) {
                    sb.append(" ");
                }
                sb.append(line);
            }
        }
        return sb.toString();
    }
}
