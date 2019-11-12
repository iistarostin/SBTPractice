package prac.sbt.dollarreport;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.stream.Stream;

@Component
public class SimpleHttpReader implements HttpReader {

    @Override
    public Stream<String> readHttp(String target) throws IOException {
        return (new BufferedReader(new InputStreamReader(new URL(target).openConnection().getInputStream()))).lines();
    }
}
