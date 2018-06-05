package annotations.database;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class TableCreator {
    public static void main(String[] args) throws Exception {
        args = new String[]{"annotations.database.Member"};
        for (String className : args) {
            Class<?> cl = Class.forName(className);
            DBTable dbTable = cl.getAnnotation(DBTable.class);
            if (dbTable == null) {
                System.out.println("No DBTable annotations in class " + className);
                continue;
            }

            String tableName = dbTable.name();
            // If the name is empty, use the Class name:
            if (tableName.length() < 1) {
                tableName = cl.getSimpleName().toUpperCase();
            }

            List<String> columnDefs = new ArrayList<>();
            for (Field field : cl.getDeclaredFields()) {
                String columnName = null;
                Annotation[] anns = field.getDeclaredAnnotations();
                if (anns.length < 1) {
                    continue; // Not a db table column
                }
                if (anns[0] instanceof SQLInteger) {
                    SQLInteger sInt = (SQLInteger) anns[0];
                    // Use field name if name not specified
                    if (sInt.name().length() < 1) {
                        columnName = field.getName().toUpperCase();
                    } else {
                        columnName = sInt.name();
                    }
                    columnDefs.add(columnName + " INT" + getConstrains(sInt.constrains()));
                } else if (anns[0] instanceof SQLString) {
                    SQLString sString = (SQLString) anns[0];
                    // Use field name if name not specified
                    if (sString.name().length() < 1) {
                        columnName = field.getName().toUpperCase();
                    } else {
                        columnName = sString.name();
                    }
                    columnDefs.add(columnName + " VARCHAR(" + sString.value() + ")" + getConstrains(sString.constrains()));
                }
            }

            StringBuilder createCommand = new StringBuilder("CREATE TABLE ").append(tableName).append("(");
            for (String columnDef : columnDefs) {
                createCommand.append("\n      ").append(columnDef).append(",");
            }
            createCommand.deleteCharAt(createCommand.length() - 1).append(");");
            System.out.println("Table Creation SQL for " + className + " is: \n" + createCommand.toString());
        }
    }

    private static String getConstrains(Constraints con) {
        String constrains = "";
        if (!con.allowNull()) {
            constrains += " NOT NULL";
        }
        if (con.primaryKey()) {
            constrains += " PRIMARY KEY";
        }
        if (con.unique()) {
            constrains += " UNIQUE";
        }
        return constrains;
    }
}
