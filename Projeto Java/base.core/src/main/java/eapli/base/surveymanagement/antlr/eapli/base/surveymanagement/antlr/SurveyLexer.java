// Generated from C:/Users/arian/lei21_22_s4_2de_01/Projeto Java/base.core/src/main/java/eapli/base/surveymanagement/antlr/eapli/base/surveymanagement/antlr\Survey.g4 by ANTLR 4.10.1
package eapli.base.surveymanagement.antlr.eapli.base.surveymanagement.antlr;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SurveyLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.10.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, MANDATORY=5, OPTIONAL=6, CONDITION_DEPENDENT=7, 
		FREE_TEXT=8, NUMERIC=9, SINGLE_CHOICE=10, SINGLE_CHOICE1=11, MULTIPLE_CHOICE=12, 
		MULTIPLE_CHOICE1=13, SORTING_OPTIONS=14, SCALING_OPTIONS=15, DECIMALS_ALLOWED=16, 
		NUMERO=17, PALAVRA=18, HIFEN=19, ESPACO=20, PONTO_FINAL=21, DOIS_PONTOS=22, 
		RETICENCIAS=23, VIRGULA=24, PONTO_INTERROGACAO=25, PONTO_EXCLAMACAO=26, 
		PARENTESIS_DIREITO=27, PARENTESIS_ESQUERDO=28, NEWLINE=29;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "MANDATORY", "OPTIONAL", "CONDITION_DEPENDENT", 
			"FREE_TEXT", "NUMERIC", "SINGLE_CHOICE", "SINGLE_CHOICE1", "MULTIPLE_CHOICE", 
			"MULTIPLE_CHOICE1", "SORTING_OPTIONS", "SCALING_OPTIONS", "DECIMALS_ALLOWED", 
			"NUMERO", "PALAVRA", "HIFEN", "ESPACO", "PONTO_FINAL", "DOIS_PONTOS", 
			"RETICENCIAS", "VIRGULA", "PONTO_INTERROGACAO", "PONTO_EXCLAMACAO", "PARENTESIS_DIREITO", 
			"PARENTESIS_ESQUERDO", "NEWLINE"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'Scale: '", "'Type: '", "'Section Obligatoriness: '", "'Repeatability: '", 
			"'mandatory'", "'optional'", "'condition dependent'", "'Free-Text'", 
			"'Numeric'", "'Single-Choice'", "'Single-Choice with input value'", "'Multiple-Choice'", 
			"'Multiple-Choice with input value'", "'Sorting Options'", "'Scaling Options'", 
			"'Decimal numbers are allowed!'", null, null, "'-'", null, "'.'", "':'", 
			"'...'", "','", "'?'", "'!'", "')'", "'('"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, "MANDATORY", "OPTIONAL", "CONDITION_DEPENDENT", 
			"FREE_TEXT", "NUMERIC", "SINGLE_CHOICE", "SINGLE_CHOICE1", "MULTIPLE_CHOICE", 
			"MULTIPLE_CHOICE1", "SORTING_OPTIONS", "SCALING_OPTIONS", "DECIMALS_ALLOWED", 
			"NUMERO", "PALAVRA", "HIFEN", "ESPACO", "PONTO_FINAL", "DOIS_PONTOS", 
			"RETICENCIAS", "VIRGULA", "PONTO_INTERROGACAO", "PONTO_EXCLAMACAO", "PARENTESIS_DIREITO", 
			"PARENTESIS_ESQUERDO", "NEWLINE"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public SurveyLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Survey.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u001d\u0166\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017"+
		"\u0002\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a"+
		"\u0002\u001b\u0007\u001b\u0002\u001c\u0007\u001c\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010"+
		"\u0001\u0011\u0004\u0011\u014b\b\u0011\u000b\u0011\f\u0011\u014c\u0001"+
		"\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001"+
		"\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0017\u0001\u0017\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019\u0001"+
		"\u001a\u0001\u001a\u0001\u001b\u0001\u001b\u0001\u001c\u0001\u001c\u0000"+
		"\u0000\u001d\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b"+
		"\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u001b"+
		"\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012%\u0013\'\u0014)\u0015+\u0016"+
		"-\u0017/\u00181\u00193\u001a5\u001b7\u001c9\u001d\u0001\u0000\u0004\u0001"+
		"\u000009\u0002\u0000AZaz\u0002\u0000\t\t  \u0002\u0000\n\n\r\r\u0166\u0000"+
		"\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000"+
		"\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000"+
		"\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r"+
		"\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011"+
		"\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015"+
		"\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019"+
		"\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d"+
		"\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000\u0000\u0000\u0000!\u0001"+
		"\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000\u0000%\u0001\u0000\u0000"+
		"\u0000\u0000\'\u0001\u0000\u0000\u0000\u0000)\u0001\u0000\u0000\u0000"+
		"\u0000+\u0001\u0000\u0000\u0000\u0000-\u0001\u0000\u0000\u0000\u0000/"+
		"\u0001\u0000\u0000\u0000\u00001\u0001\u0000\u0000\u0000\u00003\u0001\u0000"+
		"\u0000\u0000\u00005\u0001\u0000\u0000\u0000\u00007\u0001\u0000\u0000\u0000"+
		"\u00009\u0001\u0000\u0000\u0000\u0001;\u0001\u0000\u0000\u0000\u0003C"+
		"\u0001\u0000\u0000\u0000\u0005J\u0001\u0000\u0000\u0000\u0007c\u0001\u0000"+
		"\u0000\u0000\ts\u0001\u0000\u0000\u0000\u000b}\u0001\u0000\u0000\u0000"+
		"\r\u0086\u0001\u0000\u0000\u0000\u000f\u009a\u0001\u0000\u0000\u0000\u0011"+
		"\u00a4\u0001\u0000\u0000\u0000\u0013\u00ac\u0001\u0000\u0000\u0000\u0015"+
		"\u00ba\u0001\u0000\u0000\u0000\u0017\u00d9\u0001\u0000\u0000\u0000\u0019"+
		"\u00e9\u0001\u0000\u0000\u0000\u001b\u010a\u0001\u0000\u0000\u0000\u001d"+
		"\u011a\u0001\u0000\u0000\u0000\u001f\u012a\u0001\u0000\u0000\u0000!\u0147"+
		"\u0001\u0000\u0000\u0000#\u014a\u0001\u0000\u0000\u0000%\u014e\u0001\u0000"+
		"\u0000\u0000\'\u0150\u0001\u0000\u0000\u0000)\u0152\u0001\u0000\u0000"+
		"\u0000+\u0154\u0001\u0000\u0000\u0000-\u0156\u0001\u0000\u0000\u0000/"+
		"\u015a\u0001\u0000\u0000\u00001\u015c\u0001\u0000\u0000\u00003\u015e\u0001"+
		"\u0000\u0000\u00005\u0160\u0001\u0000\u0000\u00007\u0162\u0001\u0000\u0000"+
		"\u00009\u0164\u0001\u0000\u0000\u0000;<\u0005S\u0000\u0000<=\u0005c\u0000"+
		"\u0000=>\u0005a\u0000\u0000>?\u0005l\u0000\u0000?@\u0005e\u0000\u0000"+
		"@A\u0005:\u0000\u0000AB\u0005 \u0000\u0000B\u0002\u0001\u0000\u0000\u0000"+
		"CD\u0005T\u0000\u0000DE\u0005y\u0000\u0000EF\u0005p\u0000\u0000FG\u0005"+
		"e\u0000\u0000GH\u0005:\u0000\u0000HI\u0005 \u0000\u0000I\u0004\u0001\u0000"+
		"\u0000\u0000JK\u0005S\u0000\u0000KL\u0005e\u0000\u0000LM\u0005c\u0000"+
		"\u0000MN\u0005t\u0000\u0000NO\u0005i\u0000\u0000OP\u0005o\u0000\u0000"+
		"PQ\u0005n\u0000\u0000QR\u0005 \u0000\u0000RS\u0005O\u0000\u0000ST\u0005"+
		"b\u0000\u0000TU\u0005l\u0000\u0000UV\u0005i\u0000\u0000VW\u0005g\u0000"+
		"\u0000WX\u0005a\u0000\u0000XY\u0005t\u0000\u0000YZ\u0005o\u0000\u0000"+
		"Z[\u0005r\u0000\u0000[\\\u0005i\u0000\u0000\\]\u0005n\u0000\u0000]^\u0005"+
		"e\u0000\u0000^_\u0005s\u0000\u0000_`\u0005s\u0000\u0000`a\u0005:\u0000"+
		"\u0000ab\u0005 \u0000\u0000b\u0006\u0001\u0000\u0000\u0000cd\u0005R\u0000"+
		"\u0000de\u0005e\u0000\u0000ef\u0005p\u0000\u0000fg\u0005e\u0000\u0000"+
		"gh\u0005a\u0000\u0000hi\u0005t\u0000\u0000ij\u0005a\u0000\u0000jk\u0005"+
		"b\u0000\u0000kl\u0005i\u0000\u0000lm\u0005l\u0000\u0000mn\u0005i\u0000"+
		"\u0000no\u0005t\u0000\u0000op\u0005y\u0000\u0000pq\u0005:\u0000\u0000"+
		"qr\u0005 \u0000\u0000r\b\u0001\u0000\u0000\u0000st\u0005m\u0000\u0000"+
		"tu\u0005a\u0000\u0000uv\u0005n\u0000\u0000vw\u0005d\u0000\u0000wx\u0005"+
		"a\u0000\u0000xy\u0005t\u0000\u0000yz\u0005o\u0000\u0000z{\u0005r\u0000"+
		"\u0000{|\u0005y\u0000\u0000|\n\u0001\u0000\u0000\u0000}~\u0005o\u0000"+
		"\u0000~\u007f\u0005p\u0000\u0000\u007f\u0080\u0005t\u0000\u0000\u0080"+
		"\u0081\u0005i\u0000\u0000\u0081\u0082\u0005o\u0000\u0000\u0082\u0083\u0005"+
		"n\u0000\u0000\u0083\u0084\u0005a\u0000\u0000\u0084\u0085\u0005l\u0000"+
		"\u0000\u0085\f\u0001\u0000\u0000\u0000\u0086\u0087\u0005c\u0000\u0000"+
		"\u0087\u0088\u0005o\u0000\u0000\u0088\u0089\u0005n\u0000\u0000\u0089\u008a"+
		"\u0005d\u0000\u0000\u008a\u008b\u0005i\u0000\u0000\u008b\u008c\u0005t"+
		"\u0000\u0000\u008c\u008d\u0005i\u0000\u0000\u008d\u008e\u0005o\u0000\u0000"+
		"\u008e\u008f\u0005n\u0000\u0000\u008f\u0090\u0005 \u0000\u0000\u0090\u0091"+
		"\u0005d\u0000\u0000\u0091\u0092\u0005e\u0000\u0000\u0092\u0093\u0005p"+
		"\u0000\u0000\u0093\u0094\u0005e\u0000\u0000\u0094\u0095\u0005n\u0000\u0000"+
		"\u0095\u0096\u0005d\u0000\u0000\u0096\u0097\u0005e\u0000\u0000\u0097\u0098"+
		"\u0005n\u0000\u0000\u0098\u0099\u0005t\u0000\u0000\u0099\u000e\u0001\u0000"+
		"\u0000\u0000\u009a\u009b\u0005F\u0000\u0000\u009b\u009c\u0005r\u0000\u0000"+
		"\u009c\u009d\u0005e\u0000\u0000\u009d\u009e\u0005e\u0000\u0000\u009e\u009f"+
		"\u0005-\u0000\u0000\u009f\u00a0\u0005T\u0000\u0000\u00a0\u00a1\u0005e"+
		"\u0000\u0000\u00a1\u00a2\u0005x\u0000\u0000\u00a2\u00a3\u0005t\u0000\u0000"+
		"\u00a3\u0010\u0001\u0000\u0000\u0000\u00a4\u00a5\u0005N\u0000\u0000\u00a5"+
		"\u00a6\u0005u\u0000\u0000\u00a6\u00a7\u0005m\u0000\u0000\u00a7\u00a8\u0005"+
		"e\u0000\u0000\u00a8\u00a9\u0005r\u0000\u0000\u00a9\u00aa\u0005i\u0000"+
		"\u0000\u00aa\u00ab\u0005c\u0000\u0000\u00ab\u0012\u0001\u0000\u0000\u0000"+
		"\u00ac\u00ad\u0005S\u0000\u0000\u00ad\u00ae\u0005i\u0000\u0000\u00ae\u00af"+
		"\u0005n\u0000\u0000\u00af\u00b0\u0005g\u0000\u0000\u00b0\u00b1\u0005l"+
		"\u0000\u0000\u00b1\u00b2\u0005e\u0000\u0000\u00b2\u00b3\u0005-\u0000\u0000"+
		"\u00b3\u00b4\u0005C\u0000\u0000\u00b4\u00b5\u0005h\u0000\u0000\u00b5\u00b6"+
		"\u0005o\u0000\u0000\u00b6\u00b7\u0005i\u0000\u0000\u00b7\u00b8\u0005c"+
		"\u0000\u0000\u00b8\u00b9\u0005e\u0000\u0000\u00b9\u0014\u0001\u0000\u0000"+
		"\u0000\u00ba\u00bb\u0005S\u0000\u0000\u00bb\u00bc\u0005i\u0000\u0000\u00bc"+
		"\u00bd\u0005n\u0000\u0000\u00bd\u00be\u0005g\u0000\u0000\u00be\u00bf\u0005"+
		"l\u0000\u0000\u00bf\u00c0\u0005e\u0000\u0000\u00c0\u00c1\u0005-\u0000"+
		"\u0000\u00c1\u00c2\u0005C\u0000\u0000\u00c2\u00c3\u0005h\u0000\u0000\u00c3"+
		"\u00c4\u0005o\u0000\u0000\u00c4\u00c5\u0005i\u0000\u0000\u00c5\u00c6\u0005"+
		"c\u0000\u0000\u00c6\u00c7\u0005e\u0000\u0000\u00c7\u00c8\u0005 \u0000"+
		"\u0000\u00c8\u00c9\u0005w\u0000\u0000\u00c9\u00ca\u0005i\u0000\u0000\u00ca"+
		"\u00cb\u0005t\u0000\u0000\u00cb\u00cc\u0005h\u0000\u0000\u00cc\u00cd\u0005"+
		" \u0000\u0000\u00cd\u00ce\u0005i\u0000\u0000\u00ce\u00cf\u0005n\u0000"+
		"\u0000\u00cf\u00d0\u0005p\u0000\u0000\u00d0\u00d1\u0005u\u0000\u0000\u00d1"+
		"\u00d2\u0005t\u0000\u0000\u00d2\u00d3\u0005 \u0000\u0000\u00d3\u00d4\u0005"+
		"v\u0000\u0000\u00d4\u00d5\u0005a\u0000\u0000\u00d5\u00d6\u0005l\u0000"+
		"\u0000\u00d6\u00d7\u0005u\u0000\u0000\u00d7\u00d8\u0005e\u0000\u0000\u00d8"+
		"\u0016\u0001\u0000\u0000\u0000\u00d9\u00da\u0005M\u0000\u0000\u00da\u00db"+
		"\u0005u\u0000\u0000\u00db\u00dc\u0005l\u0000\u0000\u00dc\u00dd\u0005t"+
		"\u0000\u0000\u00dd\u00de\u0005i\u0000\u0000\u00de\u00df\u0005p\u0000\u0000"+
		"\u00df\u00e0\u0005l\u0000\u0000\u00e0\u00e1\u0005e\u0000\u0000\u00e1\u00e2"+
		"\u0005-\u0000\u0000\u00e2\u00e3\u0005C\u0000\u0000\u00e3\u00e4\u0005h"+
		"\u0000\u0000\u00e4\u00e5\u0005o\u0000\u0000\u00e5\u00e6\u0005i\u0000\u0000"+
		"\u00e6\u00e7\u0005c\u0000\u0000\u00e7\u00e8\u0005e\u0000\u0000\u00e8\u0018"+
		"\u0001\u0000\u0000\u0000\u00e9\u00ea\u0005M\u0000\u0000\u00ea\u00eb\u0005"+
		"u\u0000\u0000\u00eb\u00ec\u0005l\u0000\u0000\u00ec\u00ed\u0005t\u0000"+
		"\u0000\u00ed\u00ee\u0005i\u0000\u0000\u00ee\u00ef\u0005p\u0000\u0000\u00ef"+
		"\u00f0\u0005l\u0000\u0000\u00f0\u00f1\u0005e\u0000\u0000\u00f1\u00f2\u0005"+
		"-\u0000\u0000\u00f2\u00f3\u0005C\u0000\u0000\u00f3\u00f4\u0005h\u0000"+
		"\u0000\u00f4\u00f5\u0005o\u0000\u0000\u00f5\u00f6\u0005i\u0000\u0000\u00f6"+
		"\u00f7\u0005c\u0000\u0000\u00f7\u00f8\u0005e\u0000\u0000\u00f8\u00f9\u0005"+
		" \u0000\u0000\u00f9\u00fa\u0005w\u0000\u0000\u00fa\u00fb\u0005i\u0000"+
		"\u0000\u00fb\u00fc\u0005t\u0000\u0000\u00fc\u00fd\u0005h\u0000\u0000\u00fd"+
		"\u00fe\u0005 \u0000\u0000\u00fe\u00ff\u0005i\u0000\u0000\u00ff\u0100\u0005"+
		"n\u0000\u0000\u0100\u0101\u0005p\u0000\u0000\u0101\u0102\u0005u\u0000"+
		"\u0000\u0102\u0103\u0005t\u0000\u0000\u0103\u0104\u0005 \u0000\u0000\u0104"+
		"\u0105\u0005v\u0000\u0000\u0105\u0106\u0005a\u0000\u0000\u0106\u0107\u0005"+
		"l\u0000\u0000\u0107\u0108\u0005u\u0000\u0000\u0108\u0109\u0005e\u0000"+
		"\u0000\u0109\u001a\u0001\u0000\u0000\u0000\u010a\u010b\u0005S\u0000\u0000"+
		"\u010b\u010c\u0005o\u0000\u0000\u010c\u010d\u0005r\u0000\u0000\u010d\u010e"+
		"\u0005t\u0000\u0000\u010e\u010f\u0005i\u0000\u0000\u010f\u0110\u0005n"+
		"\u0000\u0000\u0110\u0111\u0005g\u0000\u0000\u0111\u0112\u0005 \u0000\u0000"+
		"\u0112\u0113\u0005O\u0000\u0000\u0113\u0114\u0005p\u0000\u0000\u0114\u0115"+
		"\u0005t\u0000\u0000\u0115\u0116\u0005i\u0000\u0000\u0116\u0117\u0005o"+
		"\u0000\u0000\u0117\u0118\u0005n\u0000\u0000\u0118\u0119\u0005s\u0000\u0000"+
		"\u0119\u001c\u0001\u0000\u0000\u0000\u011a\u011b\u0005S\u0000\u0000\u011b"+
		"\u011c\u0005c\u0000\u0000\u011c\u011d\u0005a\u0000\u0000\u011d\u011e\u0005"+
		"l\u0000\u0000\u011e\u011f\u0005i\u0000\u0000\u011f\u0120\u0005n\u0000"+
		"\u0000\u0120\u0121\u0005g\u0000\u0000\u0121\u0122\u0005 \u0000\u0000\u0122"+
		"\u0123\u0005O\u0000\u0000\u0123\u0124\u0005p\u0000\u0000\u0124\u0125\u0005"+
		"t\u0000\u0000\u0125\u0126\u0005i\u0000\u0000\u0126\u0127\u0005o\u0000"+
		"\u0000\u0127\u0128\u0005n\u0000\u0000\u0128\u0129\u0005s\u0000\u0000\u0129"+
		"\u001e\u0001\u0000\u0000\u0000\u012a\u012b\u0005D\u0000\u0000\u012b\u012c"+
		"\u0005e\u0000\u0000\u012c\u012d\u0005c\u0000\u0000\u012d\u012e\u0005i"+
		"\u0000\u0000\u012e\u012f\u0005m\u0000\u0000\u012f\u0130\u0005a\u0000\u0000"+
		"\u0130\u0131\u0005l\u0000\u0000\u0131\u0132\u0005 \u0000\u0000\u0132\u0133"+
		"\u0005n\u0000\u0000\u0133\u0134\u0005u\u0000\u0000\u0134\u0135\u0005m"+
		"\u0000\u0000\u0135\u0136\u0005b\u0000\u0000\u0136\u0137\u0005e\u0000\u0000"+
		"\u0137\u0138\u0005r\u0000\u0000\u0138\u0139\u0005s\u0000\u0000\u0139\u013a"+
		"\u0005 \u0000\u0000\u013a\u013b\u0005a\u0000\u0000\u013b\u013c\u0005r"+
		"\u0000\u0000\u013c\u013d\u0005e\u0000\u0000\u013d\u013e\u0005 \u0000\u0000"+
		"\u013e\u013f\u0005a\u0000\u0000\u013f\u0140\u0005l\u0000\u0000\u0140\u0141"+
		"\u0005l\u0000\u0000\u0141\u0142\u0005o\u0000\u0000\u0142\u0143\u0005w"+
		"\u0000\u0000\u0143\u0144\u0005e\u0000\u0000\u0144\u0145\u0005d\u0000\u0000"+
		"\u0145\u0146\u0005!\u0000\u0000\u0146 \u0001\u0000\u0000\u0000\u0147\u0148"+
		"\u0007\u0000\u0000\u0000\u0148\"\u0001\u0000\u0000\u0000\u0149\u014b\u0007"+
		"\u0001\u0000\u0000\u014a\u0149\u0001\u0000\u0000\u0000\u014b\u014c\u0001"+
		"\u0000\u0000\u0000\u014c\u014a\u0001\u0000\u0000\u0000\u014c\u014d\u0001"+
		"\u0000\u0000\u0000\u014d$\u0001\u0000\u0000\u0000\u014e\u014f\u0005-\u0000"+
		"\u0000\u014f&\u0001\u0000\u0000\u0000\u0150\u0151\u0007\u0002\u0000\u0000"+
		"\u0151(\u0001\u0000\u0000\u0000\u0152\u0153\u0005.\u0000\u0000\u0153*"+
		"\u0001\u0000\u0000\u0000\u0154\u0155\u0005:\u0000\u0000\u0155,\u0001\u0000"+
		"\u0000\u0000\u0156\u0157\u0005.\u0000\u0000\u0157\u0158\u0005.\u0000\u0000"+
		"\u0158\u0159\u0005.\u0000\u0000\u0159.\u0001\u0000\u0000\u0000\u015a\u015b"+
		"\u0005,\u0000\u0000\u015b0\u0001\u0000\u0000\u0000\u015c\u015d\u0005?"+
		"\u0000\u0000\u015d2\u0001\u0000\u0000\u0000\u015e\u015f\u0005!\u0000\u0000"+
		"\u015f4\u0001\u0000\u0000\u0000\u0160\u0161\u0005)\u0000\u0000\u01616"+
		"\u0001\u0000\u0000\u0000\u0162\u0163\u0005(\u0000\u0000\u01638\u0001\u0000"+
		"\u0000\u0000\u0164\u0165\u0007\u0003\u0000\u0000\u0165:\u0001\u0000\u0000"+
		"\u0000\u0002\u0000\u014c\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}