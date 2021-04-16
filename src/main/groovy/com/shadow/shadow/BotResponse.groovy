package com.shadow.shadow

import calendar.CalendarIntegration
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match
import message.FacebookReceived
import org.springframework.stereotype.Service

import java.util.regex.Matcher
import java.util.regex.Pattern

@Service
class BotResponse {

    //TODO -- Nome botMessage | metodo deveria receber somente mensagem e dados importantes user
    String selectResponse(FacebookReceived message){
        String botMessage = "Desculpe, eu to aprendendo ainda as coisas e nao entendi sua mensagem"
        //TODO - Isolar Mensagem facebook / quebrar classe anteriormente
        String clientMessage = message.getEntry().get(0).getMessaging().get(0).getMessage().getText().toLowerCase()


        if(clientMessage =~ "(sua idade)|(tua idade)|(quantos anos)"){
            botMessage = "Eu não tenho nem 7 semanas de vida :D"
        }
        else if(clientMessage =~ "(seu nome)|(teu nome)"){
            botMessage = "Opa, o meu nome é Shadow"
        }
        else if(clientMessage =~ "(sua agenda da semana)|(tua agenda da semana)|(seus planos pra semana)"){
            //TODO - NUNCA MAIS VOU INSTANCIAR CLASSES DESSE JEITO dentro do metodo
            CalendarIntegration calendar = new CalendarIntegration()
            Map<Integer, String> calendarResponse = calendar.getCalendarStatus()
            if(calendarResponse.isEmpty()){
                botMessage = "Não há eventos na minha agenda essa semana!"
            }
            else{
                botMessage = "Olha só, minha semana tá assim: \n"
                //TODO - Forma mais elegante (For)
                for(int i=0;i<calendarResponse.size();i++){
                    botMessage = botMessage+calendarResponse.get(i)+"\n"
                }
            }
        }

        return botMessage
    }
}
