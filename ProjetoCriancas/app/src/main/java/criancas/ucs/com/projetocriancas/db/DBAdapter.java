package criancas.ucs.com.projetocriancas.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {
    public static final String KEY_ROWID = "_id";
    public static final String KEY_NOME = "nome";
    public static final String KEY_SEXO = "sexo";
    public static final String KEY_NASCIMENTO = "dtaNascimento";
    public static final String KEY_MES = "mes";
    public static final String KEY_ALTURA = "altura";
    public static final String KEY_PESO = "peso";
    private static final String TAG = "DBAdapter";

    private static final String DATABASE_NAME = "ucs_criancas";
    private static final String DATABASE_TABLE = "criancas";
    private static final int DATABASE_VERSION =1;

    private static final String CRIA_DATABASE = "create table criancas " +
            "(_id integer primary key autoincrement, " +
            " nome text not null," +
            " sexo text not null," +
            " dtaNascimento text not null," +
            " dtaLeitura text not null," +
            " altura integer not null," +
            " peso real not null);" ;
    private final Context contexto;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DBAdapter(Context ctx){
        this.contexto = ctx;
        DBHelper = new DatabaseHelper(contexto); //classe interna que herda de SQLiteOpenHelper
    }

    //classe interna que manipula o banco
    //SQLiteOpenHelper é uma classe abstrata.
    private static class DatabaseHelper extends SQLiteOpenHelper{
        DatabaseHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db){
            try{
                db.execSQL(CRIA_DATABASE);
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion,int newVersion){
            Log.w(TAG, "Atualizando a base de dados a partir da versao " + oldVersion
                    + " para " + newVersion + ",isso irá destruir todos os dados antigos");
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }
    }

    // *******************************************************************************
    //--- abre a base de dados ---
    public DBAdapter open() throws SQLException{
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //--- fecha a base de dados ---
    public void close(){
        DBHelper.close();
    }

    //---insere um Registro na base da dados ---
    public long insereRegistro(String nome, String sexo, String nascimento, Integer mes, Double peso, Integer altura){
        ContentValues dados = new ContentValues();
        dados.put(KEY_NOME, nome);
        dados.put(KEY_SEXO, sexo);
        dados.put(KEY_NASCIMENTO, nascimento);
        dados.put(KEY_MES, mes);
        dados.put(KEY_ALTURA, altura);
        dados.put(KEY_PESO, peso);
        return db.insert(DATABASE_TABLE, null, dados);
    }

    //--- exclui um Registro---
    public boolean excluiRegistro(long idLinha){
        return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + idLinha, null) > 0;
    }

    //--- devolve todos os Registros---
    public boolean excluirRegistros(String nome, String sexo, String nascimento, Integer mes, Double peso, Integer altura){

        String linhaAcessada = "";

        if(nome != null && !nome.trim().isEmpty())
            linhaAcessada = KEY_NOME + "='" + nome+"'";

        if(sexo != null && !sexo.trim().isEmpty()) {

            if(!linhaAcessada.isEmpty())
                linhaAcessada += " AND ";

            linhaAcessada = KEY_SEXO + "='" + sexo+"'";
        }

        if(nascimento != null && !nascimento.trim().isEmpty()) {

            if(!linhaAcessada.isEmpty())
                linhaAcessada += " AND ";

            linhaAcessada += KEY_NASCIMENTO + "='" + nascimento+"'";
        }

        if(mes != null) {

            if(!linhaAcessada.isEmpty())
                linhaAcessada += " AND ";

            linhaAcessada += KEY_MES + "=" + mes;
        }

        if(peso != null) {

            if(!linhaAcessada.isEmpty())
                linhaAcessada += " AND ";

            linhaAcessada += KEY_PESO + "=" + peso;
        }

        if(altura != null) {

            if(!linhaAcessada.isEmpty())
                linhaAcessada += " AND ";

            linhaAcessada += KEY_ALTURA + "=" + altura;
        }

        return db.delete(DATABASE_TABLE, linhaAcessada, null) > 0;
    }

    //--- devolve todos os Registros---
    public Cursor getTodosRegistros(){
        String colunas[] ={KEY_ROWID,KEY_NOME,KEY_SEXO,KEY_NASCIMENTO, KEY_MES, KEY_ALTURA, KEY_PESO};
        return db.query(DATABASE_TABLE,colunas, null, null, null, null, null, null);
    }

    //--- recupera uma linha (Registro) ---
    public Cursor getRegistro(long idLinha) throws SQLException{

        String colunas[] ={KEY_ROWID,KEY_NOME,KEY_SEXO,KEY_NASCIMENTO, KEY_MES, KEY_ALTURA, KEY_PESO};
        String linhaAcessada = KEY_ROWID + "=" + idLinha;
        Cursor mCursor = db.query(DATABASE_TABLE, colunas,linhaAcessada,null,null,null,null,null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    //--- devolve todos os Registros---
    public Cursor getRegistros(String nome, String sexo, String nascimento, Integer mes, Double peso, Integer altura){

        String linhaAcessada = "";

        if(nome != null && !nome.trim().isEmpty())
            linhaAcessada = KEY_NOME + "='" + nome+"'";

        if(sexo != null && !sexo.trim().isEmpty()) {

            if(!linhaAcessada.isEmpty())
                linhaAcessada += " AND ";

            linhaAcessada = KEY_SEXO + "='" + sexo+"'";
        }

        if(nascimento != null && !nascimento.trim().isEmpty()) {

            if(!linhaAcessada.isEmpty())
                linhaAcessada += " AND ";

            linhaAcessada += KEY_NASCIMENTO + "='" + nascimento+"'";
        }

        if(mes != null) {

            if(!linhaAcessada.isEmpty())
                linhaAcessada += " AND ";

            linhaAcessada += KEY_MES + "=" + mes;
        }

        if(peso != null) {

            if(!linhaAcessada.isEmpty())
                linhaAcessada += " AND ";

            linhaAcessada += KEY_PESO + "=" + peso;
        }

        if(altura != null) {

            if(!linhaAcessada.isEmpty())
                linhaAcessada += " AND ";

            linhaAcessada += KEY_ALTURA + "=" + altura;
        }

        String colunas[] ={KEY_ROWID,KEY_NOME,KEY_SEXO,KEY_NASCIMENTO, KEY_MES, KEY_ALTURA, KEY_PESO};
        return db.query(DATABASE_TABLE,colunas, linhaAcessada, null, null, null, null, null);
    }

    //--- atualiza um nome---
    public boolean alteraRegistro(long idLinha, String nome, String sexo, String nascimento, Integer mes, Double peso, Integer altura){
        ContentValues dados = new ContentValues();
        String linhaAcessada = KEY_ROWID + "=" + idLinha;

        dados.put(KEY_NOME, nome);
        dados.put(KEY_NOME, nome);
        dados.put(KEY_SEXO, sexo);
        dados.put(KEY_NASCIMENTO, nascimento);
        dados.put(KEY_MES, mes);
        dados.put(KEY_ALTURA, altura);
        dados.put(KEY_PESO, peso);

        return db.update(DATABASE_TABLE, dados, linhaAcessada, null)>0;
    }
}