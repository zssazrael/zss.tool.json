package zss.tool.json.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.dbutils.ResultSetHandler;

import com.fasterxml.jackson.databind.node.ObjectNode;

import zss.tool.Version;
import zss.tool.database.DatabaseTool;
import zss.tool.json.JsonNodeTool;

@Version("2020.03.21")
public class ListObjectNodeResultSetHandler implements ResultSetHandler<List<ObjectNode>> {
    @Override
    public List<ObjectNode> handle(final ResultSet resultSet) throws SQLException {
        final List<ObjectNode> list = new LinkedList<>();
        final String[] columnLabelArray = DatabaseTool.getColumnLabelArray(resultSet);
        while (resultSet.next()) {
            final ObjectNode objectNode = JsonNodeTool.createObjectNode();
            for (String columnLabel : columnLabelArray) {
                final Object object = resultSet.getObject(columnLabel);
                objectNode.putPOJO(columnLabel, object);
            }
            list.add(objectNode);
        }
        return list;
    }
}
