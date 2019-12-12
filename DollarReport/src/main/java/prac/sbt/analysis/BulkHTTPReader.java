package prac.sbt.analysis;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import prac.sbt.utils.HttpReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class BulkHTTPReader implements HttpReader {
    @Override
    public Stream<String> readHttp(String target) throws IOException {
        return (new BufferedReader(new InputStreamReader(new URL(target).openConnection().getInputStream()))).lines();
    }
    public String readAll(String target) throws IOException {
        return readHttp(target).collect(Collectors.joining());
    }
}
