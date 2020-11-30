package prac.sbt.analysis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AnalysisController {
    @Autowired
    MLModel model;
    @Autowired
    DataLoader loader;
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String getSignificance() throws IOException {
        return "Dependency significance: " + String.valueOf(model.getSignificance(loader.getData().collect(Collectors.toList())));
    }
    @RequestMapping(value = "/sig", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public double getOnlySignificance() throws IOException {
        return model.getSignificance(loader.getData().collect(Collectors.toList()));
    }
    @RequestMapping(value = "/json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Double getSignificanceJSON() throws IOException {
        return model.getSignificance(loader.getData().collect(Collectors.toList()));
    }
    @RequestMapping(value = "/data", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Observation> getData() throws IOException {
        List<Observation> data = loader.getData().collect(Collectors.toList());
        return data;
    }
}
