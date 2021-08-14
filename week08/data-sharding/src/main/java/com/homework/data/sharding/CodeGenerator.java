package com.homework.data.sharding;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @author bob
 */
public class CodeGenerator {

  public static void main(String[] args) {
    DataSourceConfig dataSourceConfig = new DataSourceConfig.Builder(
        "jdbc:mysql://localhost:3306/test0", "root", "root").build();
    String projectPath = System.getProperty("user.dir");
    GlobalConfig globalConfig = new GlobalConfig.Builder().outputDir(projectPath + "/week08/data-sharding/src/main/java")
        .dateType(DateType.ONLY_DATE)
        .author("bob").openDir(false).build();
    PackageConfig packageConfig = new PackageConfig.Builder().moduleName("gen")
        .parent("com.homework.data.sharding").build();
    StrategyConfig strategyConfig = new StrategyConfig.Builder()
        .addInclude("order_info")
        .entityBuilder().naming(NamingStrategy.underline_to_camel)
        .build();
    AutoGenerator autoGenerator = new AutoGenerator(dataSourceConfig);
    autoGenerator.global(globalConfig).packageInfo(packageConfig).strategy(strategyConfig);
    autoGenerator.execute();
  }
}
