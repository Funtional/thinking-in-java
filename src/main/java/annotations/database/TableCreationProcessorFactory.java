package annotations.database;

import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.apt.AnnotationProcessorFactory;
import com.sun.mirror.declaration.AnnotationTypeDeclaration;
import com.sun.mirror.declaration.ClassDeclaration;
import com.sun.mirror.declaration.FieldDeclaration;
import com.sun.mirror.declaration.TypeDeclaration;
import com.sun.mirror.util.SimpleDeclarationVisitor;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import static com.sun.mirror.util.DeclarationVisitors.*;

public class TableCreationProcessorFactory implements AnnotationProcessorFactory {
    @Override
    public Collection<String> supportedOptions() {
        return Collections.emptyList();
    }

    @Override
    public Collection<String> supportedAnnotationTypes() {
        return Arrays.asList(
                "annotaions.database.DBTable",
                "annotaions.database.Constraints",
                "annotaions.database.SQLString",
                "annotaions.database.SQLInteger");
    }

    @Override
    public AnnotationProcessor getProcessorFor(Set<AnnotationTypeDeclaration> atds, AnnotationProcessorEnvironment env) {
        return new TableCreationProcessor(env);
    }

    private static class TableCreationProcessor implements AnnotationProcessor {
        private final AnnotationProcessorEnvironment env;

        private String sql = "";

        private TableCreationProcessor(AnnotationProcessorEnvironment env) {
            this.env = env;
        }

        @Override
        public void process() {
            for (TypeDeclaration typeDecl : env.getSpecifiedTypeDeclarations()) {
                typeDecl.accept(getDeclarationScanner(new TableCreationVisitor(), NO_OP));
                sql = sql.substring(0, sql.length() - 1) + ");";
                System.out.println("creation SQL is :\n" + sql);
                sql = "";
            }
        }

        private class TableCreationVisitor extends SimpleDeclarationVisitor {
            @Override
            public void visitClassDeclaration(ClassDeclaration d) {
                DBTable dbTable = d.getAnnotation(DBTable.class);
                if (dbTable != null) {
                    sql += "CREATE TABLE";
                    sql += (dbTable.name().length() < 1) ? d.getSimpleName().toUpperCase() : dbTable.name();
                    sql += " (";
                }
            }

            @Override
            public void visitFieldDeclaration(FieldDeclaration d) {
                String columnName = "";
                if (d.getAnnotation(SQLInteger.class) != null) {
                    SQLInteger sInt = d.getAnnotation(SQLInteger.class);
                    // Use field name if name not specified
                    if (sInt.name().length() < 1) {
                        columnName = d.getSimpleName().toUpperCase();
                    } else {
                        columnName = sInt.name();
                    }
                    sql += "\n    " + columnName + " INT " + getConstrains(sInt.constrains()) + ",";
                }
                if (d.getAnnotation(SQLString.class) != null) {
                    SQLString sStr = d.getAnnotation(SQLString.class);
                    // Use field name if name not specified
                    if (sStr.name().length() < 1) {
                        columnName = d.getSimpleName().toUpperCase();
                    } else {
                        columnName = sStr.name();
                    }
                    sql += "\n    " + columnName + " VARCHAR(" + sStr.value() + ") " + getConstrains(sStr.constrains()) + ",";
                }
            }
        }

        @SuppressWarnings("Duplicates")
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

}