grammar Survey;

start: questionario;

alfanumerico: PALAVRA
            | NUMERO;

pontucao: PONTO_FINAL
        | PONTO_INTERROGACAO
        | PONTO_EXCLAMACAO;

regraIdQuestionario: alfanumerico+ HIFEN alfanumerico+;

regraTitulo: FRASE;

regraMensagem: (FRASE pontucao NEWLINE)+;

regraId: NUMERO+;

regraPergunta: FRASE PONTO_INTERROGACAO;

questionario: regraIdQuestionario NEWLINE regraTitulo NEWLINE regraMensagem;

seccao: regraId NEWLINE regraTitulo NEWLINE regraMensagem;

pergunta: regraId NEWLINE regraPergunta NEWLINE regraMensagem;

//------------------- TOKENS -------------------

MANDATORY: 'mandatory';
OPTIONAL: 'optional';
CONDITION_DEPENDENT: 'condition dependent';

NUMERO: [0-9];
LETRA: [a-zA-Z];
PALAVRA: LETRA+;
FRASE: PALAVRA (VIRGULA? ESPACO PALAVRA)*;

HIFEN: '-';
ESPACO: ' ';
PONTO_FINAL: '.';
DOIS_PONTOS: ':';
VIRGULA: ',';
PONTO_INTERROGACAO: '?';
PONTO_EXCLAMACAO: '!';
NEWLINE : [\r\n] ;