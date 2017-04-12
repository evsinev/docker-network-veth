package io.pne.veth.server.handlers.model;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Base64;

public class DumpSocat {
    public static void main(String[] args) throws IOException {

        final byte[] bytes2 = Base64.getDecoder().decode("YnsLfVSN");
        System.out.println(new HexBinaryAdapter().marshal(bytes2));
        if(true) {
            return;
        }

        String filename = "/Users/esinev/Library/Preferences/IdeaIC15/scratches/docker-create-endpoint.txt";
        LineNumberReader reader = new LineNumberReader(new FileReader(filename));
        String line;
        while (( line = reader.readLine()) != null) {
//            System.out.println(line);
            if(line.length() >= 50 && line.charAt(48) == ' ' && line.charAt(49) == ' ') {
                String hex = line.substring(0, 49);
                final byte[] bytes = new HexBinaryAdapter().unmarshal(hex.replace(" ", ""));
                String value = new String(bytes);
                System.out.print(value);
//                line = line.substring(50);
            }  else {
                System.out.println(line);
            }
//            System.out.println(line);
        }
    }
}
