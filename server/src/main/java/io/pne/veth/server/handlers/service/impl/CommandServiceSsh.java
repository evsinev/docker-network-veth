package io.pne.veth.server.handlers.service.impl;

import io.pne.veth.server.handlers.service.ICommandService;
import io.pne.veth.server.handlers.service.model.ShellCommand;
import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.common.IOUtils;
import net.schmizz.sshj.connection.channel.direct.Session;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class CommandServiceSsh implements ICommandService {

    @Override
    public String executeCommand(ShellCommand aCommand) throws IOException {
        SSHClient ssh = new SSHClient();
        ssh.loadKnownHosts();
        ssh.connect("localhost", 2222);
        try {
            ssh.authPublickey("root");
            try (Session session = ssh.startSession()) {
                Session.Command command = session.exec(aCommand.getCommand());
                String text = IOUtils.readFully(command.getInputStream()).toString();
                command.join(5, TimeUnit.SECONDS);
                return text;
            }
        } finally {
            ssh.disconnect();
        }
    }
}
