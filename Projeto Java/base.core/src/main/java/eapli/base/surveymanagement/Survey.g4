grammar Survey;

start: questionario;

alfanumerico: PALAVRA
            | NUMERO;

pontucao: PONTO_FINAL
        | PONTO_INTERROGACAO
        | PONTO_EXCLAMACAO;

regraIdQuestionario: alfanumerico+ HIFEN alfanumerico+;

regraTitulo: FRASE NEWLINE;

regraMensagem: (FRASE pontucao NEWLINE)+;

regraId: NUMERO+;

regraPergunta: FRASE PONTO_INTERROGACAO NEWLINE;

pergunta: regraId FIM regraPergunta regraMensagem?;

seccao: regraId FIM regraTitulo regraMensagem pergunta+;

questionario: regraIdQuestionario FIM regraTitulo regraMensagem? seccao+ regraMensagem;

//------------------- TOKENS -------------------

MANDATORY: 'mandatory';
OPTIONAL: 'optional';
CONDITION_DEPENDENT: 'condition dependent';

FREE_TEXT: 'Free-Text';
NUMERIC: 'Numeric';
SINGLE_CHOICE:'Single-Choice';
SINGLE_CHOICE1: 'Single-Choice with input value';
MULTIPLE_CHOICE:'Multiple-Choice';
MULTIPLE_CHOICE1: 'Multiple-Choice with input value';
SORTING_OPTIONS:'Sorting Options';
SCALING_OPTIONS:'Scaling Options';

NUMERO: [0-9];
PALAVRA: [a-zA-Z]+;
FRASE: PALAVRA (VIRGULA? ESPACO (PALAVRA|NUMERO+))*;

HIFEN: '-';
ESPACO: ' '|'\t';
PONTO_FINAL: '.';
DOIS_PONTOS: ':';
VIRGULA: ',';
FIM: ';';
PONTO_INTERROGACAO: '?';
PONTO_EXCLAMACAO: '!';
NEWLINE : [\r\n] ;