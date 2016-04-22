package utng.edu.mx.aprendersoap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class QuizHelper extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1;
	// Database Name
	private static final String DATABASE_NAME = "mathsone";
	// tasks table name
	private static final String TABLE_QUEST = "quest";
	// tasks Table Columns names
	private static final String KEY_ID = "qid";
	private static final String KEY_QUES = "question";
	private static final String KEY_ANSWER = "answer"; // correct option
	private static final String KEY_OPTA = "opta"; // option a
	private static final String KEY_OPTB = "optb"; // option b
	private static final String KEY_OPTC = "optc"; // option c

	private SQLiteDatabase dbase;

	public QuizHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		dbase = db;
		String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
				+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
				+ " TEXT, " + KEY_ANSWER + " TEXT, " + KEY_OPTA + " TEXT, "
				+ KEY_OPTB + " TEXT, " + KEY_OPTC + " TEXT)";
		db.execSQL(sql);
		addQuestion();
		// db.close();
	}

	private void addQuestion() {
		Question q1 = new Question("Es la etiqueta type de WSDL", "<types>", "<type>", "<h:type>", "<types>");
		this.addQuestion(q1);
		Question q2 = new Question("Cual es la etiqueta de los mensajes de WSDL", "<messages>", "<message>", "<p:message>", "<message>");
		this.addQuestion(q2);
		Question q3 = new Question("Cual es la etiqueta de las operaciones de WSDL", "<operations>", "<h:operation>", "<operation>", "<operation>");
		this.addQuestion(q3);
		Question q4 = new Question("Es la etiqueta de portType", "<h:portType>", "<portType>", "<portTypes>", "<portType>");
		this.addQuestion(q4);
		Question q5 = new Question("Etiqueta del elemento binding", "<bindings>", "<binding>", "<h:binding>", "<binding>");
		this.addQuestion(q5);
		Question q6 = new Question("Es una extension del elemnto binding", "<port:binding>", "<portlet:binding>", "<let:binding>", "<portlet:binding>");
		this.addQuestion(q6);
		Question q7 = new Question("Cual es la sintaxis de la importacion del servicio web", "java.js.WebService;", "java.jws.WebService;", "javax.jws.WebService;", "javax.jws.WebService;");
		this.addQuestion(q7);
		Question q8 = new Question("se debe implementar un metodo web @________", "WebMethod", "WebService", "WebPublic", "WebMethod");
		this.addQuestion(q8);
		Question q9 = new Question("Indica que la clase define un servicio web", "@WebMethod", "@WebService", "@WebBinding", "@WebService");
		this.addQuestion(q9);
		Question q10 = new Question("Permite especificar el estilo y la codificación de los mensajes SOAP utilizados para invocar el servicio", "@SOAPBinding", "@SOAPService", "@SOAP", "@SOAPBinding");
		this.addQuestion(q10);
		Question q11 = new Question("Indica que la llamada a la operación no debe esperar ninguna respuesta", "@One", "@Oneway", "@Onemany", "@Oneway");
		this.addQuestion(q11);
		Question q12 = new Question("Permite indicar el nombre que recibirán los parámetros en el fichero WSDL:", "@WebService", "@WebMethod", "@WebParam", "@WebParam");
		this.addQuestion(q12);
		Question q13 = new Question("Permite indicar el nombre que recibirá el mensaje de respuesta en el fichero WSDL:", "@WebResult", "@WebFault", "@WebMethod", "@WebResult");
		this.addQuestion(q13);
		Question q14 = new Question("Se utiliza para anotar excepciones Java", "@WebFault", "@WebResult", "@WebMethod", "@WebFault");
		this.addQuestion(q14);
		Question q15 = new Question("Un método de servicio web detecta un caso de excepción", "SoapExcecution", "SoapException", "SoapProcess", "SoapException");
		this.addQuestion(q15);
		Question q16 = new Question("Un método de servicio web detecta un caso de excepción procesando un elemento de SOAP Header", "SoapException", "SoapException", "SoapHeaderException", "SoapHeaderException");
		this.addQuestion(q16);
		Question q17 = new Question("Que es un Port de WSDL?", "un conjunto abstracto de operaciones soportadas por uno o más puntos finales", "un punto final individual definido como una combinación de una conexióny una dirección de la red", "Un puerto para conectarse al servicio web", "un punto final individual definido como una combinación de una conexióny una dirección de la red");
		this.addQuestion(q17);
		Question q18 = new Question("Que es un Service de WSDL?", "Un servicio web", "otra", "una colección de puntos finales relacionados", "una colección de puntos finales relacionados");
		this.addQuestion(q18);
		Question q19 = new Question("Que significan las siglas SOAP?", "Simple Object Access Protocol", "Super Object Access Protocol", "Simple Object Access Project", "Simple Object Access Protocol");
		this.addQuestion(q19);
		Question q20 = new Question("Que significa las siglas de WSMX?", "Web Service Modelling eXtreme environment", "Web Service Modelling eXecution environment", "Web Service Modelling eXecution enterprice", "Web Service Modelling eXecution environment");
		this.addQuestion(q20);
		Question q21 = new Question("Que significa las siglas de SWSL?", "System Web Services Language", "Semantic Web Services Language", "Semantic Wave Services Language", "Semantic Web Services Language");
		this.addQuestion(q21);
		// END
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
		// Create tables again
		onCreate(db);
	}

	// Adding new question
	public void addQuestion(Question quest) {
		// SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_QUES, quest.getQUESTION());
		values.put(KEY_ANSWER, quest.getANSWER());
		values.put(KEY_OPTA, quest.getOPTA());
		values.put(KEY_OPTB, quest.getOPTB());
		values.put(KEY_OPTC, quest.getOPTC());

		// Inserting Row
		dbase.insert(TABLE_QUEST, null, values);
	}

	public List<Question> getAllQuestions() {
		List<Question> quesList = new ArrayList<Question>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
		dbase = this.getReadableDatabase();
		Cursor cursor = dbase.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Question quest = new Question();
				quest.setID(cursor.getInt(0));
				quest.setQUESTION(cursor.getString(1));
				quest.setANSWER(cursor.getString(2));
				quest.setOPTA(cursor.getString(3));
				quest.setOPTB(cursor.getString(4));
				quest.setOPTC(cursor.getString(5));

				quesList.add(quest);
			} while (cursor.moveToNext());
		}
		// return quest list
		return quesList;
	}


}
