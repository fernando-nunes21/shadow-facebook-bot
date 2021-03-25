package com.shadow.shadow

import groovy.transform.Canonical

import java.lang.reflect.Array

class Entry {
    private String id
    private String time
    private List<Messaging> messaging

    Entry() {
        this.id = id
        this.time = time
        this.messaging = messaging
    }

    String getId() {
        return id
    }

    String getTime() {
        return time
    }

    List<Messaging> getMessaging() {
        return messaging
    }

    void setId(String id) {
        this.id = id
    }

    void setTime(String time) {
        this.time = time
    }

    void setMessaging(List<Messaging> messaging) {
        this.messaging = messaging
    }
}
