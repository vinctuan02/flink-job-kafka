package org.example;

// test cicd

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.Properties;

public class FlinkJob {
    public static void main(String[] args) throws Exception {
        // Tạo môi trường thực thi
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // Thiết lập cấu hình Kafka
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "10.10.12.180:9092");
        properties.setProperty("group.id", "test"); // Đặt group id cho consumer

        // Tạo một FlinkKafkaConsumer
        FlinkKafkaConsumer<String> kafkaConsumer = new FlinkKafkaConsumer<>(
                "users", // Tên topic
                new SimpleStringSchema(), // Schema
                properties // Cấu hình
        );

        // Đọc dữ liệu từ topic
        DataStream<String> stream = env.addSource(kafkaConsumer);

        // Hàm để kiểm tra xem chuỗi có phải là JSON hay không
        ObjectMapper objectMapper = new ObjectMapper();

        stream.map(new MapFunction<String, String>() {
            @Override
            public String map(String value) throws Exception {
                // Kiểm tra định dạng JSON
                boolean isJson = true;
                try {
                    JsonNode jsonNode = objectMapper.readTree(value);
                } catch (Exception e) {
                    isJson = false;
                }

                // Trả về kết quả kiểm tra
                if (isJson) {
                    return "Received JSON: " + value;
                } else {
                    return "Received String: " + value;
                }
            }
        }).print(); // In kết quả ra console

        // Bắt đầu thực thi job
        env.execute("Kafka Flink Job");
    }
}
