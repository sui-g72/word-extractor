package com.example.vocabapp;

import java.awt.PageAttributes.MediaType;
import java.io.InputStream;
import java.net.URI;

import org.apache.tomcat.util.file.ConfigurationSource.Resource;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class AppConfig {
    public static class JacksonMessagePackProvider extends JacksonJsonParser {
        public JacksonMessagePackProvider() {
            super(new ObjectMapper(new JsonFactory()));
        }

        protected boolean hasMatchingMediaType(MediaType mediaType) {
            if (mediaType != null) {
                String subtype = mediaType();
                return "x-msgpack".equals(subtype);
            }
            return false;
        }

		private String mediaType() {
			// TODO 自動生成されたメソッド・スタブ
			return null;
		}
    }

    static class JerseyConfig extends Resource {
        public JerseyConfig(InputStream inputStream, URI uri) {
			super(inputStream, uri);
			// TODO 自動生成されたコンストラクター・スタブ
		}

		{
            this.packages("com.translater.org");
        }

		private void packages(String string) {
			// TODO 自動生成されたメソッド・スタブ
			
		}
    }
}