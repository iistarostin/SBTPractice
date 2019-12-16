package prac.sbt.weatherreport;

import net.aksingh.owmjapis.api.APIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class WeatherServiceController {
    @Autowired
    RecordFetcher recordFetcher;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String getAllQuotes() throws APIException, IOException {
        return recordFetcher.getAllRecords().map(WeatherRecord::toString).collect(Collectors.joining("<br>\n"));
    }

    @RequestMapping(value = "/json/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<WeatherRecord> getAllQuotesJSON() throws APIException, IOException {
        return recordFetcher.getAllRecords().collect(Collectors.toList());
    }

    @GetMapping("/local")
    @ResponseBody
    public String getRepoQuotes() throws IOException {
        return recordFetcher.getRecordsFromRepo().map(WeatherRecord::toString).collect(Collectors.joining("<br>\n"));
    }
}
