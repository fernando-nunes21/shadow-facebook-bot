package com.shadow.shadow

import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical
import groovy.transform.ToString

@ToString(includeNames = true)
class FacebookReceived {

    private String object
    private List<Entry> entry

    FacebookReceived() {

    }

    String getObject() {
        return object
    }

    List<Entry> getEntry() {
        return entry
    }

    void setObject(String object) {
        this.object = object
    }

    void setEntry(List<Entry> entry) {
        this.entry = entry
    }
}
