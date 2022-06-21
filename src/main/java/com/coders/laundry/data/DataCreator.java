package com.coders.laundry.data;

import com.coders.laundry.data.open.OpenLaundryData;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.stream.Stream;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataCreator {

    @Autowired
    private final ObjectMapper mapper;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            laundryDataCreate("src/main/resources/db/h2/file/laundry.json");
            laundryDataCreate("src/main/resources/db/h2/file/laundry2.json");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    private void laundryDataCreate(String rawDataFilePath) throws IOException {
        StringBuilder builder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(rawDataFilePath), StandardCharsets.UTF_8)) {
            stream.forEach(builder::append);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String fileContent = builder.toString();

        OpenLaundryData[] openLaundryDatas = mapper.readValue(fileContent, OpenLaundryData[].class);

        String query = """
                INSERT INTO laundry (name, jibun_address, doro_address, latitude, longitude, partnership)
                values (?, ?, ?, ?, ?, ?)
                """;

        query(openLaundryDatas, query);
    }

    private void query(OpenLaundryData[] openLaundryDatas, String query) {
        try {
            String url = "jdbc:h2:mem:test;MODE=MYSQL;DATABASE_TO_LOWER=TRUE";
            String user = "sa";
            String password = "";
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = con.prepareStatement(query);

            for (OpenLaundryData data : openLaundryDatas) {
                if (data.getRefineWgs84Lat().isBlank()
                        || data.getRefineWgs84Logt().isBlank()
                        || "폐업".equals(data.getBsnStateNm())) {
                    continue;
                }

                statement.setString(1, data.getBizplcNm());
                statement.setString(2, data.getRefineLotnoAddr());
                statement.setString(3, data.getRefineRoadnmAddr());
                statement.setBigDecimal(4, new BigDecimal(data.getRefineWgs84Lat()));
                statement.setBigDecimal(5, new BigDecimal(data.getRefineWgs84Logt()));
                statement.setBoolean(6, true);

                statement.execute();
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }
}
