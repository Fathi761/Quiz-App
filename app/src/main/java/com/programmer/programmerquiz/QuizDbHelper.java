package com.programmer.programmerquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.programmer.programmerquiz.QuizContract.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class QuizDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyAwesomeQuiz.db";
    public static final int DATABASE_VERSION = 1;

    private static QuizDbHelper instance;

    private SQLiteDatabase db;

    public QuizDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized QuizDbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new QuizDbHelper(context.getApplicationContext());
        }
        return instance;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_CATEGORIES_TABLE = "CREATE TABLE " +
                CategoriesTable.TABLE_NAME + "( " +
                CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CategoriesTable.COLUMN_NAME + " TEXT " +
                ")";

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuestionsTable.COLUMN_DIFFICULTY + " TEXT, " +
                QuestionsTable.COLUMN_CATEGORY_ID + " INTEGER, " +
                "FOREIGN KEY(" + QuestionsTable.COLUMN_CATEGORY_ID + ") REFERENCES " +
                CategoriesTable.TABLE_NAME + "(" + CategoriesTable._ID + ")" + "ON DELETE CASCADE" +
                ")";



        db.execSQL(SQL_CREATE_CATEGORIES_TABLE);
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillCategoriesTable();
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CategoriesTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    private void fillCategoriesTable() {
        Category c1 = new Category("Programming");
        addCategory(c1);
        Category c2 = new Category("Sport");
        addCategory(c2);
        Category c3 = new Category("Islamic");
        addCategory(c3);
        Category c4 = new Category("General");
        addCategory(c4);

    }

    private void addCategory(Category category) {
        ContentValues cv = new ContentValues();
        cv.put(CategoriesTable.COLUMN_NAME, category.getName());
        db.insert(CategoriesTable.TABLE_NAME, null, cv);
    }

    private void fillQuestionsTable() {
        Question q1 = new Question("Which of the following is not used to save data?",
                "MediaPlayer", "Shared Preferences", "SQLite", 1,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        addQuestion(q1);
        Question q2 = new Question("What is the capital of Germany",
                "New York", "Delhi", "Berlin", 3,
                Question.DIFFICULTY_EASY, Category.GENERAL);
        addQuestion(q2);
        Question q3 = new Question("Is it possible to run program without main() function?",
                "Yes", "No", "May be", 2,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        addQuestion(q3);
        Question q4 = new Question("Which of the following is NOT a general diference between a planet and a star?",
                "Planets are smaller than stars.", "Planets are dimmer than stars", "All planets are made of rock and all stars are made of gas.", 3,
                Question.DIFFICULTY_MEDIUM, Category.GENERAL);
        addQuestion(q4);
        Question q5 = new Question("How many main() function we can have in our project?",
                "1", "2", "No Limit", 1,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        addQuestion(q5);
        Question q6 = new Question("What is sizeof() in C?",
                "Operator", "Function", "Macro", 1,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        addQuestion(q6);
        Question q7 = new Question("Cristiano Ronaldo is a player to which team?",
                "Real Madrid", "Manchester United", "Juventus", 3,
                Question.DIFFICULTY_EASY, Category.SPORT);
        addQuestion(q7);
        Question q8 = new Question("Manchester City Football club is founded in?",
                "1890", "1900", "1880", 3,
                Question.DIFFICULTY_EASY, Category.SPORT);
        addQuestion(q8);
        Question q9 = new Question("Whom was the manager of Chelsea in 2009?",
                "Carlo Ancelotti", "Jose Mourinho", "Antonio Conte", 1,
                Question.DIFFICULTY_MEDIUM, Category.SPORT);
        addQuestion(q9);
        Question q10 = new Question("In 2007, Antonio Conte was a coach for",
                "Juventus", "Atalanta", "Bari",3 ,
                Question.DIFFICULTY_MEDIUM, Category.SPORT);
        addQuestion(q10);
        Question q11 = new Question("The Amazon River is located in:",
                "South America", "Europe", "Asia", 1,
                Question.DIFFICULTY_MEDIUM, Category.GENERAL);
        addQuestion(q11);
        Question q12 = new Question("Corona simpole is ",
                "COVID-19", "COVID-20", "COVID-17", 1,
                Question.DIFFICULTY_MEDIUM, Category.GENERAL);
        addQuestion(q12);
        Question q13 = new Question("The centre of gravity of a spinner during the race lies",
                "Behind his feet", "Ahead of his feet", "At the centre of the body", 2,
                Question.DIFFICULTY_HARD, Category.GENERAL);
        addQuestion(q13);
        Question q14 = new Question("What is meant by computer literacy?",
                "Ability to write computer programs", "Knowing what a computer can and cannot do", "Ability to assemble computers", 2,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        addQuestion(q14);
        Question q15 = new Question("In virtual storage, program segments stored on disk during processing are called",
                "tracks", "blocks", "pages", 3,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        addQuestion(q15);
        Question q16 = new Question("Who was the manager for Napoli in 1962",
                "Giovanni Molino", "Bruno Pesaola", "William Garbutt", 2,
                Question.DIFFICULTY_HARD, Category.SPORT);
        addQuestion(q16);
        Question q17 = new Question("Who win more Classico then the other in La Liga? ",
                "Barcelona", "Real Madrid", "Drawn", 2,
                Question.DIFFICULTY_MEDIUM, Category.SPORT);
        addQuestion(q17);
        Question q18 = new Question("Which of the following country won Football world Cup maximum times?",
                "Germany", "italy", "Brazil", 3,
                Question.DIFFICULTY_MEDIUM, Category.SPORT);
        addQuestion(q18);
        Question q19 = new Question("If there are multiple recycle bin for a hard disk",
                "You can choose which recycle bin to use to store your deleted files", "You can set different size for each recycle bin", "Non of above", 2,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        addQuestion(q19);
        Question q20 = new Question("What do Muslims recite during and throughout the Hajj?",
                "Al-Fatiha", "Taalbiya", "Declaration of Hajj", 2,
                Question.DIFFICULTY_EASY, Category.ISLAMIC);
        addQuestion(q20);
        Question q21 = new Question("What is the state of purity in which all pilgrims enter Makkah for Hajj called?",
                "Adhaan", "Istikhara", "Ihram", 3,
                Question.DIFFICULTY_EASY, Category.ISLAMIC);
        addQuestion(q21);
        Question q22 = new Question("How many times do the Muslims circumambulate the Kaabah?",
                "Seven Times", "Five Times", "Three Times",1 ,
                Question.DIFFICULTY_MEDIUM, Category.ISLAMIC);
        addQuestion(q22);
        Question q23 = new Question("How many days does the month of Ramadhan or Month of Fasting last?",
                "28 or 29 days", "29 or 30 days", "30 or 31 days", 2,
                Question.DIFFICULTY_MEDIUM, Category.ISLAMIC);
        addQuestion(q23);
        Question q24 = new Question("During which month do Muslims fast for a month?",
                "Ramadan", "Sha'ban", "Shawwal", 1,
                Question.DIFFICULTY_EASY, Category.ISLAMIC);
        addQuestion(q24);
        Question q25 = new Question("What do Muslims say when thanking someone?",
                "Alhamdulillah ", "Jazak Allah Khair", "Subhanallah", 2,
                Question.DIFFICULTY_MEDIUM, Category.ISLAMIC);
        addQuestion(q25);
        Question q26 = new Question("What is the mannerism of brotherhood taught by the Prophet Muhammad (PBUH)?",
                "Everyone should be selfish", "Brothers do not deserve anything", "One should wish for the brother the same as he wishes for himself", 3,
                Question.DIFFICULTY_HARD, Category.ISLAMIC);
        addQuestion(q26);
        Question q27 = new Question("What do Muslims say when assuring someone or intending to do something in the future?",
                "In Sha Allah", "Alhamdulillah", "Bismillah", 1,
                Question.DIFFICULTY_EASY, Category.ISLAMIC);
        addQuestion(q27);
        Question q28 = new Question("Of which companion did the Prophet say, “If there was to be a Prophet after Me, it would have been you”?",
                "Abu Bakr Al-Siddiq", "Omar Bin Al-Khatab", "Ali Bin Abu Talib", 2,
                Question.DIFFICULTY_HARD, Category.ISLAMIC);
        addQuestion(q28);
        Question q29 = new Question("How many chapters are there in Quran?",
                "116", "118", "114", 3,
                Question.DIFFICULTY_HARD, Category.ISLAMIC);
        addQuestion(q29);
        Question q30 = new Question("Name the last prophet of Islam:",
                "Mohammad", "Mousa", "Issa", 1,
                Question.DIFFICULTY_MEDIUM, Category.ISLAMIC);
        addQuestion(q30);
        Question q31 = new Question("Last chapter in Quran:",
                "An-Nas", "Al Ikhlas", "Al Fatiha", 1,
                Question.DIFFICULTY_EASY, Category.ISLAMIC);
        addQuestion(q31);
        Question q32 = new Question("In how many ways do the Muhammad get messages according to Quran?",
                "In two ways", "In four ways", "In three ways", 3,
                Question.DIFFICULTY_HARD, Category.ISLAMIC);
        addQuestion(q32);
        Question q33 = new Question("Who is the first Caliph after Mohammad?",
                "Caliph Omar", "Caliph Abu Bakr.", "Caliph Ali", 2,
                Question.DIFFICULTY_EASY, Category.ISLAMIC);
        addQuestion(q33);
        Question q34 = new Question("Quran is divided into how many parts?",
                "30", "34", "25", 1,
                Question.DIFFICULTY_MEDIUM, Category.ISLAMIC);
        addQuestion(q34);
        Question q35 = new Question("The supposed span of life of Noah",
                "800 years", "950 years", "900 years", 2,
                Question.DIFFICULTY_HARD, Category.ISLAMIC);
        addQuestion(q35);
        Question q36 = new Question("Who is the official spokesman of Quran?",
                "Abo Bakir", "Omar", "Mohammad", 3,
                Question.DIFFICULTY_EASY, Category.ISLAMIC);
        addQuestion(q36);
        Question q37 = new Question("What is the term used to refer to the explanations and actions of Mohammad?",
                "Poetry", "Hadith", "Wisdom", 2,
                Question.DIFFICULTY_HARD, Category.ISLAMIC);
        addQuestion(q37);
        Question q38 = new Question(" How many subjects as classified are there in Quran?",
                "4", "7", "5", 3,
                Question.DIFFICULTY_HARD, Category.ISLAMIC);
        addQuestion(q38);
        Question q39 = new Question("How many times in a day should one observe Salah?",
                "Five times", "Seven times", "Three times", 1,
                Question.DIFFICULTY_EASY, Category.ISLAMIC);
        addQuestion(q39);
        Question q40 = new Question("What does the word Umm al-Qurā mean?",
                "Mother of century", "Mother of country.", "Mother of towns", 3,
                Question.DIFFICULTY_MEDIUM, Category.ISLAMIC);
        addQuestion(q40);
        Question q41 = new Question("What is ‘Arafah’?",
                "A Large ground", "A huge place", "A small ground", 1,
                Question.DIFFICULTY_MEDIUM, Category.ISLAMIC);
        addQuestion(q41);
        Question q42 = new Question("After how many days can a divorced woman marry?",
                "After 1 month", "After an iddah.", "Non specific time", 2,
                Question.DIFFICULTY_MEDIUM, Category.ISLAMIC);
        addQuestion(q42);
        Question q43 = new Question("How many skies does Quran mention about?",
                "Five ", "Eight", "Seven", 3,
                Question.DIFFICULTY_HARD, Category.ISLAMIC);
        addQuestion(q43);
        Question q44 = new Question("Where did Ibrahim accommodate his second son, Ishaq Nabi and his family?",
                "In Makka", "In Palestine", "In Bilad El Sham", 2,
                Question.DIFFICULTY_HARD, Category.ISLAMIC);
        addQuestion(q44);
        Question q45 = new Question("Who is Yūsuf Nabi?",
                "Son of Ibrahim", "Son of Yakub ", "Son of Nooh", 2,
                Question.DIFFICULTY_EASY, Category.ISLAMIC);
        addQuestion(q45);
        Question q46 = new Question("Where did Syu’aib Nabi live?",
                "In Makka", "In Madyna", "In Palestine", 2,
                Question.DIFFICULTY_EASY, Category.ISLAMIC);
        addQuestion(q46);
        Question q47 = new Question("Is there any Prophet after our Holy Prophet Sallallahu ‘Alaihi Wa Sallam?",
                "No", "Yes", "I don't know", 1,
                Question.DIFFICULTY_EASY, Category.ISLAMIC);
        addQuestion(q47);
        Question q48 = new Question("What is the number of days in Islamic Calendar?",
                "355", "354", "356", 2,
                Question.DIFFICULTY_MEDIUM, Category.ISLAMIC);
        addQuestion(q48);
        Question q49 = new Question("What is the significance of 786 in Islam?",
                "Number of villages in Arabia",
                "Sum of the numerical value of Arabic letters for “Bismillah ir Rahman ir Rahim”",
                "Year of Hijra", 2,
                Question.DIFFICULTY_HARD, Category.ISLAMIC);
        addQuestion(q49);
        Question q50 = new Question("Messages revealed to Muhammad during which period are collected in Quran?",
                "During the age of 64 to 80", "During the age of 29 to 40", "During the age of 41 to 63", 3,
                Question.DIFFICULTY_HARD, Category.ISLAMIC);
        addQuestion(q50);
        Question q51 = new Question("Which crop is sown on the largest area in India?",
                "Sugracne", "Wheat", "Rice", 3,
                Question.DIFFICULTY_HARD, Category.GENERAL);
        addQuestion(q51);
        Question q52 = new Question("Entomology is the science that studies",
                "Insects", "Behaviour of human beings", "The origin and history of technical and scientific terms", 1,
                Question.DIFFICULTY_HARD, Category.GENERAL);
        addQuestion(q52);
        Question q53 = new Question("Galileo was an astronomer who",
                "developed the telescope", "discovered four satellites of Jupiter", "All the above.", 1,
                Question.DIFFICULTY_HARD, Category.GENERAL);
        addQuestion(q53);
        Question q54 = new Question("The world smallest country is",
                "Canada", "Vatican City", "Maldives", 2,
                Question.DIFFICULTY_EASY, Category.GENERAL);
        addQuestion(q54);
        Question q55 = new Question("Novak Djokovic is a famous player associated with the game of",
                "Football", "Chess", "Tennis", 3,
                Question.DIFFICULTY_EASY, Category.GENERAL);
        addQuestion(q55);
        Question q56 = new Question("Which country below is not one of the members of the UN security council (Jan 2003)?",
                "France", "Germany", "China", 2,
                Question.DIFFICULTY_MEDIUM, Category.GENERAL);
        addQuestion(q56);
        Question q57 = new Question("What is the second largest country (in size) in the world?",
                "China", "Canada", "Russia", 2,
                Question.DIFFICULTY_EASY, Category.GENERAL);
        addQuestion(q57);
        Question q58 = new Question("The state which has the largest number of sugar mills in India is",
                "Bihar", "Uttar Pradesh", "Punjab", 2,
                Question.DIFFICULTY_HARD, Category.GENERAL);
        addQuestion(q58);
        Question q59 = new Question("For which of the following disciplines is Nobel Prize awarded?",
                "Physiology or Medicine", "Literature, Peace and Economics", "All of the above", 3,
                Question.DIFFICULTY_EASY, Category.GENERAL);
        addQuestion(q59);
        Question q60 = new Question("What is the world's most common religion?",
                "Muslim", "Christianity", "Buddhism", 2,
                Question.DIFFICULTY_MEDIUM, Category.GENERAL);
        addQuestion(q60);
        Question q61 = new Question("The common elements of NIFE layer of the earth include",
                "Silicon and Magnesium", "Nickel and Iron", "Silver and Tungsten", 2,
                Question.DIFFICULTY_EASY, Category.GENERAL);
        addQuestion(q61);
        Question q62 = new Question("What percentage of land area should remain covered by forest to maintain Ecological balance?",
                "10%", "5%", "33%", 3,
                Question.DIFFICULTY_HARD, Category.GENERAL);
        addQuestion(q62);
        Question q63 = new Question("World Wildlife Day is celebrated at:",
                "25th January", "27th January", "3rd March", 3,
                Question.DIFFICULTY_MEDIUM, Category.GENERAL);
        addQuestion(q63);
        Question q64 = new Question("The main source of water pollution is :",
                "Sewage Water", "Atmospheric Pollutants", "Rain Water", 1,
                Question.DIFFICULTY_MEDIUM, Category.GENERAL);
        addQuestion(q64);
        Question q65 = new Question("The concentration of which gas is highest in our environment?",
                "Oxygen", "Nitrogen", "Hydrogen", 2,
                Question.DIFFICULTY_MEDIUM, Category.GENERAL);
        addQuestion(q65);
        Question q66 = new Question("Which among the following is not required for the formation of Photochemical smog?",
                "Oxide of Nitrogen", "Carbon Monoxide", "Sunlight", 2,
                Question.DIFFICULTY_HARD, Category.GENERAL);
        addQuestion(q66);
        Question q67 = new Question("In a food chain, the solar energy utilized by plants is only",
                "1%", "10%", "0.1%", 2,
                Question.DIFFICULTY_MEDIUM, Category.GENERAL);
        addQuestion(q67);
        Question q68 = new Question("Which one of the following is the reason for happening of days and nights?",
                "earth's rotation", "Sun's rotation", "Non of the above", 1,
                Question.DIFFICULTY_EASY, Category.GENERAL);
        addQuestion(q68);
        Question q69 = new Question("What country is also known as Persia?",
                "Iraq", "Iran", "Istambul", 2,
                Question.DIFFICULTY_EASY, Category.GENERAL);
        addQuestion(q69);
        Question q70 = new Question("Which of the following countries are separated by the Strait of Gibraltar?",
                "Portugal and Morocco", " Morroco and Spain", "Algeria and Portugal", 2,
                Question.DIFFICULTY_MEDIUM, Category.GENERAL);
        addQuestion(q70);
        Question q71 = new Question("",
                "Canada", "West Africa", "Australia", 3,
                Question.DIFFICULTY_HARD, Category.GENERAL);
        addQuestion(q71);
        Question q72 = new Question("Ceylon is the former name of what country?",
                "India", "Sri Lanka", "Pakistan", 2,
                Question.DIFFICULTY_MEDIUM, Category.GENERAL);
        addQuestion(q72);
        Question q73 = new Question("What is the capital of Lebanon?",
                "Beirut", "Saidon", "Tyr", 1,
                Question.DIFFICULTY_EASY, Category.GENERAL);
        addQuestion(q73);
        Question q74 = new Question("What is the surface temperature of Sun?",
                "6000 K", " 5778 K", "7194 K", 2,
                Question.DIFFICULTY_MEDIUM, Category.GENERAL);
        addQuestion(q74);
        Question q75 = new Question("Which of the following is known as White Coal due to environmental friendly",
                "Thermal Power", "Water Supply", "Non of These", 2,
                Question.DIFFICULTY_HARD, Category.GENERAL);
        addQuestion(q75);
        Question q76 = new Question("What is the diameter of earth?",
                "13, 990 Km", "18, 527 Km", "12, 742 Km", 2,
                Question.DIFFICULTY_EASY, Category.GENERAL);
        addQuestion(q76);
        Question q77 = new Question("Which among the following has the world's largest reserves of Uranium?",
                "Australia", "USA", "Russian Federation", 1,
                Question.DIFFICULTY_HARD, Category.GENERAL);
        addQuestion(q77);
        Question q78 = new Question("If a computer has more than one processor then it is known as?",
                "Multithreaded", "Multiprocessor", "Multiprogramming", 2,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        addQuestion(q78);
        Question q79 = new Question(" “Macintosh” an Operating System is a product of ?",
                "Microsoft", "Apple", "Intel", 2,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        addQuestion(q79);
        Question q80 = new Question("Which of the following are components of Central Processing Unit (CPU)?",
                "Arithmetic logic unit, Mouse", "Arithmetic logic unit, Control unit", "Arithmetic logic unit, Integrated Circuits", 2,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        addQuestion(q80);
        Question q81 = new Question("Full form of URL is?",
                "Uniform Resource Link", "Unified Resource Link", "Uniform Resource Locator", 3,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        addQuestion(q81);
        Question q82 = new Question("________ are software which is used to do a particular task",
                "Program", "Operating System", "Data", 1,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        addQuestion(q82);
        Question q83 = new Question("One kilobyte (KB) is equal to",
                "1000 bits", "1024 bytes", "1024 megabytes", 2,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        addQuestion(q83);
        Question q84 = new Question("_______ is the application of investigation and analysis techniques to gather and preserve evidence from a particular computing device in a way that is suitable for presentation in a court of law.",
                "Robotics", "Computer Forensics", "Animation", 2,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        addQuestion(q84);
        Question q85 = new Question("The practice of collecting into a single database data relevant to a particular function, department, etc is known as?",
                "Data Mining", "Data Diddling", "Data Marting", 3,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        addQuestion(q85);
        Question q86 = new Question("The rules of a language is known as",
                "Structure", "Syntax", "Code", 2,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        addQuestion(q86);
        Question q87 = new Question("Father of ‘C’ programming language?",
                "Thomas Kurtz", "Dennis Ritchie", "Bill Gates",2 ,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        addQuestion(q87);
        Question q88 = new Question("SMPS stands for",
                "Store mode power supply", "Start mode power supply", "Switched mode power suppl", 3,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        addQuestion(q88);
        Question q89 = new Question("The lowest form of Computer language is called",
                "BASIC", "FORTRAN", "Machine Language", 3,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        addQuestion(q89);
        Question q90 = new Question("Symantec is the maker of which among the following popular antivirus software?",
                "Notron", "BitDefender", "Avast", 1,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        addQuestion(q90);
        Question q91 = new Question("The basic unit of a worksheet into which you enter data in Excel is called a",
                "Tab", "Cell", "Box", 2,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        addQuestion(q91);
        Question q92 = new Question("Information can be stored or retrieved from memory location through its",
                "Variable declaration", "Address", "Value", 2,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        addQuestion(q92);
        Question q93 = new Question("What is a floppy disk used for",
                "To unlock the computer", "To store infrmation", "To make the printer work",2 ,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        addQuestion(q93);
        Question q94 = new Question("A compact disc (CD) is a data storage system of the type:",
                "Magnetic", "Optical", "Electrical", 2,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        addQuestion(q94);
        Question q95 = new Question(" One nibble is equal to how many bits?",
                "4 bits", "8 bits", "16 bits", 1,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        addQuestion(q95);
        Question q96 = new Question("NOS stands for",
                "Node operating system", "Network Operating system", "Non-operating software", 2,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        addQuestion(q96);
        Question q97 = new Question(" A program that performs a useful task while simultaneously allowing destructive acts is a",
                "Trojan horse", "Worm", "Virus", 1,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        addQuestion(q97);
        Question q98 = new Question("The common name for the crime of stealing passwords is",
                " spooling", "identity theft", "spoofing", 3,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        addQuestion(q98);
        Question q99 = new Question("A storage area used to store data to a compensate for the difference in speed at which the different units can handle data is",
                "Memory", "Buffer", "Address", 2,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        addQuestion(q99);
        Question q100 = new Question("Which is the national sport of Canada?",
                "Lacrosse/Ice hockey", "Volleyball", "Cricket", 1,
                Question.DIFFICULTY_MEDIUM, Category.SPORT);
        addQuestion(q100);
        Question q101 = new Question("Famous Tennis player Stefan Edberg belongs to",
                "U.K", "Sweden", "Italy", 2,
                Question.DIFFICULTY_EASY, Category.SPORT);
        addQuestion(q101);
        Question q102 = new Question("How long are professional Golf Tour players allotted per short?",
                "55 seconds", "85 seconds", "45 seconds", 3,
                Question.DIFFICULTY_HARD, Category.SPORT);
        addQuestion(q102);
        Question q103 = new Question("2010 Commonwealth Games held in?",
                "Canada", "India", "Britain", 2,
                Question.DIFFICULTY_HARD, Category.SPORT);
        addQuestion(q103);
        Question q104 = new Question("Which of the following countries won the final of the triangular cricket series held in Durban in February 1997?",
                "South Africa", "New Zealand", "India", 1,
                Question.DIFFICULTY_HARD, Category.SPORT);
        addQuestion(q104);
        Question q105 = new Question("World cup of hockey is held after the gap of",
                "2 years", "3 years", "4 years", 3,
                Question.DIFFICULTY_MEDIUM, Category.SPORT);
        addQuestion(q105);
        Question q106 = new Question("FIFA World Cup 2014 was held in",
                "Brazil", "London", "Germany", 2,
                Question.DIFFICULTY_EASY, Category.SPORT);
        addQuestion(q106);
        Question q107 = new Question("When was the first Common Wealth Games held?",
                "1930", "1934", "1948", 1,
                Question.DIFFICULTY_EASY, Category.SPORT);
        addQuestion(q107);
        Question q108 = new Question(" Westchester Cup belongs to",
                "Cricket", "POLO", "Hockey", 2,
                Question.DIFFICULTY_MEDIUM, Category.SPORT);
        addQuestion(q108);
        Question q109 = new Question("Who is the world's greatest goal scorer?",
                "Messi", "Ronaldo", "Pele", 3,
                Question.DIFFICULTY_EASY, Category.SPORT);
        addQuestion(q109);
        Question q110 = new Question("How many players are there on each side in a baseball match?",
                "7", "5", "11", 2,
                Question.DIFFICULTY_EASY, Category.SPORT);
        addQuestion(q110);
        Question q111 = new Question("In which sports is the participant called pugilist?",
                "Sprinter", "Boxing", "Wrestling", 2,
                Question.DIFFICULTY_MEDIUM, Category.SPORT);
        addQuestion(q111);
        Question q112 = new Question("The term ‘Butterfly Stroke’ is referred to in which sport?",
                "Tennis", "Volleyball", "Swimming", 3,
                Question.DIFFICULTY_EASY, Category.SPORT);
        addQuestion(q112);
        Question q113 = new Question(" In which game the term ‘Putting’ is used?",
                "Golf", "Hockey", "Chess", 1,
                Question.DIFFICULTY_EASY, Category.SPORT);
        addQuestion(q113);
        Question q114 = new Question("Who was the first Test Centurion in India Cricket?",
                "C.K. Naidu", "Lala Amarnath", "Vinu Manked", 2,
                Question.DIFFICULTY_HARD, Category.SPORT);
        addQuestion(q114);
        Question q115 = new Question("Ryder Cup is related with which sports?",
                "Football", "Tennis", "Golf", 3,
                Question.DIFFICULTY_MEDIUM, Category.SPORT);
        addQuestion(q115);
        Question q116 = new Question("The number of players in each side in Water Polo is",
                "9", "6", "7", 2,
                Question.DIFFICULTY_EASY, Category.SPORT);
        addQuestion(q116);
        Question q117 = new Question("Hopman Cup is related with which sports?",
                "Football", "Tennis", "Hockey", 2,
                Question.DIFFICULTY_HARD, Category.SPORT);
        addQuestion(q117);
        Question q118 = new Question("2018 FIFA World Cup would be held in",
                "France", "Qatar", "Russia", 3,
                Question.DIFFICULTY_EASY, Category.SPORT);
        addQuestion(q118);
        Question q119 = new Question("The term Pitcher is associated with:",
                "Basketball", "Baseball", "Boxing", 2,
                Question.DIFFICULTY_HARD, Category.SPORT);
        addQuestion(q119);
        Question q120 = new Question("The first World Cup in cricket was held in",
                "1971", "1975", "1977", 2,
                Question.DIFFICULTY_HARD, Category.SPORT);
        addQuestion(q120);
        Question q121 = new Question("The word “Jump ball” is associated with",
                "Baseball", "Basketball", "Netball", 2,
                Question.DIFFICULTY_MEDIUM, Category.SPORT);
        addQuestion(q121);
        Question q122 = new Question("Where did the game of Chess originate?",
                "India", "Europe", "Arabia", 1,
                Question.DIFFICULTY_HARD, Category.SPORT);
        addQuestion(q122);
        Question q123 = new Question("Santosh Trophy is associated with",
                "Hockey", "Football", "Basketball", 2,
                Question.DIFFICULTY_HARD, Category.SPORT);
        addQuestion(q123);
        Question q124 = new Question("Kun Aguero is a player to which team?",
                "Manchester United", "Chelsea", "Manchester City", 3,
                Question.DIFFICULTY_EASY, Category.SPORT);
        addQuestion(q124);
    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuestionsTable.COLUMN_DIFFICULTY, question.getDifficulty());
        cv.put(QuestionsTable.COLUMN_CATEGORY_ID, question.getCategoryID());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + CategoriesTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Category category = new Category();
                category.setId(c.getInt(c.getColumnIndex(CategoriesTable._ID)));
                category.setName(c.getString(c.getColumnIndex(CategoriesTable.COLUMN_NAME)));
                categoryList.add(category);
            } while (c.moveToNext());
        }

        c.close();
        return categoryList;
    }

    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }

    public ArrayList<Question> getQuestions(int categoryID, String difficulty) {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        String selection = QuestionsTable.COLUMN_CATEGORY_ID + " = ? " +
                " AND " + QuestionsTable.COLUMN_DIFFICULTY + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(categoryID), difficulty};

        Cursor c = db.query(
                QuestionsTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
}
