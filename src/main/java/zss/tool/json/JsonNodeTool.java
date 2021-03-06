package zss.tool.json;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import zss.tool.LoggedException;

public final class JsonNodeTool {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonNodeTool.class);

    private static ObjectMapper objectMapper;

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public static void setObjectMapper(ObjectMapper objectMapper) {
        JsonNodeTool.objectMapper = objectMapper;
    }

    public static ObjectNode createObjectNode() {
        return objectMapper.createObjectNode();
    }

    public static boolean isNull(final JsonNode node) {
        if (node == null) {
            return true;
        }
        return node.isNull();
    }

    public static String asText(final JsonNode node, final String defaultValue) {
        if (isNull(node)) {
            return defaultValue;
        }
        return node.asText(defaultValue);
    }

    public static String asText(final JsonNode node, final String fieldName, final String defaultValue) {
        if (isNull(node)) {
            return defaultValue;
        }
        return asText(node.get(fieldName), defaultValue);
    }

    public static String writeValueAsString(Object value) {
        if (value == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            LOGGER.error(e.getMessage(), e);
        }
        throw new LoggedException();
    }

    private JsonNodeTool() {
    }
}
