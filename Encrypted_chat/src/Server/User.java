/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.util.List;

/**
 *
 * @author Henrique
 */
public class User {

    private String name;
    private List<List<String>> messages;

    public User(String name, List<List<String>> messages) {
        this.name = name;
        this.messages = messages;
    }

    public List<List<String>> getMessages() {
        return messages;
    }

    public void setMessages(List<List<String>> messages) {
        this.messages = messages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
