package com.homework.spring.kafka.demo.configuration;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

/**
 * kafka主题配置
 *
 * @author bob
 */
@Configuration
public class KafkaTopicConfiguration {

  private final KafkaProperty kafkaProperty;

  public KafkaTopicConfiguration(KafkaProperty kafkaProperty) {
    this.kafkaProperty = kafkaProperty;
  }

  /**
   * kafka管理员，委派给AdminClient以创建在应用程序上下文中定义的主题的管理员
   *
   * @return kafka管理员
   */
  @Bean
  public KafkaAdmin kafkaAdmin() {
    // 配置Kafka实例的连接地址
    Map<String, Object> props = new HashMap<>(1);
    props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperty.getBootstrapServers());
    KafkaAdmin admin = new KafkaAdmin(props);
    return admin;
  }

  /**
   * kafka的管理客户端，用于创建、修改、删除主题等
   *
   * @return kafka的管理客户端
   */
  @Bean
  public AdminClient adminClient() {
    return AdminClient.create(kafkaAdmin().getConfigurationProperties());
  }

  /**
   * 创建一个新的Topic，如果kafka中topic已经存在，则忽略。
   *
   * @return kafka主题
   */
  @Bean
  public NewTopic mqInfo() {
    // 主题名称
    String topicName = kafkaProperty.getProducer().getTopicName();
    // 第二个参数是分区数， 第三个参数是副本数量，确保集群中配置的数目大于等于副本数量
    return new NewTopic(topicName, 3, (short) 3);
  }
}
