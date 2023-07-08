package io.jpress.modulegen;

import io.jpress.codegen.ModuleGenerator;

public class BarrageModuleGenerator {


    private static String dbUrl = "jdbc:mysql://127.0.0.1:3306/jpress";
    private static String dbUser = "root";
    private static String dbPassword = "123qwe";

    private static String moduleName = "barrage";
    private static String dbTables = "barrage";
    private static String optionsTables = "";
    private static String sortTables = "";
    private static String sortOptionsTables = "";
    private static String modelPackage = "io.jpress.module.barrage.model";
    private static String servicePackage = "io.jpress.module.barrage.service";


    public static void main(String[] args) {

        ModuleGenerator moduleGenerator = new ModuleGenerator(moduleName, dbUrl, dbUser, dbPassword, dbTables, optionsTables,sortTables,sortOptionsTables, modelPackage, servicePackage);
        moduleGenerator.setGenUI(true).gen();

    }
}
