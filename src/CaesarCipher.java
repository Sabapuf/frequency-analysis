public class CaesarCipher {
    private static final String RUSSIAN_ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    public static String decrypt(String encryptedText, int shift) {
        StringBuilder decryptedText = new StringBuilder();
        for (char c : encryptedText.toCharArray()) {
            int index = RUSSIAN_ALPHABET.indexOf(Character.toLowerCase(c));
            if (index != -1) {
                int newIndex = (index - shift + RUSSIAN_ALPHABET.length()) % RUSSIAN_ALPHABET.length();
                decryptedText.append(RUSSIAN_ALPHABET.charAt(newIndex));
            } else {
                decryptedText.append(c); // Если символ не буква русского алфавита, оставляем его без изменений
            }
        }
        return decryptedText.toString();
    }
}
