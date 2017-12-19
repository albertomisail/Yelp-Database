package ca.ece.ubc.cpen221.mp5;
// Generated from QueryLex.g4 by ANTLR 4.5.3
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QueryLex extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		OR=1, AND=2, GT=3, GTE=4, LT=5, LTE=6, EQ=7, IN=8, CATEGORY=9, NUM=10, 
		LPAREN=11, RPAREN=12, CATEGORYTOKEN=13, NAMETOKEN=14, RATINGTOKEN=15, 
		PRICETOKEN=16, WHITE_SPACE=17, STRING=18;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"OR", "AND", "GT", "GTE", "LT", "LTE", "EQ", "IN", "CATEGORY", "NUM", 
		"LPAREN", "RPAREN", "CATEGORYTOKEN", "NAMETOKEN", "RATINGTOKEN", "PRICETOKEN", 
		"WHITE_SPACE", "STRING"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'||'", "'&&'", "'>'", "'>='", "'<'", "'<='", "'='", "'in'", null, 
		null, "'('", "')'", null, "'name'", "'rating'", "'price'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "OR", "AND", "GT", "GTE", "LT", "LTE", "EQ", "IN", "CATEGORY", "NUM", 
		"LPAREN", "RPAREN", "CATEGORYTOKEN", "NAMETOKEN", "RATINGTOKEN", "PRICETOKEN", 
		"WHITE_SPACE", "STRING"
	};
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


	public QueryLex(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "QueryLex.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\24z\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\3\2\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\7\3\7"+
		"\3\7\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13"+
		"\3\f\3\f\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17"+
		"\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\22\3\22\3\22\3\22\3\23\6\23l\n\23\r\23\16\23m\3\23\3\23\6"+
		"\23r\n\23\r\23\16\23s\7\23v\n\23\f\23\16\23y\13\23\2\2\24\3\3\5\4\7\5"+
		"\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23"+
		"%\24\3\2\6\3\2\63\67\5\2\13\f\17\17\"\"\4\2C\\c|\4\2\13\13\"\"|\2\3\3"+
		"\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2"+
		"\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3"+
		"\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2"+
		"%\3\2\2\2\3\'\3\2\2\2\5*\3\2\2\2\7-\3\2\2\2\t/\3\2\2\2\13\62\3\2\2\2\r"+
		"\64\3\2\2\2\17\67\3\2\2\2\219\3\2\2\2\23<\3\2\2\2\25E\3\2\2\2\27G\3\2"+
		"\2\2\31I\3\2\2\2\33K\3\2\2\2\35T\3\2\2\2\37Y\3\2\2\2!`\3\2\2\2#f\3\2\2"+
		"\2%k\3\2\2\2\'(\7~\2\2()\7~\2\2)\4\3\2\2\2*+\7(\2\2+,\7(\2\2,\6\3\2\2"+
		"\2-.\7@\2\2.\b\3\2\2\2/\60\7@\2\2\60\61\7?\2\2\61\n\3\2\2\2\62\63\7>\2"+
		"\2\63\f\3\2\2\2\64\65\7>\2\2\65\66\7?\2\2\66\16\3\2\2\2\678\7?\2\28\20"+
		"\3\2\2\29:\7k\2\2:;\7p\2\2;\22\3\2\2\2<=\7e\2\2=>\7c\2\2>?\7v\2\2?@\7"+
		"g\2\2@A\7i\2\2AB\7q\2\2BC\7t\2\2CD\7{\2\2D\24\3\2\2\2EF\t\2\2\2F\26\3"+
		"\2\2\2GH\7*\2\2H\30\3\2\2\2IJ\7+\2\2J\32\3\2\2\2KL\7e\2\2LM\7c\2\2MN\7"+
		"v\2\2NO\7g\2\2OP\7i\2\2PQ\7q\2\2QR\7t\2\2RS\7{\2\2S\34\3\2\2\2TU\7p\2"+
		"\2UV\7c\2\2VW\7o\2\2WX\7g\2\2X\36\3\2\2\2YZ\7t\2\2Z[\7c\2\2[\\\7v\2\2"+
		"\\]\7k\2\2]^\7p\2\2^_\7i\2\2_ \3\2\2\2`a\7r\2\2ab\7t\2\2bc\7k\2\2cd\7"+
		"e\2\2de\7g\2\2e\"\3\2\2\2fg\t\3\2\2gh\3\2\2\2hi\b\22\2\2i$\3\2\2\2jl\t"+
		"\4\2\2kj\3\2\2\2lm\3\2\2\2mk\3\2\2\2mn\3\2\2\2nw\3\2\2\2oq\t\5\2\2pr\t"+
		"\4\2\2qp\3\2\2\2rs\3\2\2\2sq\3\2\2\2st\3\2\2\2tv\3\2\2\2uo\3\2\2\2vy\3"+
		"\2\2\2wu\3\2\2\2wx\3\2\2\2x&\3\2\2\2yw\3\2\2\2\6\2msw\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}