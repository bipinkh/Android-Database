package com.bipinkh.dbapp.helper.daoGenerator;

/**
 * Created by bipin on 11/8/2017.
 */

import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Schema;

public class DaoFilesGenerator {


    public static void main(String[] args) {
        Schema schema = new Schema(1, "com.bipinkh.dbapp.models.database"); // package name + db folder location to generate dao files
        schema.enableKeepSectionsByDefault();
        //make tables
        addTables(schema);
        //generate
        try {
            new DaoGenerator().generateAll(schema,"./app/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addTables(final Schema schema) {
        addUserEntities(schema);
        //add other tables here
    }

    // This is used to describe the colums of your table
    private static Entity addUserEntities(final Schema schema) {
        Entity user = schema.addEntity("User"); //table name
        user.addIdProperty().primaryKey().autoincrement();
        user.addStringProperty("first_name");
        user.addStringProperty("last_name");
        user.addLongProperty("phone");
        user.addStringProperty("address");
        user.addStringProperty("email");
        user.addStringProperty("gender");
        return user;
    }

}
