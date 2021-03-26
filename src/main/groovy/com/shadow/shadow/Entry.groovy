package com.shadow.shadow

import groovy.transform.Canonical
import groovy.transform.ToString

import java.lang.reflect.Array

@ToString(includeNames = true)
class Entry {
    private String id
    private String time
    private List<Messaging> messaging

    Entry() {

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
