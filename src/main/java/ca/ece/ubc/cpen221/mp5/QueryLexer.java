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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, OR=8, AND=9, INEQ=10, 
		NUM=11, LParen=12, RParen=13;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "OR", "AND", "INEQ", 
		"GT", "GTE", "LT", "LTE", "EQ", "NUM", "CHAR", "LParen", "RParen"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'QUERY'", "'in'", "'category'", "'name'", "'rating'", "'price'", 
		"'\"'", "'||'", "'&&'", null, null, "'('", "')'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, "OR", "AND", "INEQ", "NUM", 
		"LParen", "RParen"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\17p\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13"+
		"\3\13\3\13\5\13[\n\13\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\17\3\17\3\17\3\20"+
		"\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\2\2\25\3\3\5\4\7\5\t\6\13"+
		"\7\r\b\17\t\21\n\23\13\25\f\27\2\31\2\33\2\35\2\37\2!\r#\2%\16\'\17\3"+
		"\2\3\3\2\62;m\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2"+
		"\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2"+
		"!\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\3)\3\2\2\2\5/\3\2\2\2\7\62\3\2\2\2\t"+
		";\3\2\2\2\13@\3\2\2\2\rG\3\2\2\2\17M\3\2\2\2\21O\3\2\2\2\23R\3\2\2\2\25"+
		"Z\3\2\2\2\27\\\3\2\2\2\31^\3\2\2\2\33a\3\2\2\2\35c\3\2\2\2\37f\3\2\2\2"+
		"!h\3\2\2\2#j\3\2\2\2%l\3\2\2\2\'n\3\2\2\2)*\7S\2\2*+\7W\2\2+,\7G\2\2,"+
		"-\7T\2\2-.\7[\2\2.\4\3\2\2\2/\60\7k\2\2\60\61\7p\2\2\61\6\3\2\2\2\62\63"+
		"\7e\2\2\63\64\7c\2\2\64\65\7v\2\2\65\66\7g\2\2\66\67\7i\2\2\678\7q\2\2"+
		"89\7t\2\29:\7{\2\2:\b\3\2\2\2;<\7p\2\2<=\7c\2\2=>\7o\2\2>?\7g\2\2?\n\3"+
		"\2\2\2@A\7t\2\2AB\7c\2\2BC\7v\2\2CD\7k\2\2DE\7p\2\2EF\7i\2\2F\f\3\2\2"+
		"\2GH\7r\2\2HI\7t\2\2IJ\7k\2\2JK\7e\2\2KL\7g\2\2L\16\3\2\2\2MN\7$\2\2N"+
		"\20\3\2\2\2OP\7~\2\2PQ\7~\2\2Q\22\3\2\2\2RS\7(\2\2ST\7(\2\2T\24\3\2\2"+
		"\2U[\5\27\f\2V[\5\31\r\2W[\5\33\16\2X[\5\35\17\2Y[\5\37\20\2ZU\3\2\2\2"+
		"ZV\3\2\2\2ZW\3\2\2\2ZX\3\2\2\2ZY\3\2\2\2[\26\3\2\2\2\\]\7@\2\2]\30\3\2"+
		"\2\2^_\7@\2\2_`\7?\2\2`\32\3\2\2\2ab\7>\2\2b\34\3\2\2\2cd\7>\2\2de\7?"+
		"\2\2e\36\3\2\2\2fg\7?\2\2g \3\2\2\2hi\t\2\2\2i\"\3\2\2\2jk\13\2\2\2k$"+
		"\3\2\2\2lm\7*\2\2m&\3\2\2\2no\7+\2\2o(\3\2\2\2\4\2Z\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}