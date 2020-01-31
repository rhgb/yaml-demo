package com.example;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Input {
    /**
     * 如果你的变量名和文件里面的名字一样，就这么写就行了
     */
    public String someWord;
    public List<String> words;
    /**
     * 这种名字不太一样的场景，你需要用这种注解来告诉他在yaml文件里的名字是什么
     */
    @JsonProperty("input number")
    public int inputNumber;
}
