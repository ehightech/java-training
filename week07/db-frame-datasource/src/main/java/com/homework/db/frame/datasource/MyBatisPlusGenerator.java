package com.homework.db.frame.datasource;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;

/**
 * @author bob
 */
public class MyBatisPlusGenerator {

  public static void main(String[] args) {
    DataSourceConfig dataSourceConfig = new DataSourceConfig.Builder(
        "jdbc:mysql://localhost:3306/master", "root", "root").build();
    String projectPath = System.getProperty("user.dir");
    GlobalConfig globalConfig = new GlobalConfig.Builder()
        .outputDir(projectPath + "/week07/db-frame-datasource/src/main/java")
        .openDir(false).build();
    PackageConfig packageConfig = new PackageConfig.Builder()
        .moduleName("test")
        .parent("com.homework.db.frame.datasource").build();
    AutoGenerator autoGenerator = new AutoGenerator(dataSourceConfig);
    autoGenerator.global(globalConfig).packageInfo(packageConfig);
    autoGenerator.execute();
  }
}
