package com.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    private static ObjectMapper mapper = new YAMLMapper();

    /**
     * 我们这里展示两种不同的读yaml文件的方法
     */
    public static void main(String[] args) throws Exception {
        byte[] bytes = Files.readAllBytes(Path.of("sample.yaml"));
        // 方法1
        readAsTree(bytes);
        System.out.println();
        // 方法2（推荐）
        readAsObject(bytes);
    }

    /**
     * 方法1 我们把yaml直接读成一个结构化的JsonNode
     * 这个方法的好处是不需要定义一个读取类型，在写一些测试代码的时候比较快
     * 坏处就是如果你的这个结构变得复杂，那么读取的代码还是有些冗余
     */
    private static void readAsTree(byte[] content) throws Exception {
        System.out.println("Read as tree");

        JsonNode jsonNode = mapper.readTree(content);
        // 读一个字符串
        String someWord = jsonNode.get("someWord").asText();
        System.out.println("Some word is: " + someWord);
        // 读一个数字
        int inputNumber = jsonNode.get("input number").asInt();
        System.out.println("Input number is: " + inputNumber);
        // 读一个列表
        int i = 1;
        for (JsonNode item : jsonNode.get("words")) {
            System.out.println("Word " + i + " is: " + item.asText());
            i++;
        }
    }

    /**
     * 方法2 你需要先定义一个类，他的结构和你的yaml文件是一致的
     * 参考Input.java
     * 这个方法就是要额外定义一个类，但是后面增删内容什么的就都很简单
     * 适合在正式的代码中使用
     */
    private static void readAsObject(byte[] content) throws Exception {
        System.out.println("Read as object");
        // 读一切
        Input input = mapper.readValue(content, Input.class);

        // 打印各种东西
        System.out.println("Some word is: " + input.someWord);
        System.out.println("Input number is: " + input.inputNumber);
        int i = 1;
        for (String word : input.words) {
            System.out.println("Word " + i + " is: " + word);
            i++;
        }
    }
}
