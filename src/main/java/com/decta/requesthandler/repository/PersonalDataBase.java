package com.decta.requesthandler.repository;

import com.decta.requesthandler.domain.BussinessClient;
import com.decta.requesthandler.domain.IndividualClient;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class PersonalDataBase {

    private static final String INDIVIDUAL_BASE_PATH = "individual_base.json";
    private static final String COMPANY_BASE_PATH = "company_base.json";

    private <K, V> Map<K, V> getMapFromJson(String path, Class<K> keyClass, Class<V> valueClass) {

        ObjectMapper mapper = new ObjectMapper();
        JavaType mapType = mapper.getTypeFactory().constructMapType(Map.class, keyClass, valueClass);
        Map<K, V> objectMap = new HashMap<>();

        try {
            byte[] mapData = Files.readAllBytes(Paths.get(path));
            objectMap = mapper.readValue(mapData, mapType);
        } catch (Exception e) {
            System.out.println(e.getMessage() + e.getCause());
        }

        return objectMap;
    }

    public Optional<IndividualClient> getIndividualById(String id) {
        Map<String, IndividualClient> individualMap = getMapFromJson(INDIVIDUAL_BASE_PATH, String.class, IndividualClient.class);
        return Optional.ofNullable(individualMap.get(id));
    }

    public Optional<BussinessClient> getCompanyById(String id) {
        Map<String, BussinessClient> companyMap = getMapFromJson(COMPANY_BASE_PATH, String.class, BussinessClient.class);
        return Optional.ofNullable(companyMap.get(id));
    }
}
