package zss.tool.json.database;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.ResultSetHandler;

import com.fasterxml.jackson.databind.node.ObjectNode;

import zss.tool.Version;
import zss.tool.database.DatabaseTool;
import zss.tool.json.JsonNodeTool;

@Version("2020.03.21")
public class ObjectNodeResultSetHandler implements ResultSetHandler<ObjectNode> {
    @Override
    public ObjectNode handle(final ResultSet resultSet) throws SQLException {
        final ObjectNode objectNode = JsonNodeTool.createObjectNode();
        for (String columnLabel : DatabaseTool.getColumnLabelArray(resultSet)) {
            final Object object = resultSet.getObject(columnLabel);
            objectNode.putPOJO(columnLabel, object);
        }
        return objectNode;
    }
}
