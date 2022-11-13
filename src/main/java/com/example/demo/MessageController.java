package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.*;



@RestController
public class MessageController {
    private final List<String> messages = new ArrayList<>();
    // curl -X GET http://localhost:8080/messages -i
    @GetMapping("messages_old")
    public ResponseEntity<List<String>> getMessages() {
        return ResponseEntity.ok(messages);
    }
    // curl -i -X POST http://localhost:8080/messages -H "Content-Type: application/json" -d "Hello1"
    @PostMapping("messages")
    public ResponseEntity<Void> addMessage(@RequestBody String text) {
        messages.add(text);
        return ResponseEntity.accepted().build();
    }



    // curl -X GET http://localhost:8080/messages/1
    @GetMapping("messages/{index}")
    public ResponseEntity<String> getMessage(@PathVariable("index") Integer
                                                     index) {
        return ResponseEntity.ok(messages.get(index));
    }





    // curl -X DELETE http://localhost:8080/messages/0 -i
    @DeleteMapping("messages/{index}")
    public ResponseEntity<Void> deleteText(@PathVariable("index") Integer
                                                   index) {
        messages.remove((int) index);
        return ResponseEntity.noContent().build();
    }
    // curl -X PUT http://localhost:8080/messages/1 -H "Content-Type: application/json" -d "apdateeee" -i
    @PutMapping("messages/{index}")
    public ResponseEntity<Void> updateMessage(
            @PathVariable("index") Integer i,
            @RequestBody String message) {
        messages.remove((int) i);
        messages.add(i, message);
        return ResponseEntity.accepted().build();
    }

    //********************************************************************************
    // на оценку 4
    //********************************************************************************
    // curl -X GET http://localhost:8080/messages/search/onemore -i
    @GetMapping("messages/search/{text}")
    public ResponseEntity<String> getMessage(@PathVariable("text") String str) {
        Integer index=messages.indexOf(str);
        return ResponseEntity.ok(index.toString());
    }

    // curl -X GET http://localhost:8080/messages/count -i
    @GetMapping("messages/count")
    public ResponseEntity<String> getMessage() {
        return ResponseEntity.ok(Integer.toString(messages.size()));
    }

    // curl -X POST  http://localhost:8080/messages/1/create -H "Content-Type: application/json" -d "7" -i
    @PostMapping("messages/{index}/create")
    public ResponseEntity<Void> addMessage(@RequestBody String text, @PathVariable("index") Integer index) {
        messages.add(index, text);
        return ResponseEntity.accepted().build();
    }

    // curl -X DELETE http://localhost:8080/messages/search/8 -i
    @DeleteMapping("messages/search/{text}")
    public ResponseEntity<Void> deleteText(@PathVariable("text") String str) {
        int i=0;
        while (i< messages.size()){
            if (messages.get(i).indexOf(str)!=-1){
                messages.remove((int) i);
                continue;
            }
            i++;
        }
        return ResponseEntity.noContent().build();
    }

    // curl -i -X GET http://localhost:8080/messages -H "Content-Type: application/json" -d "1"
    @GetMapping("messages")
    public ResponseEntity<List<String>> getMessages(
            @RequestBody String text) {

        List<String>    output = new ArrayList<>();
        String          cur_text;

        // иначе, анализируем каждую строку
        for (int i=0;i< messages.size();i++)
        {
            cur_text = messages.get(i);
            if (cur_text.indexOf(text) != -1)
                output.add(cur_text);
        }

        return ResponseEntity.ok(output);
    }





}
