package com.cefet.gpsbus;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by diego on 22/01/2018.
 */

public class DbHelper extends SQLiteOpenHelper {
    /*private static final String INTEGER_TYPE = "integer";
    private static final String VARCHAR_TYPE = "varchar(150)";
    private static final String NUMERIC_TYPE = "numeric(2,20)";
    private static final String CHAR_TYPE = "char(2)";
    private static final String TIME_TYPE = "time";
    private static final String COMMA_SEP = ",";*/

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 7;
    public static final String DATABASE_NAME = "GpsBus.db";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE pontoTuristico (" +
                "id INTEGER PRIMARY KEY NOT NULL," +
                "nome VARCHAR(100) NOT NULL," +
                "descricao VARCHAR(500) NOT NULL," +
                "lat VARCHAR(30) NOT NULL," +
                "lng VARCHAR(30) NOT NULL," +
                "site VARCHAR(300) NOT NULL"+
                ")");

        db.execSQL("CREATE TABLE terminal (" +
                "idPonto INTEGER NOT NULL," +
                "possuiConexao VARCHAR(2) NOT NULL" +
                ")");

        db.execSQL("CREATE TABLE bairro (" +
                "id INTEGER PRIMARY KEY NOT NULL," +
                "nome VARCHAR(100) NOT NULL" +
                ")");

        db.execSQL("CREATE TABLE itinerario (" +
                "idItinerario INTEGER NOT NULL," +
                "idPonto INTEGER NOT NULL," +
                "sort INTEGER NOT NULL," +
                "PRIMARY KEY(idItinerario, sort)" +
                ")");

        db.execSQL("CREATE TABLE onibus (" +
                "numero INTEGER NOT NULL," +
                "nome VARCHAR(100) NOT NULL," +
                "sentido VARCHAR(2) NOT NULL," +
                "diurnoNoturno VARCHAR(2) NOT NULL," +
                "informacoesAdicionais VARCHAR(500) NOT NULL," +
                "idItinerario INTEGER PRIMARY KEY NOT NULL" +
                ")");

        db.execSQL("CREATE TABLE ponto (" +
                "id INTEGER PRIMARY KEY NOT NULL," +
                "lat VARCHAR(30) NOT NULL," +
                "lng VARCHAR(30) NOT NULL," +
                "referencia VARCHAR(150) NOT NULL," +
                "idBairro INTEGER NOT NULL" +
                ")");

        db.execSQL("CREATE TABLE horarioDomingoIda (" +
                "numeroOnibus INT NOT NULL," +
                "horario VARCHAR(10) NOT NULL," +
                "PRIMARY KEY (numeroOnibus, horario)" +
                ")");

        db.execSQL("CREATE TABLE horarioDomingoVolta (" +
                "numeroOnibus INT NOT NULL," +
                "horario VARCHAR(10) NOT NULL," +
                "PRIMARY KEY (numeroOnibus, horario)" +
                ")");

        db.execSQL("CREATE TABLE horarioSabadoIda (" +
                "numeroOnibus INT NOT NULL," +
                "horario VARCHAR(10) NOT NULL," +
                "PRIMARY KEY (numeroOnibus, horario)" +
                ")");

        db.execSQL("CREATE TABLE horarioSabadoVolta (" +
                "numeroOnibus INT NOT NULL," +
                "horario VARCHAR(10) NOT NULL," +
                "PRIMARY KEY (numeroOnibus, horario)" +
                ")");

        db.execSQL("CREATE TABLE horarioSemanaIda (" +
                "numeroOnibus INT NOT NULL," +
                "horario VARCHAR(10) NOT NULL," +
                "PRIMARY KEY (numeroOnibus, horario)" +
                ")");

        db.execSQL("CREATE TABLE horarioSemanaVolta (" +
                "numeroOnibus INT NOT NULL," +
                "horario VARCHAR(10) NOT NULL," +
                "PRIMARY KEY (numeroOnibus, horario)" +
                ")");

        //Log.d("banco_dados", "Criado");

