package com.nguyenvm.stockservice.config;

import com.nguyenvm.common.util.CommonConstants;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.sleuth.autoconfig.zipkin2.ZipkinAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import zipkin2.Call;
import zipkin2.Span;
import zipkin2.codec.Encoding;
import zipkin2.reporter.AsyncReporter;
import zipkin2.reporter.BytesMessageEncoder;
import zipkin2.reporter.Reporter;
import zipkin2.reporter.Sender;

import java.util.List;

@Configuration
public class SleuthSpanSenderConfiguration {
    @Autowired
    @Qualifier("zipkinKafkaTemplate")
    private KafkaTemplate<String, String> kafkaTemplate;

    @Bean(ZipkinAutoConfiguration.REPORTER_BEAN_NAME)
    Reporter<Span> myReporter() {
        return AsyncReporter.create(mySender());
    }

    @Bean(ZipkinAutoConfiguration.SENDER_BEAN_NAME)
    MySender mySender() {
        return new MySender();
    }

    class MySender extends Sender {
        private BytesMessageEncoder encoder;

        public MySender() {
            this.encoder = BytesMessageEncoder.forEncoding(encoding());
        }

        @Override
        public Encoding encoding() {
            return Encoding.JSON;
        }

        @Override
        public int messageMaxBytes() {
            return Integer.MAX_VALUE;
        }

        @Override
        public int messageSizeInBytes(List<byte[]> encodedSpans) {
            return encoding().listSizeInBytes(encodedSpans);
        }

        @Override
        public Call<Void> sendSpans(List<byte[]> encodedSpans) {
            byte[] encodedMessage = encoder.encode(encodedSpans);
            kafkaTemplate.send(new ProducerRecord(CommonConstants.ZIPKIN_TOPIC, encodedMessage));

            return Call.create(null);
        }
    }
}