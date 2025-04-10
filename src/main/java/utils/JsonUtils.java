package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonUtils {

    // Generic method to read any list of objects from JSON file
    public static <T> List<T> readListFromJson(String filePath, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(filePath),
                mapper.getTypeFactory().constructCollectionType(List.class, clazz));
    }
}
