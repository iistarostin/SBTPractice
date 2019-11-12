package prac.sbt.dollarreport;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.stream.Stream;

public interface HttpReader {
    Stream<String> readHttp(String target) throws IOException;
}
