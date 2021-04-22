package shadow

import org.springframework.stereotype.Service

@Service
class BotMessages {

    private ArrayList inputMessages
    private ArrayList outputMessages

    BotMessages(){
        this.inputMessages = new ArrayList()
        this.outputMessages = new ArrayList()
        constructorInputMessages()
        constructorOutputMessages()
    }

    String getInputMessages(int pos){
        return this.inputMessages.get(pos).toString()
    }

    String getOutputMessages(int pos){
        return this.outputMessages.get(pos).toString()
    }

    private void constructorInputMessages(){
        this.inputMessages.add("(?=.*(sua|tua|quantos))(?=.*(idade|anos)).*")
        this.inputMessages.add("(?=.*(seu|teu))(?=.*nome).*")
        this.inputMessages.add("(?=.*(planos|plano|agenda|compromissos))(?=.*(semana)).*")
        this.inputMessages.add("(?=.*(qual|previsao|quantos))(?=.*(temperatura|clima|tempo)).*")
    }

    private void constructorOutputMessages(){
        this.outputMessages.add("Eu não tenho nem 7 semanas de vida :D")
        this.outputMessages.add("Opa, o meu nome é Shadow")
        this.outputMessages.add("call Calendar")
        this.outputMessages.add("call Temperature")
    }
}
