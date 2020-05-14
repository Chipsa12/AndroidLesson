package com.ipushka001.example.lesson1;

class Contact {
    private final String name;
    private final String phoneNumber;

    static final Contact[] contacts = {
            new Contact("50cent", "8800553535"),
            new Contact("Булка с корицей", "8800124578")
    };

    private Contact(String name, String phoneNumber){
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    String getName(){
        return name;
    }

    String getPhoneNumber(){
        return phoneNumber;
    }
}
