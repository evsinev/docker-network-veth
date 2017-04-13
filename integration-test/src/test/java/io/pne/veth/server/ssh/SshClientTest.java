package io.pne.veth.server.ssh;

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.common.IOUtils;
import net.schmizz.sshj.connection.channel.direct.Session;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class SshClientTest {

    @Test
    public void connect_to_virtual_box() throws IOException {
        SSHClient ssh = new SSHClient();
        ssh.loadKnownHosts();
        ssh.connect("localhost", 2222);
        try {
            ssh.authPublickey("root");
            try (Session session = ssh.startSession()) {
                Session.Command command = session.exec("ls -l");
                System.out.println(IOUtils.readFully(command.getInputStream()).toString());
                command.join(5, TimeUnit.SECONDS);
            }
        } finally {
            ssh.disconnect();
        }

    }
}
