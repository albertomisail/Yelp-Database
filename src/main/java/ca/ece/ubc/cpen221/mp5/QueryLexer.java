package ca.ece.ubc.cpen221.mp5;
// Generated from Query.g4 by ANTLR 4.5.3
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QueryLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		OR=1, AND=2, GT=3, GTE=4, LT=5, LTE=6, EQ=7, IN=8, CATEGORY=9, NUM=10, 
		STRING=11, LPAREN=12, RPAREN=13, QUERYTOKEN=14, CATEGORYTOKEN=15, NAMETOKEN=16, 
		RATINGTOKEN=17, PRICETOKEN=18;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"OR", "AND", "GT", "GTE", "LT", "LTE", "EQ", "IN", "CATEGORY", "NUM", 
		"STRING", "LPAREN", "RPAREN", "QUERYTOKEN", "CATEGORYTOKEN", "NAMETOKEN", 
		"RATINGTOKEN", "PRICETOKEN"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'||'", "'&&'", "'>'", "'>='", "'<'", "'<='", "'='", "'in'", null, 
		null, null, "'('", "')'", "'QUERY'", null, "'name'", "'rating'", "'price'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "OR", "AND", "GT", "GTE", "LT", "LTE", "EQ", "IN", "CATEGORY", "NUM", 
		"STRING", "LPAREN", "RPAREN", "QUERYTOKEN", "CATEGORYTOKEN", "NAMETOKEN", 
		"RATINGTOKEN", "PRICETOKEN"
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


	public QueryLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Query.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\24|\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\3\2\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\7\3\7"+
		"\3\7\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13"+
		"\3\f\6\fI\n\f\r\f\16\fJ\3\f\3\f\6\fO\n\f\r\f\16\fP\7\fS\n\f\f\f\16\fV"+
		"\13\f\3\r\3\r\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3"+
		"\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\2\2\24\3\3\5\4\7\5\t"+
		"\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23"+
		"%\24\3\2\5\3\2\63\67\4\2C\\c|\4\2\13\13\"\"~\2\3\3\2\2\2\2\5\3\2\2\2\2"+
		"\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2"+
		"\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2"+
		"\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\3\'\3\2\2"+
		"\2\5*\3\2\2\2\7-\3\2\2\2\t/\3\2\2\2\13\62\3\2\2\2\r\64\3\2\2\2\17\67\3"+
		"\2\2\2\219\3\2\2\2\23<\3\2\2\2\25E\3\2\2\2\27H\3\2\2\2\31W\3\2\2\2\33"+
		"Y\3\2\2\2\35[\3\2\2\2\37a\3\2\2\2!j\3\2\2\2#o\3\2\2\2%v\3\2\2\2\'(\7~"+
		"\2\2()\7~\2\2)\4\3\2\2\2*+\7(\2\2+,\7(\2\2,\6\3\2\2\2-.\7@\2\2.\b\3\2"+
		"\2\2/\60\7@\2\2\60\61\7?\2\2\61\n\3\2\2\2\62\63\7>\2\2\63\f\3\2\2\2\64"+
		"\65\7>\2\2\65\66\7?\2\2\66\16\3\2\2\2\678\7?\2\28\20\3\2\2\29:\7k\2\2"+
		":;\7p\2\2;\22\3\2\2\2<=\7e\2\2=>\7c\2\2>?\7v\2\2?@\7g\2\2@A\7i\2\2AB\7"+
		"q\2\2BC\7t\2\2CD\7{\2\2D\24\3\2\2\2EF\t\2\2\2F\26\3\2\2\2GI\t\3\2\2HG"+
		"\3\2\2\2IJ\3\2\2\2JH\3\2\2\2JK\3\2\2\2KT\3\2\2\2LN\t\4\2\2MO\t\3\2\2N"+
		"M\3\2\2\2OP\3\2\2\2PN\3\2\2\2PQ\3\2\2\2QS\3\2\2\2RL\3\2\2\2SV\3\2\2\2"+
		"TR\3\2\2\2TU\3\2\2\2U\30\3\2\2\2VT\3\2\2\2WX\7*\2\2X\32\3\2\2\2YZ\7+\2"+
		"\2Z\34\3\2\2\2[\\\7S\2\2\\]\7W\2\2]^\7G\2\2^_\7T\2\2_`\7[\2\2`\36\3\2"+
		"\2\2ab\7e\2\2bc\7c\2\2cd\7v\2\2de\7g\2\2ef\7i\2\2fg\7q\2\2gh\7t\2\2hi"+
		"\7{\2\2i \3\2\2\2jk\7p\2\2kl\7c\2\2lm\7o\2\2mn\7g\2\2n\"\3\2\2\2op\7t"+
		"\2\2pq\7c\2\2qr\7v\2\2rs\7k\2\2st\7p\2\2tu\7i\2\2u$\3\2\2\2vw\7r\2\2w"+
		"x\7t\2\2xy\7k\2\2yz\7e\2\2z{\7g\2\2{&\3\2\2\2\6\2JPT\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}