package prac.sbt.dollarreport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.stream.Collectors;

@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    Quoter quoter;
    @RequestMapping("/")
    @ResponseBody
    ResponseEntity<String> getMaxQuote() {

        try {
            return new ResponseEntity<>(String.valueOf(quoter.getMaxQuote()), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/all")
    @ResponseBody
    ResponseEntity<String> getAllQuotes() {

        try {
            return new ResponseEntity<>(quoter.getAllQuotes().map(Quote::toString).collect(Collectors.joining("<br>\n")), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/local")
    @ResponseBody
    ResponseEntity<String> getRepoQuotes() {

        try {
            return new ResponseEntity<>(quoter.getQuotesFromRepo().map(Quote::toString).collect(Collectors.joining("<br>\n")), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
