package shadow.message

import groovy.transform.Canonical
import groovy.transform.CompileDynamic

@Canonical
@CompileDynamic
class MessageToFacebook {

    String text

    String getText() {
        text
    }

}
