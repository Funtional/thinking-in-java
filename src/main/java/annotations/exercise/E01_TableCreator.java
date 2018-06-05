package annotations.exercise;

import annotations.database.Constraints;
import annotations.database.DBTable;
import annotations.database.SQLInteger;
import annotations.database.SQLString;

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class E01_TableCreator {
    public static void main(String[] args) throws Exception {
        args = new String[]{"annotations.exercise.Member"};
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
                    columnDefs.add(columnName + " INT" + getConstraints(sInt.constrains()));
                } else if (anns[0] instanceof SQLString) {
                    SQLString sString = (SQLString) anns[0];
                    // Use field name if name not specified
                    if (sString.name().length() < 1) {
                        columnName = field.getName().toUpperCase();
                    } else {
                        columnName = sString.name();
                    }
                    columnDefs.add(columnName + " VARCHAR(" + sString.value() + ")" + getConstraints(sString.constrains()));
                } else if (anns[0] instanceof SQLBoolean) {
                    SQLBoolean sBol = (SQLBoolean) anns[0];
                    // Use field name if name not specified
                    if (sBol.name().length() < 1) {
                        columnName = field.getName().toUpperCase();
                    } else {
                        columnName = sBol.name();
                    }
                    columnDefs.add(columnName + " BOOLEAN" + getConstraints(sBol.constrains()));
                } else if (anns[0] instanceof SQLCharacter) {
                    SQLCharacter sChar = (SQLCharacter) anns[0];
                    // Use field name if name not specified
                    if (sChar.name().length() < 1) {
                        columnName = field.getName().toUpperCase();
                    } else {
                        columnName = sChar.name();
                    }
                    columnDefs.add(columnName + " CHARACTER(" + sChar.value() + ")" + getConstraints(sChar.constrains()));
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

    private static String getConstraints(Constraints con) {
        String constraints = "";
        if (!con.allowNull())
            constraints += " NOT NULL";
        if (con.primaryKey())
            constraints += " PRIMARY KEY";
        if (con.unique())
            constraints += " UNIQUE";
        return constraints;
    }
}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface SQLBoolean {
    String name() default "";

    Constraints constrains() default @Constraints;
}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface SQLCharacter {
    int value() default 0;

    String name() default "";

    Constraints constrains() default @Constraints;
}

@DBTable(name = "MEMBER")
class Member {
    @SQLString(30)
    private String firstName;
    @SQLString(50)
    private String lastName;
    @SQLInteger
    private int age;

    @SQLCharacter(value = 15, constrains = @Constraints(primaryKey = true))
    private String handle;
    @SQLBoolean
    private Boolean isVip;

    static int memberCount;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getHandle() {
        return handle;
    }

    public Boolean isVip() {
        return isVip;
    }

    @Override
    public String toString() {
        return "handle";
    }
}

