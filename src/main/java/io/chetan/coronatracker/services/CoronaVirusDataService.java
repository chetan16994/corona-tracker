package io.chetan.coronatracker.services;

import io.chetan.coronatracker.models.LocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronaVirusDataService {

//    private static String VIRUS_DATA_URL="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_US.csv";
private static String VIRUS_DATA_URL="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_US.csv";

private List<LocationStats> allStats=new ArrayList<>();

public List<LocationStats> getAllStats() {
    return allStats;
}

    @PostConstruct
    @Scheduled(fixedRate = 1000000)
    public void fetchVirusData () throws IOException, InterruptedException {
        List<LocationStats> newStats=new ArrayList<>();
        HttpClient client=HttpClient.newHttpClient();
        HttpRequest request= HttpRequest.newBuilder()
                .uri(URI.create(VIRUS_DATA_URL))
                .build();

        HttpResponse<String> response= client.send(request,HttpResponse.BodyHandlers.ofString() );
        StringReader csvReader= new StringReader(response.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvReader);
        for (CSVRecord record : records) {
            LocationStats locationStats=new LocationStats();
            locationStats.setState(record.get("Province_State"));
            locationStats.setCounty(record.get("Admin2"));
            locationStats.setTotalLatestCases(Integer.parseInt(record.get(record.size()-1)) );
            locationStats.setLastWeekCases(Integer.parseInt(record.get(record.size()-1)) - Integer.parseInt(record.get(record.size()-8)) );
            locationStats.setYesterdayCases(Integer.parseInt(record.get(record.size()-1)) - Integer.parseInt(record.get(record.size()-2)) );
            System.out.println(locationStats);
            newStats.add(locationStats);
        }
        this.allStats=newStats;
    }

}
