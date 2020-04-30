package com.ipushka001.example.lesson1;

class Contact {
    private final String name;
    private final String phoneNumber;

    Contact(String name, String phoneNumber){
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    String getName(){ return name;}
    String getPhoneNumber(){ return phoneNumber;}
}
