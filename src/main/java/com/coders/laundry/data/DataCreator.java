package com.coders.laundry.data;

import com.coders.laundry.data.open.OpenLaundryData;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataCreator {

  @Autowired
  private final ObjectMapper mapper;

  @EventListener
  public void onApplicationEvent(ContextRefreshedEvent event) {
    try {
      laundryDataCreate();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
  }

  private void laundryDataCreate() throws IOException {
    File file = new File("src/main/resources/db/h2/file/laundry.json");
    File file2 = new File("src/main/resources/db/h2/file/laundry2.json");
    BufferedReader br = new BufferedReader(new FileReader(file));
    BufferedReader br2 = new BufferedReader(new FileReader(file2));

    String line;

    StringBuilder builder = new StringBuilder();
    StringBuilder builder2 = new StringBuilder();
    while ((line = br.readLine()) != null) {
      builder.append(line);
    }
    br.close();

    while((line = br2.readLine()) != null) {
      builder2.append(line);
    }
    br2.close();

    OpenLaundryData[] openLaundryDatas = mapper.readValue(builder.toString(), OpenLaundryData[].class);
    OpenLaundryData[] openLaundryDatas2 = mapper.readValue(builder2.toString(), OpenLaundryData[].class);

    String query = """
        INSERT INTO laundry (name, jibun_address, doro_address, latitude, longitude, partnership)
        values (?, ?, ?, ?, ?, ?)
        """;

    query(openLaundryDatas, query);
    query(openLaundryDatas2, query);
  }

  private boolean query(OpenLaundryData[] openLaundryDatas, String query) {
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
     return true;
   } catch (SQLException e) {
     log.error(e.getMessage(), e);
   }
    return false;
  }
}