        //Log.d("gera tabelas", "ok");
        /*db.execSQL("CREATE TABLE " + DbContract.FeedEntryOnibus.TABLE_NAME + " (" + DbContract.FeedEntryOnibus.COLUMN_NUMERO + " " + INTEGER_TYPE + " UNIQUE PRIMARY KEY" + COMMA_SEP + " " + DbContract.FeedEntryOnibus.COLUMN_NOME + " " + VARCHAR_TYPE + ")");
        db.execSQL("CREATE TABLE " + DbContract.FeedEntryPontos.TABLE_NAME + " (" + DbContract.FeedEntryPontos.COLUMN_ID + " " + INTEGER_TYPE + " UNIQUE PRIMARY KEY" + COMMA_SEP + " " + DbContract.FeedEntryPontos.COLUMN_LATITUDE + " " + NUMERIC_TYPE + COMMA_SEP + " " + DbContract.FeedEntryPontos.COLUMN_LONGITUDE + " " + NUMERIC_TYPE + COMMA_SEP + " " + DbContract.FeedEntryPontos.COLUMN_SENTIDO + " " + CHAR_TYPE + COMMA_SEP + " " + DbContract.FeedEntryPontos.COLUMN_REFERENCIA + " " + VARCHAR_TYPE + COMMA_SEP + " " + DbContract.FeedEntryPontos.COLUMN_ID_BAIRRO + " " + INTEGER_TYPE + ")");
        db.execSQL("CREATE TABLE " + DbContract.FeedEntryBairros.TABLE_NAME + " (" + DbContract.FeedEntryBairros.COLUMN_ID + " " + INTEGER_TYPE + " UNIQUE PRIMARY KEY" + COMMA_SEP + " " + DbContract.FeedEntryBairros.COLUMN_NOME + " " + VARCHAR_TYPE + ")");
        db.execSQL("CREATE TABLE " + DbContract.FeedEntryItinerario.TABLE_NAME + " (" + DbContract.FeedEntryItinerario.COLUMN_NUMERO_ONIBUS + " " + INTEGER_TYPE + COMMA_SEP + " " + DbContract.FeedEntryItinerario.COLUMN_ID_PONTO + " " + INTEGER_TYPE + COMMA_SEP + " PRIMARY KEY(" + DbContract.FeedEntryItinerario.COLUMN_NUMERO_ONIBUS + COMMA_SEP + " " + DbContract.FeedEntryItinerario.COLUMN_ID_PONTO + "))");
        db.execSQL("CREATE TABLE " + DbContract.FeedEntryHorario.TABLE_NAME + " (" + DbContract.FeedEntryHorario.COLUMN_NUMERO_ONIBUS + " " + INTEGER_TYPE + COMMA_SEP + " " + DbContract.FeedEntryHorario.COLUMN_HORARIO_TERMINAL + " " + NUMERIC_TYPE + COMMA_SEP + " " + DbContract.FeedEntryHorario.COLUMN_HORARIO_BAIRRO + " " + NUMERIC_TYPE + COMMA_SEP + " " + DbContract.FeedEntryHorario.COLUMN_DIA_DA_SEMANA + " " + CHAR_TYPE + COMMA_SEP + " PRIMARY KEY(" + DbContract.FeedEntryHorario.COLUMN_NUMERO_ONIBUS + COMMA_SEP + " " + DbContract.FeedEntryHorario.COLUMN_HORARIO_TERMINAL + COMMA_SEP + " " + DbContract.FeedEntryHorario.COLUMN_HORARIO_BAIRRO + COMMA_SEP + " " + DbContract.FeedEntryHorario.COLUMN_DIA_DA_SEMANA + "));");
        db.execSQL("CREATE TABLE " + DbContract.FeedEntryPontosTuristicos.TABLE_NAME + " (" + DbContract.FeedEntryPontosTuristicos.COLUMN_NAME_ID + " " + INTEGER_TYPE + " UNIQUE PRIMARY KEY " + COMMA_SEP + " " + DbContract.FeedEntryPontosTuristicos.COLUMN_NAME_NOME + " " + VARCHAR_TYPE + "UNIQUE" + COMMA_SEP + " " + DbContract.FeedEntryPontosTuristicos.COLUMN_NAME_DESCRICAO + " " + VARCHAR_TYPE + COMMA_SEP + " " + DbContract.FeedEntryPontosTuristicos.COLUMN_NAME_ID_IMAGEM + " " + VARCHAR_TYPE + ")");*/
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS pontoTuristico");
        db.execSQL("DROP TABLE IF EXISTS terminal");
        db.execSQL("DROP TABLE IF EXISTS bairro");
        db.execSQL("DROP TABLE IF EXISTS itinerario");
        db.execSQL("DROP TABLE IF EXISTS onibus");
        db.execSQL("DROP TABLE IF EXISTS ponto");
        db.execSQL("DROP TABLE IF EXISTS horarioDomingoIda");
        db.execSQL("DROP TABLE IF EXISTS horarioDomingoVolta");
        db.execSQL("DROP TABLE IF EXISTS horarioSabadoIda");
        db.execSQL("DROP TABLE IF EXISTS horarioSabadoVolta");
        db.execSQL("DROP TABLE IF EXISTS horarioSemanaIda");
        db.execSQL("DROP TABLE IF EXISTS horarioSemanaVolta");
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        /*db.execSQL("DROP TABLE IF EXISTS " + DbContract.FeedEntryOnibus.TABLE_NAME + ";");
        db.execSQL("DROP TABLE IF EXISTS " + DbContract.FeedEntryPontos.TABLE_NAME + ";");
        db.execSQL("DROP TABLE IF EXISTS " + DbContract.FeedEntryBairros.TABLE_NAME + ";");
        db.execSQL("DROP TABLE IF EXISTS " + DbContract.FeedEntryItinerario.TABLE_NAME + ";");
        db.execSQL("DROP TABLE IF EXISTS " + DbContract.FeedEntryHorario.TABLE_NAME + ";");
        db.execSQL("DROP TABLE IF EXISTS " + DbContract.FeedEntryPontosTuristicos.TABLE_NAME);*/
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}