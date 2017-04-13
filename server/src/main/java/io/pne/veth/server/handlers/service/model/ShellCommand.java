package io.pne.veth.server.handlers.service.model;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShellCommand {

    public final String[] arguments;

    private ShellCommand(String[] aArguments) {
        arguments = aArguments;
    }

    public String getCommand() {
        StringBuilder sb = new StringBuilder();
        sb.append(arguments[0]);
        for(int i=1; i<arguments.length; i++) {
            sb.append(' ');
            sb.append(arguments[i]);
        }
        return sb.toString();
    }

    public static class Builder {
        private final List<String> arguments;

        public Builder(String aCommandName) {
            arguments = new ArrayList<>();
            arguments.add(aCommandName);
        }

        public Builder add(String aArgument) {
            arguments.add(aArgument);
            return this;
        }

        public ShellCommand build() {
            return new ShellCommand(arguments.toArray(new String[arguments.size()]));
        }
    }

    @Override
    public String toString() {
        return "ShellCommand{" +
                "arguments=" + Arrays.toString(arguments) +
                '}';
    }
}
