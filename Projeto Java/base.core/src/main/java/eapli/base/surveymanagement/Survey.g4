grammar Survey;

alfa: MINUSCULA
    | MAIUSCULA;

alfanumerico: alfa
            | NUMERO;

palavra : (alfa)+ ;

frase : palavra frase
      | palavra ;

question : frase PONTO_INTERROGACAO;

//------------------- TOKENS -------------------

MANDATORY: 'mandatory';
OPTIONAL: 'optional';
CONDITION_DEPENDENT: 'condition dependent';

NUMERO: [0-9];
MINUSCULA: [a-z];
MAIUSCULA: [A-Z];
HIFEN: '-';
PONTO_INTERROGACAO: '?';

WS : [ \t\r\n]+ -> skip;