package org.example;



import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.datastream.DataStream;

public class HelloWorldJob {
    public static void main(String[] args) throws Exception {
        // Tạo môi trường thực thi
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // Tạo một nguồn dữ liệu đơn giản
        DataStream<String> stream = env.fromElements("Message 1", "Message 2", "Message 3");

        // Áp dụng hàm map để xử lý dữ liệu
        stream.map(new MapFunction<String, String>() {
            @Override
            public String map(String value) throws Exception {
                // Trả về thông điệp Hello World cho mỗi tin nhắn
                return "Hello, World! Received: " + value;
            }
        }).print(); // In kết quả ra console

        // Bắt đầu thực thi job
        env.execute("Hello World Job");
    }
}
