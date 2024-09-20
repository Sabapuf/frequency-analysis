import java.util.HashMap;
import java.util.Map;

public class Algorythm {
    static String CipherText = "ЙФRЫЕ ЛМН ЧЦ?77? ИЮИЮИЮИЮюии чц аыч ъч ъыщсхи ьбнф?!? Ииюиюи фзые ь киъ ъыщсхнщ ьбнф ъч ъыщсхи?! Шщчъыч къыиф с ьбнф??? ЮИЮиююиюиюи Ъыьф ъыщсхсы ас бч?! ИЬЬЬ! Хчпны чц ыих шчмиксфъз кчмчт сфс чй ьлчф ъшчыуцьфъз с йнр ъчрцицсз кифзныъз!! ИЮИЮИ Ць фимцч, з ычлми ычпн кчрехь с ьтмь с цн йьмь цсан цн шсъиые шчуи цн шщсмоы.";
    static String DecipheredText;

    //Создание и заполнение массива частотами русского алфавита

    static private Map <String, Double> dictionary = new HashMap<>();
    static void generateDictionary(){
        String[] RUSSIAN_ALPHABET = {"А", "Б", "В", "Г", "Д", "Е", "Ё", "Ж", "З", "И", "Й", "К",
                "Л", "М", "Н", "О", "П", "Р", "С", "Т", "У", "Ф", "Х",
                "Ц", "Ч", "Ш", "Щ", "Ъ", "Ы", "Ь", "Э", "Ю", "Я"};
        double[] frequencies = {8.00,1.60,4.54,1.59,2.98,7.33,0.02,0.67,2.68,6.72,0.94,3.49,4.34,3.21,6.70,10.9,2.81,4.99,5.47,6.26,3.98,0.26,0.94,0.47,1.52,0.72,0.40,0.03,1.90,1.73,0.30,0.70,2.00};
        for (int i = 0; i < RUSSIAN_ALPHABET.length; i++) {
            dictionary.put(RUSSIAN_ALPHABET[i], frequencies[i]);
        }
    }

    //Метод для вывода массива для проверки
    public static void getDictionary (){
        generateDictionary();
        System.out.println(dictionary);
    }

    //Алгоритм для подсчета частоты каждой буквы в шифротексте

    public static Map <String, Double> textFrequency = textFrequency(CipherText);
    public static Map<String, Double> textFrequency(String CText) {

        String CipherText = CText.toUpperCase();

        Map<String, Double> textFrequency = new HashMap<>();
        for (char c : CipherText.toCharArray()) {
            if (Character.isLetter(c)) {
                textFrequency.put(String.valueOf(c), textFrequency.getOrDefault(String.valueOf(c), 0.0) + 1);
            }
        }

        return textFrequency;
    }

    public static String findKeyWithMaxValue(Map<String, Double> map) {
        double max = Double.MIN_VALUE;
        String keyWithMaxValue = null;

        for (Map.Entry<String, Double> entry : map.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                keyWithMaxValue = entry.getKey();
            }
        }

        return keyWithMaxValue;
    }
    public static String originalChar = String.valueOf('О');
    public static String encryptedChar = findKeyWithMaxValue(textFrequency);
    static int findKey(String originalChar, String encryptedChar) {
        String RUSSIAN_ALPHABET = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";

        int originalIndex = RUSSIAN_ALPHABET.indexOf(originalChar);
        int encryptedIndex = RUSSIAN_ALPHABET.indexOf(encryptedChar);

        if (originalIndex == -1 || encryptedIndex == -1) {
            throw new IllegalArgumentException("Некорректные символы");
        }

        int shift = encryptedIndex - originalIndex;
        if (shift < 0) {
            shift += RUSSIAN_ALPHABET.length();
        }
        return shift;
    }

    //vau

    public static void decryptedText(){
        String decryptedText = CaesarCipher.decrypt(CipherText, findKey(originalChar, encryptedChar));
        System.out.println("Расшифрованный текст: " + decryptedText);
    }

}
